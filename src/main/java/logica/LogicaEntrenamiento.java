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
                    if(jugador.getProgresoArquero()>99 && jugador.getArquero()<10){
                        jugador.setArquero(jugador.getArquero()+1);
                    }
                    break;
                }case Jugador.CABEZA:{
                    jugador.setProgresoCabezazo(jugador.getProgresoCabezazo()+1);
                    if(jugador.getProgresoCabezazo()>99 && jugador.getCabezazo()<10){
                        jugador.setCabezazo(jugador.getCabezazo()+1);
                    }
                    break;
                }
                case Jugador.DEFENSA:{
                    jugador.setProgresoEntradas(jugador.getProgresoEntradas()+1);
                    jugador.setProgresoMarca(jugador.getProgresoMarca() +1);
                    if(jugador.getProgresoMarca()>99 && jugador.getMarca()<10){
                        jugador.setMarca(jugador.getMarca()+1);
                    }
                    if(jugador.getProgresoEntradas()>99 && jugador.getEntradas()<10){
                        jugador.setEntradas(jugador.getEntradas()+1);
                    }
                    break;
                }case Jugador.DESCANSO:{
                    break;
                }case Jugador.MENTALIDAD:{
                    jugador.setProgresoMentalidad(jugador.getProgresoMentalidad() +1);
                    if(jugador.getProgresoMentalidad()>99 && jugador.getMentalidad()<10){
                        jugador.setMentalidad(jugador.getMentalidad()+1);
                    }
                    break;
                }case Jugador.PASES_CORTOS:{
                    jugador.setProgresoPases(jugador.getProgresoPases() +1);
                    if(jugador.getProgresoPases()>99 && jugador.getPasesCortos()<10){
                        jugador.setPasesCortos(jugador.getPasesCortos()+1);
                    }
                    break;
                }case Jugador.PASES_LARGOS:{
                    jugador.setProgresoPasesLargos(jugador.getProgresoPasesLargos()+1);
                    if(jugador.getProgresoPasesLargos()>99 && jugador.getPasesLargos()<10){
                        jugador.setPasesLargos(jugador.getPasesLargos()+1);
                    }
                    break;
                }case Jugador.REMATES:{
                    jugador.setProgresoPotenciaTiro(jugador.getProgresoPotenciaTiro() +1);
                    jugador.setProgresoPrecisionTiro(jugador.getProgresoPrecisionTiro() +1);
                    
                    if(jugador.getProgresoPotenciaTiro()>99 && jugador.getPotenciaTiro()<10){
                        jugador.setPotenciaTiro(jugador.getPotenciaTiro()+1);
                    }
                    if(jugador.getProgresoPrecisionTiro()>99 && jugador.getPrecisionTiro()<10){
                        jugador.setPrecisionTiro(jugador.getPrecisionTiro()+1);
                    }
                    
                    break;
                }case Jugador.RESISTENCIA:{
                    jugador.setProgresoResistencia(jugador.getProgresoResistencia() +1);
                    
                    if(jugador.getProgresoResistencia()>99 && jugador.getResistencia()<10){
                        jugador.setResistencia(jugador.getResistencia()+1);
                    }
                    break;
                }case Jugador.VELOCIDAD:{
                    jugador.setProgresoVelocidad(jugador.getProgresoVelocidad()+1);
                    if(jugador.getProgresoVelocidad()>99 && jugador.getVelocidad()<10){
                        jugador.setVelocidad(jugador.getVelocidad()+1);
                    }
                    break;
                }case Jugador.REGATE:{
                    jugador.setProgresoRegate(jugador.getProgresoRegate()+1);
                    if(jugador.getProgresoRegate()>99 && jugador.getRegate()<10){
                        jugador.setRegate(jugador.getRegate()+1);
                    }
                    break;
                }
                
                
                
                
            }
            
            
        }
        
        
        jugadores.get(0).getClub().agregarNotificacion("Tu equipo ha entrenado. Puedes revisar los progresos en el reporte de entrenamiento");
        
    }
    
}
