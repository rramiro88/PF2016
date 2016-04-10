/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Jugador;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import utilidades.CargadorDB;

/**
 *
 * @author ramiro
 */
public class JugadorDAO {

    public List<Jugador> listarLibres() {
        List<Jugador> lista;

        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();

        lista = s.createCriteria(Jugador.class).add(Restrictions.isNull("club")).list();

        s.close();

        return lista;

    }

    public boolean crearJugador(Jugador j) {
        boolean respuesta = true;

        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();

        try {
            s.save(j);
            s.getTransaction().commit();
            s.close();
        } catch (Exception e) {
            respuesta = false;
            s.getTransaction().rollback();
            System.out.println("ROLLBACK EN TRANSACCION");

        }

        return respuesta;
    }

    public boolean actualizarJugador(Jugador j) {
        
        
        System.out.println("en actualizar jugador DAO");
        boolean respuesta = true;

        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();

        try {
            s.update(j);
            System.out.println("update exitoso! DAO");
            s.getTransaction().commit();
            s.close();
        } catch (Exception e) {
            respuesta = false;
            s.getTransaction().rollback();

        }

        return respuesta;
    }
    
    
    public void crearJugadoresAlAzar(){
        CargadorDB c = new CargadorDB();
        
       ArrayList<String> nombres = c.cargarJugadores();
       Jugador jugador = null;
       
       
       SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();
       
        for (String nombre : nombres) {
            
            jugador = new Jugador();
            jugador.setNombre(nombre);
            crearJugador(jugador);
        }
       
       
    }

}
