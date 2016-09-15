/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import dao.ClubDAO;
import dao.JugadorDAO;
import dao.OfertaDAO;
import entidades.Club;
import entidades.Jugador;
import entidades.Oferta;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class LogicaMercado implements Serializable {
    
    @Inject
    LogicaTactica logicaTactica;

    private final int MIN_PLANTEL = 11;
    private final int MAX_PLANTEL = 30;
    
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

    /**
     *
     * @param oferta: contiene las condiciones propuestas
     * @return si tuvo exito o no
     */
    public boolean transferir(Oferta oferta) {



        JugadorDAO jugadorDAO = new JugadorDAO();
        ClubDAO clubDAO = new ClubDAO();
        OfertaDAO ofertaDAO = new OfertaDAO();

        System.out.println("en logica!");

        if (!validarCondiciones(oferta)) {
            return false;
        }

        //Seteo el nuevo club del jugador
        oferta.getJugadorObjetivo().setClub(oferta.getOrigen());
        //Agrego el jugador al club
        oferta.getOrigen().agregarJugador(oferta.getJugadorObjetivo());
        //Quito al jugador del club anterior
        oferta.getDestino().getPlantel().remove(oferta.getJugadorObjetivo());
        
        //Acomodo las tacticas en las que participaba el jugador
        logicaTactica.reorganizarTacticas(oferta);
        
        //Transfiero los fondos
        oferta.getOrigen().setPresupuesto(oferta.getOrigen().getPresupuesto() - oferta.getMontoDeOperacion());
        oferta.getDestino().setPresupuesto(oferta.getDestino().getPresupuesto() + oferta.getMontoDeOperacion());

        //Agrego las notificaciones
        oferta.getDestino().agregarNotificacion("El jugador " + oferta.getJugadorObjetivo().getNombre() + " ha sido transferido a " + oferta.getOrigen().getNombre());
        oferta.getOrigen().agregarNotificacion("El club " + oferta.getDestino().getNombre() + " ha aceptado la oferta por " + oferta.getJugadorObjetivo().getNombre() + ". El jugador se incorpora a tu plantel. El gasto total fue de $ " + oferta.getMontoDeOperacion());



        //Persisto los clubes y jugador
        jugadorDAO.actualizarJugador(oferta.getJugadorObjetivo());
        clubDAO.actualizarClub(oferta.getOrigen());
        clubDAO.actualizarClub(oferta.getDestino());
        
        //Elimino la oferta
        
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
     * @param porcentaje Porcentaje del pase que se ofrece
     * @param desde inicio del prestamo
     * @param hasta fin del prestamo
     * @return true si tuvo exito, false si no pasa la validacion
     */
    public boolean ofertar(Jugador jugador, Club oferente, Double monto, String condicion, Double porcentaje, Date desde, Date hasta) {

        Club poseedor = jugador.getClub();

        Oferta oferta = new Oferta();
        oferta.setDestino(poseedor);
        oferta.setOrigen(oferente);
        oferta.setMontoDeOperacion(monto);
        oferta.setCondicion(condicion);
        oferta.setJugadorObjetivo(jugador);
        oferta.setPorcentaje(porcentaje);
        oferta.setDesde(desde);
        oferta.setHasta(hasta);

        if (!validarCondiciones(oferta)) {

            return false;
        }

        poseedor.agregarNotificacion("Ha llegado una oferta por el jugador " + jugador.getNombre());

        ClubDAO clubDAO = new ClubDAO();
        OfertaDAO ofertaDAO = new OfertaDAO();

        ofertaDAO.crearOferta(oferta);
        clubDAO.actualizarClub(poseedor);
        clubDAO.actualizarClub(oferente);

        System.out.println("OFERTA DE COMPRA - LogicaMercado");

        return true;

    }

    public boolean transferirLibre(Jugador j, Club c, int condicion) {

        boolean respuesta = false;

        JugadorDAO jugadorDAO = new JugadorDAO();
        ClubDAO clubDAO = new ClubDAO();

        System.out.println("en logica!");

        j.setClub(c);
        j.setNumeroCamiseta(c.getNumerosLibres().get(0));
        c.agregarJugador(j);

        jugadorDAO.actualizarJugador(j);
        clubDAO.actualizarClub(c);

        return true;
    }

    public void rechazarOferta(Oferta oferta) {
        /* oferta.getOrigen().getOfertasEnviadas().remove(oferta);
         oferta.getDestino().getOfertasRecibidas().remove(oferta);*/
        
        oferta.getOrigen().agregarNotificacion("la oferta por "+oferta.getJugadorObjetivo().getNombre()+" ha sido rechazada por el club "+oferta.getDestino().getNombre());
        
        ClubDAO clubDAO = new ClubDAO();
        clubDAO.actualizarClub(oferta.getOrigen());
        
        OfertaDAO ofertaDAO = new OfertaDAO();
        ofertaDAO.eliminarOferta(oferta);
    }

    private boolean validarCondiciones(Oferta oferta) {
        boolean respuesta = false;

        if (oferta.getMontoDeOperacion() <= oferta.getOrigen().getPresupuesto()
                && oferta.getDestino().getPlantel().size()>MIN_PLANTEL
                && oferta.getOrigen().getPlantel().size()<MAX_PLANTEL) {
            respuesta = true;
        }

        return respuesta;
    }

    public List<Jugador> buscarJugadoresPorClub(String nombreClub) {
        ClubDAO clubDAO = new ClubDAO();
        return clubDAO.obtenerJugadoresPorNombreDeClub(nombreClub);
    }

}
