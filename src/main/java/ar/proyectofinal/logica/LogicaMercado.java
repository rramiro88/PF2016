/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.proyectofinal.logica;

import dao.ClubDAO;
import dao.JugadorDAO;
import entidades.Club;
import entidades.Jugador;
import entidades.Oferta;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class LogicaMercado implements Serializable {

    public List<Jugador> listarLibres() {

        JugadorDAO jugadorDAO = new JugadorDAO();

        return jugadorDAO.listarLibres();

    }

    public boolean transferir(Jugador j, Club c, int condicion) {

        boolean respuesta = false;

        JugadorDAO jugadorDAO = new JugadorDAO();
        ClubDAO clubDAO = new ClubDAO();

        System.out.println("en logica!");
        j.setClub(c);
        c.agregarJugador(j);
        jugadorDAO.actualizarJugador(j);
        clubDAO.actualizarClub(c);

        return true;
    }

    public void liberarJugador(Jugador j, Club c) {

        JugadorDAO jugadorDAO = new JugadorDAO();
        ClubDAO clubDAO = new ClubDAO();
        j.setClub(null);
        c.getPlantel().remove(j);
        clubDAO.actualizarClub(c);
        jugadorDAO.actualizarJugador(j);

    }

    public List<Jugador> buscarJugadoresPorNombre(String nombreJugador) {
        JugadorDAO jugadorDAO = new JugadorDAO();
        return jugadorDAO.obtenerAlumnosPorNombre(nombreJugador);

    }

    public void ofertaDeCompra(Jugador jugador, Club oferente, Double monto, int condicion) {
        
        Club poseedor = jugador.getClub();
        Oferta oferta = new Oferta();
        oferta.setDestino(poseedor);
        oferta.setOrigen(oferente);
        oferta.setMontoDeOperacion(monto);
        oferta.setCondicion(condicion);
        
        ClubDAO clubDAO = new ClubDAO();
        clubDAO.actualizarClub(poseedor);
        clubDAO.actualizarClub(oferente);
        
        
        System.out.println("OFERTA DE COMPRA");
        
    }

}
