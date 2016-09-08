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
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
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
        club.setPresupuesto(1000000D);
        club.setUrlEscudo("https://image.freepik.com/iconos-gratis/variante-escudo-con-bordes-blancos-y-negros_318-48076.png");

        Estadio estadio = new Estadio();
        club.setEstadio(estadio);

        JugadorDAO jugadorDAO = new JugadorDAO();
        List<Jugador> jugadoresIniciales = jugadorDAO.crearJugadoresAlAzarLista();
        
        
        List<Integer> numerosLibres = club.getNumerosLibres();
       
        for (int i = 0; i < jugadoresIniciales.size(); i++) {
            jugadoresIniciales.get(i).setNumeroCamiseta(numerosLibres.get(i));
        }
        
        
        
        club.setPlantel(jugadoresIniciales);

        for (Jugador jugador : jugadoresIniciales) {
            jugador.setClub(club);
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

        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();

        try {

//            s.save(estadio);
            for (Jugador j : jugadoresIniciales) {
                s.saveOrUpdate(j);
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
            

//            s.save(tactica);
            s.persist(club);

            s.getTransaction().commit();

        } catch (Exception ex) {
            s.getTransaction().rollback();
            ex.printStackTrace();
        } finally {
            s.close();
        }

        return club;
    }

    public void actualizarClub(Club club) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();

        try {

            s.saveOrUpdate(club);
            s.getTransaction().commit();

        } catch (Exception ex) {
            s.getTransaction().rollback();
            ex.printStackTrace();
        } finally {
            s.close();
        }
    }

    public List<Club> obtenerClubesPorNombre(String nombreClub) {
        List<Club> respuesta;

        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();

        Query consulta = s.createQuery("From Club where nombre like :parametro");
        consulta.setParameter("parametro", "%" + nombreClub + "%");

        respuesta = consulta.list();

        for (Club c : respuesta) {
            System.out.println(c.getTacticas().size());

        }

//        Criteria c = s.createCriteria(Alumno.class)
//                .add(Restrictions.like("nombreYApellido", nombre, MatchMode.START));
//        respuesta = c.list();
        s.close();

        return respuesta;
    }

    public void actualizarNotificaciones(List<Notificacion> notificaciones) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();
        
        for (Notificacion notificacion : notificaciones) {
            s.saveOrUpdate(notificacion);
        }
        
        s.getTransaction().commit();
        
        s.close();
    }
    
    public List<Jugador> obtenerJugadoresPorNombreDeClub(String nombreClub) {
        List<Object[]> resultado;
        List<Jugador> respuesta = new ArrayList<>();

        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();

        Query consulta = s.createQuery("From Jugador j inner join j.club where j.club.nombre like :parametro");
        consulta.setParameter("parametro", "%" + nombreClub + "%");

        resultado = consulta.list();

        

        s.close();
        
        for ( Object[] r : resultado) {
            
            respuesta.add((Jugador)r[0]);
            
        }

        return respuesta;
    }

}
