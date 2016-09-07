/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.Date;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import logica.LogicaMercado;

/**
 *
 * @author alumno
 */
@Named
@RequestScoped
public class OfertaController {

    @Inject
    LogicaMercado logicaMercado;

    @Inject
    BuscarJugadorController buscarJugadorController;

    @Inject
    SesionController sesionController;

    String condicion;

    Date prestamoFechaDesde, prestamoFechaHasta;

    Double monto;

    Double porcentaje;

    public String enviarOferta() {

        if (buscarJugadorController.getJugadorEnCuestion().getClub() == null) {
            return "jugadoresLibres";
        }
        if (sesionController.getUsuarioLogueado().getClub().getId()==buscarJugadorController.getJugadorEnCuestion().getClub().getId()) {
            this.addMessage("No puede ofertar por un jugador propio", "");
        } else {
            if (logicaMercado.ofertar(buscarJugadorController.getJugadorEnCuestion(), sesionController.getUsuarioLogueado().getClub(), monto, condicion)) {

                this.addMessage("La oferta fue enviada", "");

                return "";
            }
            this.addMessage("La oferta no supero la validacion del sistema", "");
        }

        return "";
    }

    public Double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(Double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Date getPrestamoFechaDesde() {
        return prestamoFechaDesde;
    }

    public void setPrestamoFechaDesde(Date prestamoFechaDesde) {
        this.prestamoFechaDesde = prestamoFechaDesde;
    }

    public Date getPrestamoFechaHasta() {
        return prestamoFechaHasta;
    }

    public void setPrestamoFechaHasta(Date prestamoFechaHasta) {
        this.prestamoFechaHasta = prestamoFechaHasta;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
