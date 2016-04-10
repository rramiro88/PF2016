/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.proyectofinal.logica;

import dao.JugadorDAO;
import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

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
    
    
    
}
