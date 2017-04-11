/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import logica.LogicaMercado;
import entidades.Club;
import entidades.Jugador;
import entidades.Oferta;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.inject.Inject;

/**
 *
 * @author ramiro
 */
@Named(value = "mercadoController")
@SessionScoped
public class MercadoController implements Serializable {

    @Inject
    private LogicaMercado miLogicaMercado;

    @Inject
    private SesionController sesionController;

    private List<Jugador> listaDeLibres;

    private List<Oferta> ofertasEnviadas;

    private Oferta oferta;

    private void cargarOfertas() {

    }

    public Oferta getOferta() {
        return oferta;
    }

    public void setOferta(Oferta oferta) {
        this.oferta = oferta;
    }

    public List<Oferta> getOfertasEnviadas() {
        return ofertasEnviadas;
    }

    public void setOfertasEnviadas(List<Oferta> ofertasEnviadas) {
        this.ofertasEnviadas = ofertasEnviadas;
    }

    public MercadoController() {

    }

    public List<Jugador> listarLibres() {
        return miLogicaMercado.listarLibres();
    }

    public List<Jugador> getListaDeLibres() {
        return listaDeLibres;
    }

    public void setListaDeLibres(List<Jugador> listaDeLibres) {
        this.listaDeLibres = listaDeLibres;
    }

    public void transferir(Jugador j, Club c, int condicion) {

        miLogicaMercado.transferirLibre(j, c, condicion);
        System.out.println("en controller!");

        this.listaDeLibres.remove(j);

    }

    public void liberarJugador(Jugador j, Club c) {

        if (miLogicaMercado.liberarJugador(j, c)) {
            this.addMessage("El jugador ahora esta libre.", null);
        }else{
            this.addMessage("No se puede liberar a este jugador", null);
        }
        this.listaDeLibres = listarLibres();

    }

    @PostConstruct
    public void inicializar() {
        this.actualizar();
    }

    public void actualizar() {
        listaDeLibres = listarLibres();
    }

    public String evaluarOferta(Oferta oferta) {

        this.oferta = oferta;
        miLogicaMercado.setOfertaEnCuestion(oferta);

        return "evaluarOferta";
    }

    public String transferirClubAClub() {

        if (miLogicaMercado.transferir(oferta)) {

            sesionController.getUsuarioLogueado().getClub().getOfertasRecibidas().remove(oferta);
            System.out.println("Jugador transferido");
            this.addMessage("El jugador fue transferido al club " + oferta.getOrigen().getNombre() + ". Le quedan " + oferta.getMontoDeOperacion() + " limpios por la transaccion.", "");
            return "plantel";
        }

        System.out.println("************* la oferta no supero la validacion ***********");
        this.addMessage("La oferta no supero la validacion del sistema. Verifique si le alcanza el dinero y el tamaño de su plantel", "");
        return "#";
    }

    public String rechazarOferta() {

        miLogicaMercado.rechazarOferta(this.oferta);
        sesionController.getUsuarioLogueado().getClub().getOfertasRecibidas().remove(oferta);
        this.addMessage("La oferta fue rechazada", "");
        return "usuario/plantel";
    }

    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
