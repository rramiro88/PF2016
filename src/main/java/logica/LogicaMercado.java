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
import entidades.Prestamo;
import entidades.TransaccionEconomica;
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
    
    @Inject
    JugadorDAO jugadorDAO;
    
    @Inject
    ClubDAO clubDAO;
    
    @Inject
    OfertaDAO ofertaDAO;

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

        

        return jugadorDAO.listarLibres();

    }

    /**
     *
     * @param oferta: contiene las condiciones propuestas
     * @return si tuvo exito o no
     */
    public boolean transferir(Oferta oferta) {


        
        
         //Obtengo el club completo desde la BD, con las collections que me hacen falta
        oferta.setOrigen(clubDAO.obtenerClubPorId(oferta.getOrigen().getId()));

        System.out.println("en logica!");

        if (!validarCondiciones(oferta)) {
            return false;
        }

        switch (oferta.getCondicion()) {
            case Oferta.VENTA:
                //Seteo el nuevo club del jugador
                oferta.getJugadorObjetivo().setClub(oferta.getOrigen());
                //Agrego el jugador al club
                oferta.getOrigen().agregarJugador(oferta.getJugadorObjetivo());
                //Quito al jugador del club anterior
                oferta.getDestino().getPlantel().remove(oferta.getJugadorObjetivo());
                //Agrego las notificaciones
                oferta.getDestino().agregarNotificacion("El jugador " + oferta.getJugadorObjetivo().getNombre() + " ha sido transferido a " + oferta.getOrigen().getNombre());
                oferta.getOrigen().agregarNotificacion("El club " + oferta.getDestino().getNombre() + " ha aceptado la oferta por " + oferta.getJugadorObjetivo().getNombre() + ". El jugador se incorpora a tu plantel. El gasto total fue de $ " + oferta.getMontoDeOperacion());

                break;
            case Oferta.PRESTAMO:
                programarPrestamo(oferta);
                
                oferta.getOrigen().agregarNotificacion("El club " + oferta.getDestino().getNombre() + " ha aceptado ceder a prestamo"
                        + "al jugador " + oferta.getJugadorObjetivo().getNombre() +". Se incorporará a tu plantel a partir de la fecha"
                        + oferta.getDesde() + ". El costo total de la operacion es de $"+oferta.getMontoDeOperacion());
                
                oferta.getDestino().agregarNotificacion("El jugador " + oferta.getJugadorObjetivo().getNombre() + " se marchará a prestamo a partir del dia "+ oferta.getDesde() +
                        " hasta el dia " + oferta.getHasta());
                
                break;
        }

        //Acomodo las tacticas en las que participaba el jugador
        logicaTactica.reorganizarTacticas(oferta);

        //Transfiero los fondos
        oferta.getOrigen().setPresupuesto(oferta.getOrigen().getPresupuesto() - oferta.getMontoDeOperacion());
        oferta.getDestino().setPresupuesto(oferta.getDestino().getPresupuesto() + oferta.getMontoDeOperacion());
        
        
       
        
        //Agrego la transaccion economica correspondiente en la contraparte
        oferta.getOrigen().getTransacciones().add(new TransaccionEconomica(oferta.getCondicion().toUpperCase()+". Pago por transferencia del jugador "+oferta.getJugadorObjetivo().getNombre()+" al club "+oferta.getDestino().getNombre(), -oferta.getMontoDeOperacion(), new Date()));
        
        //Agrego la transaccion en el club actual
        oferta.getDestino().getTransacciones().add(new TransaccionEconomica(oferta.getCondicion().toUpperCase()+". Cobro por transferencia del jugador "+oferta.getJugadorObjetivo().getNombre()+" al club "+oferta.getOrigen().getNombre(), oferta.getMontoDeOperacion(), new Date()));
        
        //Persisto los clubes y jugador
        jugadorDAO.actualizarJugador(oferta.getJugadorObjetivo());

        clubDAO.actualizarClub(oferta.getOrigen());
        clubDAO.actualizarClub(oferta.getDestino());

        //Elimino la oferta
        ofertaDAO.eliminarOferta(oferta);

        return true;
    }

    public boolean liberarJugador(Jugador j, Club c) {
        
        if(c.getPlantel().size()<=MIN_PLANTEL){
            return false;
        }

        for (Prestamo p : c.getPrestamos() ) {
            if(p.getJugador().getId().equals(j.getId())){
                return false;
            }
        }
        
        c.getPlantel().remove(j);
        c.getTacticas().get(0).quitarJugadorDeTactica(j.getId());
        
        j.setClub(null);
        
        c.getTransacciones().add(new TransaccionEconomica("Libertad de accion del jugador "+j.getNombre(), -j.getSalario()*12, new Date()));
        c.setPresupuesto(c.getPresupuesto()-(j.getSalario()*12));
        
        
        
        
        
        jugadorDAO.actualizarJugador(j);
        clubDAO.actualizarClub(c);
        
        
        return true;

    }

    public List<Jugador> buscarJugadoresPorNombre(String nombreJugador) {
        
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

        

        ofertaDAO.crearOferta(oferta);
        clubDAO.actualizarClub(poseedor);
        clubDAO.actualizarClub(oferente);

        System.out.println("OFERTA DE COMPRA - LogicaMercado");

        return true;

    }

    public boolean transferirLibre(Jugador j, Club c, int condicion) {

        boolean respuesta = false;

        
        

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

        oferta.getOrigen().agregarNotificacion("la oferta por " + oferta.getJugadorObjetivo().getNombre() + " ha sido rechazada por el club " + oferta.getDestino().getNombre());

        
        clubDAO.actualizarClub(oferta.getOrigen());

        
        ofertaDAO.eliminarOferta(oferta);
    }

    private boolean validarCondiciones(Oferta oferta) {
        boolean respuesta = false;

        if (oferta.getMontoDeOperacion() <= oferta.getOrigen().getPresupuesto()
                && oferta.getDestino().getPlantel().size() > MIN_PLANTEL
                && oferta.getOrigen().getPlantel().size() < MAX_PLANTEL) {
            respuesta = true;
        }

        if (oferta.getCondicion().equals(Oferta.PRESTAMO)) {
            if (oferta.getDesde() == null
                    || oferta.getHasta() == null
                    || oferta.getDesde().before(new Date())
                    || oferta.getHasta().before(new Date())) {
                return false;
            }
        }
       
        for (Oferta enviada : oferta.getOrigen().getOfertasEnviadas() ) {
            if(enviada.getJugadorObjetivo().getId() == oferta.getJugadorObjetivo().getId()){
                return false;
            }
        }

        return respuesta;
    }

    public List<Jugador> buscarJugadoresPorClub(String nombreClub) {
     
        return clubDAO.obtenerJugadoresPorNombreDeClub(nombreClub);
    }

    private void programarPrestamo(Oferta oferta) {

        Prestamo prestamo = new Prestamo();

        prestamo.setClubOriginal(oferta.getDestino());
        prestamo.setDesde(oferta.getDesde());
        prestamo.setHasta(oferta.getHasta());
        prestamo.setJugador(oferta.getJugadorObjetivo());
        oferta.getOrigen().getPrestamos().add(prestamo);

//        ClubDAO clubDAO = new ClubDAO();
//
//        Club clubOrigen = clubDAO.obtenerClubPorId(oferta.getOrigen().getId());
//        clubOrigen.getPrestamos().add(prestamo);
//        clubDAO.actualizarClub(clubOrigen);

    }

    void devolverJugador(Prestamo prestamo, Club club) {
        //agregar jugador nuevamente al club dueño del pase
        prestamo.getClubOriginal().agregarJugador(prestamo.getJugador());
        prestamo.getJugador().setClub(prestamo.getClubOriginal());
        
        
        //asignarle un numero de camiseta libre en el club dueño del pase
        prestamo.getJugador().setNumeroCamiseta(prestamo.getClubOriginal().getNumerosLibres().get(0));
        
        //quitarlo de las tacticas del club en el que estuvo a prestamo
        club.getTacticas().get(0).quitarJugadorDeTactica(prestamo.getJugador().getId());

        System.out.println("JUGADOR DEVUELTO ------------------------------------------");
        
        //agregar notificaciones
        club.agregarNotificacion("El jugador "+prestamo.getJugador().getNombre() + " finalizo su prestamo y vuelve a su club original.");
        prestamo.getClubOriginal().agregarNotificacion("El jugador "+prestamo.getJugador().getNombre() + "finalizo su prestamo y se reintegra a tu plantel");
    }

    void prestarJugador(Prestamo prestamo, Club club) {
        
        
        
        
        //agregarlo al plantel del club que lo lleva a prestamo
        club.agregarJugador(prestamo.getJugador());
        prestamo.getJugador().setClub(club);
        prestamo.getClubOriginal().getPlantel().remove(prestamo.getJugador());
        
        
        
        //asignarle un numero libre de camiseta
        prestamo.getJugador().setNumeroCamiseta(club.getNumerosLibres().get(0));
        
        //quitar al jugador de las tacticas en las que participa en el club dueño del pase
        prestamo.getClubOriginal().getTacticas().get(0).quitarJugadorDeTactica(prestamo.getJugador().getId());
        
        
        
        
        //agregar las notificaciones
        club.agregarNotificacion("Se inicia el prestamo del jugador " + prestamo.getJugador().getNombre() + ". Finaliza el dia " + prestamo.getHasta());
        prestamo.getClubOriginal().agregarNotificacion("El jugador "+prestamo.getJugador().getNombre() + "comienza su prestamo y se marcha a "+club.getNombre()+". Retorna el dia "+prestamo.getHasta());
        System.out.println("JUGADOR INICIA PRESTAMO ----------------------------------");
        
        
        jugadorDAO.actualizarJugador(prestamo.getJugador());
        
       
        clubDAO.actualizarClub(prestamo.getClubOriginal());
    }

}
