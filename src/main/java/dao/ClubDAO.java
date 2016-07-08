/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Club;
import entidades.Estadio;
import entidades.Jugador;
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
        club.setPresupuesto(0F);
        club.setUrlEscudo("https://image.freepik.com/iconos-gratis/variante-escudo-con-bordes-blancos-y-negros_318-48076.png");


        
        Estadio estadio = new Estadio();
        club.setEstadio(estadio);
        
        JugadorDAO jugadorDAO = new JugadorDAO();
        List<Jugador> jugadoresIniciales = jugadorDAO.crearJugadoresAlAzarLista();
        club.setPlantel(jugadoresIniciales);
        
        for (Jugador jugador : jugadoresIniciales) {
            jugador.setClub(club);
        }
        
        Tactica tactica = new Tactica();
        tactica.setNombre("Liga");
        tactica.setTitulares(jugadoresIniciales.subList(0, 11));
        tactica.setSuplentes(jugadoresIniciales.subList(11, jugadoresIniciales.size()));
        tactica.setPosiciones(new ArrayList<String>());

        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();

        try {

            s.save(estadio);
            s.save(tactica);
            
            for (Jugador j : jugadoresIniciales) {
                s.saveOrUpdate(j);
            }

            s.save(club);
            s.getTransaction().commit();
            

        } catch (Exception ex) {
            s.getTransaction().rollback();
            ex.printStackTrace();
        }finally{
            s.close();
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
            

        } catch (Exception ex) {
            s.getTransaction().rollback();
            ex.printStackTrace();
        }finally{
            s.close();
        }
    }

    public List<Club> obtenerClubesPorNombre(String nombreClub) {
        List<Club> respuesta;

        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();

        

        Query consulta = s.createQuery("From Club where nombre like :parametro");
        consulta.setParameter("parametro","%"+ nombreClub +"%" );
        
        respuesta = consulta.list();
        
        for (Club c : respuesta) {
            System.out.println(c.getTacticas().size());
            System.out.println(c.getTacticas().get(0).getTitulares().size());
        }
        
        
//        Criteria c = s.createCriteria(Alumno.class)
//                .add(Restrictions.like("nombreYApellido", nombre, MatchMode.START));
//        respuesta = c.list();

        

        s.close();

        return respuesta;
    }

}
