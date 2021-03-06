/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import logica.LogicaMercado;
import entidades.Jugador;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author alumno
 */
@Named
@SessionScoped
public class BuscarJugadorController implements Serializable {

    @Inject
    LogicaMercado logicaMercado;

    @Inject
    SesionController sesionController;

    private String nombreJugador;
    private List<Jugador> resultado;
    private Double montoDeOperacion;
    private Jugador jugadorEnCuestion;
    private String nombreClub;

    public String getNombreClub() {
        return nombreClub;
    }

    public void setNombreClub(String nombreClub) {
        this.nombreClub = nombreClub;
    }

    public String irADetalleOferta(Jugador jugador) {
        
        if(jugador.getClub()==null){
            jugadorEnCuestion=jugador;
            return "jugadoresLibres";
        }

        if (sesionController.getUsuarioLogueado().getClub().getId() == jugador.getClub().getId()) {
            this.addMessage("No puede ofertar por un jugador propio", null);
            return "";
        } else {
            jugadorEnCuestion = jugador;
            return "detallesOferta";
        }

    }

    public Jugador getJugadorEnCuestion() {
        return jugadorEnCuestion;
    }

    public void setJugadorEnCuestion(Jugador jugadorEnCuestion) {
        this.jugadorEnCuestion = jugadorEnCuestion;
    }

    public Double getMontoDeOperacion() {
        return montoDeOperacion;
    }

    public void setMontoDeOperacion(Double montoDeOperacion) {
        this.montoDeOperacion = montoDeOperacion;
    }

    public void buscarJugadores() {

        if ("".equals(nombreJugador)) {
            if ("".equals(nombreClub)) {
                resultado = logicaMercado.buscarJugadoresPorNombre("");
            } else {
                resultado = logicaMercado.buscarJugadoresPorClub(nombreClub);
            }
        } else {
            nombreClub = "";
            resultado = logicaMercado.buscarJugadoresPorNombre(nombreJugador);
        }

    }

    public List<Jugador> getResultado() {
        return resultado;
    }

    public void setResultado(List<Jugador> resultado) {
        this.resultado = resultado;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

//    public String realizarOferta(Club origen, Jugador jugador) {
//
//        if (jugador.getClub() == null) {
//            return "jugadoresLibres";
//        }
//        if (logicaMercado.ofertar(jugador, origen, this.getMontoDeOperacion(), Oferta.VENTA)) {
//
//            montoDeOperacion = 0D;
//            this.addMessage("La oferta fue enviada", "");
//
//            return "";
//        }
//        this.addMessage("La oferta no supero la validacion del sistema", "");
//        return "";
//
//    }
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
