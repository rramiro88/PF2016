/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.proyectofinal.controller;

import ar.proyectofinal.logica.LogicaMercado;
import entidades.Club;
import entidades.Jugador;
import entidades.Oferta;
import java.io.Serializable;
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
public class BuscarJugadorController implements Serializable{
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
    
    
    public String realizarOferta(Club origen, Jugador jugador){
        
        System.out.println("antes de oferta");
        
        String respuesta;
        
        respuesta = logicaMercado.ofertar(jugador,origen,this.getMontoDeOperacion(),Oferta.VENTA);
        
        montoDeOperacion = 0D;
        
        return respuesta;
        
    }
    
    
    
}
