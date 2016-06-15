/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.proyectofinal.controller;

import ar.proyectofinal.logica.LogicaMercado;
import entidades.Jugador;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author alumno
 */

@Named
@SessionScoped
public class buscarJugadorController implements Serializable{
    @Inject
    LogicaMercado logicaMercado;
    
    private String nombreJugador;
    private List<Jugador> resultado;

    
    public void buscarJugadores(){
        resultado=logicaMercado.buscarJugadoresPorNombre(this.nombreJugador);
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
    
    
    
}
