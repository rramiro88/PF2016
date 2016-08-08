/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.proyectofinal.controller;

import ar.proyectofinal.logica.LogicaFinanzas;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author alumno
 */
@Named
@SessionScoped
public class FinanzasController implements Serializable{
    
    
    Double gastoMensual;
    
    Double presupuestoActual;

    @Inject
    LogicaFinanzas logicaFinanzas;
    
    @Inject
    SesionController sesionController;

    
    
    
    
    
    public Double getGastoMensual() {
        return gastoMensual;
    }

    public void setGastoMensual(Double gastoMensual) {
        this.gastoMensual = gastoMensual;
    }

    public Double getPresupuestoActual() {
        return presupuestoActual;
    }

    public void setPresupuestoActual(Double presupuestoActual) {
        this.presupuestoActual = presupuestoActual;
    }
    
    
    
    
    public Double calcularGastoMensual(){
        Double respuesta = logicaFinanzas.calcularGastoMensual(sesionController.getUsuarioLogueado().getClub());
        this.gastoMensual = respuesta;
        return respuesta;
    }
    
}
