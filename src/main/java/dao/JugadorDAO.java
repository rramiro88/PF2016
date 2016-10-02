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
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import utilidades.CargadorDB;

/**
 *
 * @author ramiro
 */
@Stateless
public class JugadorDAO {
    
    @PersistenceContext
    EntityManager em;

    public List<Jugador> listarLibres() {
        List<Jugador> lista;

       Query q = em.createQuery("select j from Jugador j where j.club IS NULL", Jugador.class);

        return q.getResultList();

    }

    public boolean crearJugador(Jugador j) {
        em.persist(j);
        
        

        return true;
    }

    public boolean actualizarJugador(Jugador j) {

       em.merge(j);
       return true;
    }

    public List<Jugador> crearJugadoresAlAzarLista() {

        List<Jugador> respuesta = new ArrayList<>();
        CargadorDB c = new CargadorDB();

        ArrayList<String> nombres = c.cargarJugadores();
        Jugador jugador = null;

       

        for (String nombre : nombres) {

            jugador = new Jugador();

            jugador.setNombre(nombre);
            jugador.setArquero(randInt(1, 10));
            jugador.setCabezazo(randInt(1, 10));
            jugador.setCotizacion(1.0 * randInt(0, 10000000));
            jugador.setEntradas(randInt(1, 10));
            jugador.setMarca(randInt(1, 10));
            jugador.setMentalidad(randInt(1, 10));
            jugador.setPelotaParada(randInt(1, 10));
            jugador.setPotenciaTiro(randInt(1, 10));
            jugador.setPrecisionTiro(randInt(1, 10));
            jugador.setRegate(randInt(1, 10));
            jugador.setResistencia(randInt(1, 10));
            jugador.setSalario(1.0 * randInt(2000, 100000));
            jugador.setVelocidad(randInt(1, 10));

            respuesta.add(jugador);
        }

        return respuesta;
    }

    public void crearJugadoresAlAzar() {
        CargadorDB c = new CargadorDB();

        ArrayList<String> nombres = c.cargarJugadores();
        Jugador jugador = null;

       

        for (String nombre : nombres) {

            jugador = new Jugador();

            jugador.setNombre(nombre);
            jugador.setArquero(randInt(1, 10));
            jugador.setCabezazo(randInt(1, 10));
            jugador.setCotizacion(1.0 * randInt(0, 10000000));
            jugador.setEntradas(randInt(1, 10));
            jugador.setMarca(randInt(1, 10));
            jugador.setMentalidad(randInt(1, 10));
            jugador.setPelotaParada(randInt(1, 10));
            jugador.setPotenciaTiro(randInt(1, 10));
            jugador.setPrecisionTiro(randInt(1, 10));
            jugador.setRegate(randInt(1, 10));
            jugador.setResistencia(randInt(1, 10));
            jugador.setSalario(1.0 * randInt(2000, 100000));
            jugador.setVelocidad(randInt(1, 10));

            crearJugador(jugador);
        }

    }

    public List<Jugador> obtenerJugadorPorNombre(String nombre) {
        List<Jugador> respuesta;

        

        Query consulta = em.createQuery("Select j From Jugador j where j.nombre like :parametro",Jugador.class);
        consulta.setParameter("parametro", "%" + nombre + "%");

        respuesta = consulta.getResultList();



        return respuesta;
    }

    public static int randInt(int min, int max) {

        Random rand = new Random();

        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

}
