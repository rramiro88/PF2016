/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import entidades.Club;
import entidades.Jugador;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author alumno
 */
@Named
@SessionScoped
public class LogicaFinanzas implements java.io.Serializable{

    public Double calcularGastoMensual(Club club) {
       Double respuesta=0D;
       
        for (Jugador j : club.getPlantel()) {
            respuesta += j.getSalario();
        }
        
        
        return respuesta;
    }
    
}
