/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.proyectofinal.controller;

import ar.proyectofinal.logica.LogicaMercado;
import entidades.Club;
import entidades.Jugador;
import entidades.Notificacion;
import entidades.Oferta;
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

    private String nombreJugador;
    private List<Jugador> resultado;
    private Double montoDeOperacion;

    public Double getMontoDeOperacion() {
        return montoDeOperacion;
    }

    public void setMontoDeOperacion(Double montoDeOperacion) {
        this.montoDeOperacion = montoDeOperacion;
    }

    public void buscarJugadores() {
        resultado = logicaMercado.buscarJugadoresPorNombre(this.nombreJugador);
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

    public String realizarOferta(Club origen, Jugador jugador) {

        System.out.println("antes de oferta");

        String respuesta;

        respuesta = logicaMercado.ofertar(jugador, origen, this.getMontoDeOperacion(), Oferta.VENTA);
        Notificacion notificacion = new Notificacion();
        notificacion.setMensaje("Ha llegado una oferta por el jugador "+jugador.getNombre());
        notificacion.setLeida(false);
        jugador.getClub().getNotificaciones().add(notificacion);

        montoDeOperacion = 0D;
        this.addMessage("La oferta fue enviada", "");

        return respuesta;

    }

    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
