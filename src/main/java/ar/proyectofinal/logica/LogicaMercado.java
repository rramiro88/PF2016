/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.proyectofinal.logica;

import dao.ClubDAO;
import dao.JugadorDAO;
import dao.OfertaDAO;
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

    private Oferta ofertaEnCuestion;

    public Oferta getOfertaEnCuestion() {
        return ofertaEnCuestion;
    }

    public void setOfertaEnCuestion(Oferta ofertaEnCuestion) {
        this.ofertaEnCuestion = ofertaEnCuestion;
    }
    
    
    
    public List<Jugador> listarLibres() {

        JugadorDAO jugadorDAO = new JugadorDAO();

        return jugadorDAO.listarLibres();

    }

    public boolean transferir(Oferta oferta) {

        boolean respuesta = false;

        JugadorDAO jugadorDAO = new JugadorDAO();
        ClubDAO clubDAO = new ClubDAO();
        OfertaDAO ofertaDAO = new OfertaDAO();

        System.out.println("en logica!");
        
        
        
        
        
        oferta.getJugadorObjetivo().setClub(oferta.getOrigen());
        oferta.getOrigen().agregarJugador(oferta.getJugadorObjetivo());
        
        oferta.getDestino().getPlantel().remove(oferta.getJugadorObjetivo());
        
        
        
        
        jugadorDAO.actualizarJugador(oferta.getJugadorObjetivo());
        clubDAO.actualizarClub(oferta.getOrigen());
        clubDAO.actualizarClub(oferta.getDestino());
        ofertaDAO.eliminarOferta(oferta);

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
        return jugadorDAO.obtenerJugadorPorNombre(nombreJugador);

    }

    
    /**
     * 
     * @param jugador jugador en cuestion
     * @param oferente club que realiza la oferta
     * @param monto cantidad de dinero ficticio ofrecido
     * @param condicion Venta o Prestamo
     * @return 
     */
    public String ofertar(Jugador jugador, Club oferente, Double monto, int condicion) {
        

        Club poseedor = jugador.getClub();

        
        if(poseedor == null){
            return "jugadoresLibres";
        }
        
        Oferta oferta = new Oferta();   
        oferta.setDestino(poseedor);
        oferta.setOrigen(oferente);
        oferta.setMontoDeOperacion(monto);
        oferta.setCondicion(condicion);
        oferta.setJugadorObjetivo(jugador);
        
        ClubDAO clubDAO = new ClubDAO();
        OfertaDAO ofertaDAO = new OfertaDAO();
        
        ofertaDAO.crearOferta(oferta);
        clubDAO.actualizarClub(poseedor);
        clubDAO.actualizarClub(oferente);
        
        
        System.out.println("OFERTA DE COMPRA - LogicaMercado");
        
        return "";
        
    }

    public boolean transferirLibre(Jugador j, Club c, int condicion) {
        
        
        
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

    public void rechazarOferta(Oferta oferta) {
       /* oferta.getOrigen().getOfertasEnviadas().remove(oferta);
        oferta.getDestino().getOfertasRecibidas().remove(oferta);*/
        
        OfertaDAO ofertaDAO = new OfertaDAO();
        ofertaDAO.eliminarOferta(oferta);
    }

}
