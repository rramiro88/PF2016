/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Club;
import entidades.Estadio;
import entidades.Jugador;
import entidades.Notificacion;
import entidades.Tactica;
import entidades.TransaccionEconomica;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ramiro
 */
@Stateless
public class ClubDAO {

    @PersistenceContext
    EntityManager em;

    @Inject
    JugadorDAO jugadorDAO;

    public Club crearClub(String nombreClub) {
        Club club = new Club();
        club.setNombre(nombreClub);
        club.setPresupuesto(1000000D);
        club.setUrlEscudo("https://image.freepik.com/iconos-gratis/variante-escudo-con-bordes-blancos-y-negros_318-48076.png");

        Estadio estadio = new Estadio();
        club.setEstadio(estadio);

        List<Jugador> jugadoresIniciales = jugadorDAO.crearJugadoresAlAzarLista();

        List<Integer> numerosLibres = club.getNumerosLibres();

        for (int i = 0; i < jugadoresIniciales.size(); i++) {
            jugadoresIniciales.get(i).setNumeroCamiseta(numerosLibres.get(i));
        }

        club.setPlantel(jugadoresIniciales);

        for (Jugador jugador : jugadoresIniciales) {
            jugador.setClub(club);
            jugador.setEntrenando(Jugador.PASES_CORTOS);
        }

        Tactica tactica = new Tactica();
        tactica.setNombre("Liga");
        tactica.setClub(club);

        ArrayList<String> posiciones = new ArrayList<>();
        posiciones.add("100a100");
        posiciones.add("100a100");
        posiciones.add("100a100");
        posiciones.add("100a100");
        posiciones.add("100a100");
        posiciones.add("100a100");
        posiciones.add("100a100");
        posiciones.add("100a100");
        posiciones.add("100a100");
        posiciones.add("100a100");
        posiciones.add("100a100");

        tactica.setPosiciones(posiciones);

        club.getTacticas().add(tactica);

//            s.save(estadio);
        for (Jugador j : jugadoresIniciales) {
            em.persist(j);
        }

        tactica.getPosicionesEnCancha().put(jugadoresIniciales.get(0).getId(), Tactica.ARQUERO);
        tactica.getPosicionesEnCancha().put(jugadoresIniciales.get(1).getId(), Tactica.LATERAL_DERECHO);
        tactica.getPosicionesEnCancha().put(jugadoresIniciales.get(2).getId(), Tactica.DEFENSA_CENTRAL1);
        tactica.getPosicionesEnCancha().put(jugadoresIniciales.get(3).getId(), Tactica.DEFENSA_CENTRAL2);
        tactica.getPosicionesEnCancha().put(jugadoresIniciales.get(4).getId(), Tactica.LATERAL_IZQUIERDO);
        tactica.getPosicionesEnCancha().put(jugadoresIniciales.get(5).getId(), Tactica.MEDIO_CENTRO1);
        tactica.getPosicionesEnCancha().put(jugadoresIniciales.get(6).getId(), Tactica.MEDIO_DERECHO);
        tactica.getPosicionesEnCancha().put(jugadoresIniciales.get(7).getId(), Tactica.MEDIO_IZQUIERDO);
        tactica.getPosicionesEnCancha().put(jugadoresIniciales.get(8).getId(), Tactica.MEDIAPUNTA);
        tactica.getPosicionesEnCancha().put(jugadoresIniciales.get(9).getId(), Tactica.DELANTERO_CENTRO1);
        tactica.getPosicionesEnCancha().put(jugadoresIniciales.get(10).getId(), Tactica.DELANTERO_CENTRO2);

        tactica.getPosicionesEnCancha().put(jugadoresIniciales.get(11).getId(), Tactica.SUPLENTE1);
        tactica.getPosicionesEnCancha().put(jugadoresIniciales.get(12).getId(), Tactica.SUPLENTE2);
        tactica.getPosicionesEnCancha().put(jugadoresIniciales.get(13).getId(), Tactica.SUPLENTE3);
        tactica.getPosicionesEnCancha().put(jugadoresIniciales.get(14).getId(), Tactica.SUPLENTE4);
        tactica.getPosicionesEnCancha().put(jugadoresIniciales.get(15).getId(), Tactica.SUPLENTE5);

//            s.save(tactica);


        club.getTransacciones().add(new TransaccionEconomica("Inicio", 0D, new Date()));
        em.persist(club);

        return club;
    }

    public void actualizarClub(Club club) {
        em.merge(club);
    }

    public List<Club> obtenerClubesPorNombre(String nombreClub) {
        List<Club> respuesta;

        Query consulta = em.createQuery("Select c From Club  c where c.nombre like :parametro", Club.class);
        consulta.setParameter("parametro", "%" + nombreClub + "%");

        respuesta = consulta.getResultList();

        for (Club c : respuesta) {
            System.out.println("ClubDAO.obtenerClubesPorNombre: TACTICAS-->" + c.getTacticas().size());
            System.out.println("ClubDAO.obtenerClubesPorNombre: PRESTAMOS-->" + c.getPrestamos().size());
            System.out.println("ClubDAO.obtenerClubesPorNombre: PARTIDOS LOCAL-->" + c.getPartidosLocal().size());
            System.out.println("ClubDAO.obtenerClubesPorNombre: PARTIDOS VISITANTE-->" + c.getPartidosVisitante().size());
            System.out.println("ClubDAO.obtenerClubesPorNombre: TRANSACCIONES-->" + c.getTransacciones().size());

        }

        return respuesta;
    }

    public void actualizarNotificaciones(List<Notificacion> notificaciones) {

        for (Notificacion notificacion : notificaciones) {
            em.persist(notificacion);
        }

    }

    public List<Jugador> obtenerJugadoresPorNombreDeClub(String nombreClub) {
        List<Object[]> resultado;
        List<Jugador> respuesta = new ArrayList<>();

        Query consulta = em.createQuery("select j From Jugador j inner join j.club c where j.club.nombre like :parametro", Jugador.class);
        consulta.setParameter("parametro", "%" + nombreClub + "%");

        respuesta = consulta.getResultList();

        return respuesta;
    }

    public void inicializarPrestamos(Club origen) {

        em.refresh(origen.getPrestamos());

    }

    public Club obtenerClubPorId(long id) {

        Club c = em.find(Club.class, id);
        System.out.println(c.getPrestamos().size());
        System.out.println(c.getTransacciones().size());
        System.out.println(c.getPartidosLocal().size());
        System.out.println(c.getPartidosVisitante().size());
        return c;

    }

}
