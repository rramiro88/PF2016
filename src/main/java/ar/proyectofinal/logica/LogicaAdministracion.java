/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.proyectofinal.logica;

import dao.JugadorDAO;
import dao.PartidosDAO;
import entidades.Partido;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import utilidades.Simulador;

/**
 *
 * @author ramiro
 */

@Named
@ApplicationScoped
public class LogicaAdministracion implements Serializable{
    
    public void cargarJugadoresDB(){
        
        JugadorDAO jugadorDAO = new JugadorDAO();
        
        
        jugadorDAO.crearJugadoresAlAzar();
        
        
    }
    
    public void avanzarUnDia(){
        
        
        PartidosDAO partidosDAO = new PartidosDAO();
        
        List<Partido> partidosASimular = partidosDAO.obtenerPartidosDeHoy();
        int diferenciaGoles;
        for (Partido p : partidosASimular) {
            
            diferenciaGoles = Simulador.simular(p.getLocal().getTacticas().get(0).getTitulares(), p.getVisitante().getTacticas().get(0).getTitulares());
            
            if(diferenciaGoles > 0 ){
                p.setGolesLocal(diferenciaGoles);
                p.setGolesVisitantes(0);
            }else if(diferenciaGoles < 0 ){
                p.setGolesLocal(0);
                p.setGolesVisitantes(diferenciaGoles);
            }else{
                p.setGolesLocal(0);
                p.setGolesVisitantes(0);
            }
            
            partidosDAO.actualizarPartido(p);
            
        }
        
        
        
        
    }
    
    
    
}
