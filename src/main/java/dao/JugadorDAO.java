/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Jugador;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.hibernate.Query;
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

        } catch (Exception e) {
            respuesta = false;
            s.getTransaction().rollback();
            System.out.println("ROLLBACK EN TRANSACCION");

        } finally {
            s.close();
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

        } catch (Exception e) {
            respuesta = false;
            s.getTransaction().rollback();

        } finally {
            s.close();
        }

        return respuesta;
    }

    public List<Jugador> crearJugadoresAlAzarLista() {

        List<Jugador> respuesta = new ArrayList<>();
        CargadorDB c = new CargadorDB();

        ArrayList<String> nombres = c.cargarJugadores();
        Jugador jugador = null;

        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();

        for (String nombre : nombres) {

            jugador = new Jugador();

            jugador.setNombre(nombre);
            jugador.setArquero(randInt(0, 10));
            jugador.setCabezazo(randInt(0, 10));
            jugador.setCotizacion(1.0 * randInt(0, 10000000));
            jugador.setEntradas(randInt(0, 10));
            jugador.setMarca(randInt(0, 10));
            jugador.setMentalidad(randInt(0, 10));
            jugador.setPelotaParada(randInt(0, 10));
            jugador.setPotenciaTiro(randInt(0, 10));
            jugador.setPrecisionTiro(randInt(0, 10));
            jugador.setRegate(randInt(0, 10));
            jugador.setResistencia(randInt(0, 10));
            jugador.setSalario(1.0 * randInt(2000, 100000));
            jugador.setVelocidad(randInt(0, 10));

            respuesta.add(jugador);
        }

        return respuesta;
    }

    public void crearJugadoresAlAzar() {
        CargadorDB c = new CargadorDB();

        ArrayList<String> nombres = c.cargarJugadores();
        Jugador jugador = null;

        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();

        for (String nombre : nombres) {

            jugador = new Jugador();

            jugador.setNombre(nombre);
            jugador.setArquero(randInt(0, 10));
            jugador.setCabezazo(randInt(0, 10));
            jugador.setCotizacion(1.0 * randInt(0, 10000000));
            jugador.setEntradas(randInt(0, 10));
            jugador.setMarca(randInt(0, 10));
            jugador.setMentalidad(randInt(0, 10));
            jugador.setPelotaParada(randInt(0, 10));
            jugador.setPotenciaTiro(randInt(0, 10));
            jugador.setPrecisionTiro(randInt(0, 10));
            jugador.setRegate(randInt(0, 10));
            jugador.setResistencia(randInt(0, 10));
            jugador.setSalario(1.0 * randInt(2000, 100000));
            jugador.setVelocidad(randInt(0, 10));

            crearJugador(jugador);
        }

    }

    public List<Jugador> obtenerJugadorPorNombre(String nombre) {
        List<Jugador> respuesta;

        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();

        Query consulta = s.createQuery("From Jugador where nombre like :parametro");
        consulta.setParameter("parametro", "%" + nombre + "%");

        respuesta = consulta.list();

//        Criteria c = s.createCriteria(Alumno.class)
//                .add(Restrictions.like("nombreYApellido", nombre, MatchMode.START));
//        respuesta = c.list();
        s.close();

        return respuesta;
    }

    public static int randInt(int min, int max) {

        Random rand = new Random();

        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

}
