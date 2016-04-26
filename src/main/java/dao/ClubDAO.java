/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Club;
import entidades.Estadio;
import entidades.Jugador;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author ramiro
 */
public class ClubDAO {

    public Club crearClub(String nombreClub) {
        Club club = new Club();
        club.setNombre(nombreClub);
        club.setPresupuesto(0F);
        club.setUrlEscudo("http://www.pubzi.com/f/md-Simple-shield.png");

//        Jugador jugador1 = new Jugador();
//        jugador1.setNombre("Leonardo Pisculichi");
//        club.agregarJugador(jugador1);
//        
//        Jugador jugador2 = new Jugador();
//        jugador2.setNombre("Lucas Alario");
//        club.agregarJugador(jugador2);
//        
//        Jugador jugador3 = new Jugador();
//        jugador3.setNombre("Leonardo Ponzio");
//        club.agregarJugador(jugador3);
//        
//        Jugador jugador4 = new Jugador();
//        jugador4.setNombre("Marcelo Barovero");
//        club.agregarJugador(jugador4);
//        
//        Jugador jugador5 = new Jugador();
//        jugador5.setNombre("Jonathan Maidana");
//        jugador4.setCotizacion(6000000.0);
//        club.agregarJugador(jugador5);
//        
//        Jugador jugador6 = new Jugador();
//        jugador6.setNombre("Rodrigo Mora");
//        club.agregarJugador(jugador6);
        
        Estadio estadio = new Estadio();
        club.setEstadio(estadio);

        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();

        try {

            s.save(estadio);
//            s.save(jugador1);
//            s.save(jugador2);
//            s.save(jugador3);
//            s.save(jugador4);
//            s.save(jugador5);
//            s.save(jugador6);
            s.save(club);
            s.getTransaction().commit();
            s.close();

        } catch (Exception ex) {
            s.getTransaction().rollback();
            ex.printStackTrace();
        }

        return club;
    }


    public void actualizarClub(Club club) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();

        try {

            s.update(club);
            s.getTransaction().commit();
            s.close();

        } catch (Exception ex) {
            s.getTransaction().rollback();
            ex.printStackTrace();
        }
    }

}
