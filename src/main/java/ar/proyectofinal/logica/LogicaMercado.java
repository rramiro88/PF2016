/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.proyectofinal.logica;

import dao.JugadorDAO;
import entidades.Club;
import entidades.Jugador;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


@Named
@SessionScoped
public class LogicaMercado implements Serializable{
    
    
    public List<Jugador> listarLibres(){
        
        JugadorDAO jugadorDAO = new JugadorDAO();
        
        return jugadorDAO.listarLibres();
        
        
        
    }

    public boolean transferir(Jugador j, Club c, int condicion) {
       
        boolean respuesta = false;
        
        JugadorDAO jugadorDAO = new JugadorDAO();
        
        System.out.println("en logica!");
        j.setClub(c);
        jugadorDAO.actualizarJugador(j);
        
        
        return true;
    }

    
    
    
    
}
