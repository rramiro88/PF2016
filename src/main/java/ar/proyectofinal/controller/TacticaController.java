/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.proyectofinal.controller;


import ar.proyectofinal.logica.LogicaTactica;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author ramiro
 */

@Named
@SessionScoped
public class TacticaController implements Serializable{
    
    @Inject
    LogicaTactica logicaTactica;
    
    private String posicion;

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }
    
    public void guardarPosicion(){
        System.out.println("guardar posicion");
    }
    
    
    
}
