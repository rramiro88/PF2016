/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import entidades.Jugador;
import java.util.List;

/**
 *
 * @author alumno
 */
public class LogicaEntrenamiento {
    
    public void calcularProgresos(List<Jugador> jugadores){
        
        for (Jugador jugador : jugadores) {
            
            switch(jugador.getEntrenando()){
                
                case Jugador.ARQUERO:{
                    jugador.setProgresoArquero(jugador.getProgresoArquero()+1);
                    break;
                }case Jugador.CABEZA:{
                    jugador.setProgresoCabezazo(jugador.getProgresoCabezazo()+1);
                    break;
                }
                case Jugador.DEFENSA:{
                    jugador.setProgresoEntradas(jugador.getProgresoEntradas()+1);
                    jugador.setProgresoMarca(jugador.getProgresoMarca() +1);
                    break;
                }case Jugador.DESCANSO:{
                    break;
                }case Jugador.MENTALIDAD:{
                    jugador.setProgresoMentalidad(jugador.getProgresoMentalidad() +1);
                    break;
                }case Jugador.PASES_CORTOS:{
                    //agregar
                    break;
                }case Jugador.PASES_LARGOS:{
                    //agregar
                    break;
                }case Jugador.REMATES:{
                    jugador.setProgresoPotenciaTiro(jugador.getProgresoPotenciaTiro() +1);
                    jugador.setProgresoPrecisionTiro(jugador.getProgresoPrecisionTiro() +1);
                    break;
                }case Jugador.RESISTENCIA:{
                    jugador.setProgresoResistencia(jugador.getProgresoResistencia() +1);
                    break;
                }case Jugador.VELOCIDAD:{
                    jugador.setProgresoVelocidad(jugador.getProgresoVelocidad()+1);
                    break;
                }
                
                
                
            }
            
            
        }
        
        
    }
    
}
