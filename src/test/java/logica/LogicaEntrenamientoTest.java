/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import entidades.Club;
import entidades.Jugador;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import utilidades.ConexionJPA;

/**
 *
 * @author ramiro
 */
public class LogicaEntrenamientoTest {
    
    public LogicaEntrenamientoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of calcularProgresos method, of class LogicaEntrenamiento.
     */
    @Test
    public void testCalcularProgresos() {
        System.out.println("calcularProgresos");
        
        EntityManager em = ConexionJPA.getInstance().em();
        em.getTransaction().begin();

        List<Jugador> jugadores = em.find(Club.class, 1L).getPlantel();
        
        List<Integer> progresos = new ArrayList<>();
        for (Jugador jugador : jugadores) {
            jugador.setEntrenando(Jugador.VELOCIDAD);
            progresos.add(jugador.getProgresoVelocidad());
        }
        
        LogicaEntrenamiento logicaEntrenamiento=  new LogicaEntrenamiento();
        logicaEntrenamiento.calcularProgresos(jugadores);
        
        
        for (int i = 0; i < jugadores.size(); i++) {
            
            assertTrue("El jugador no progresó",jugadores.get(i).getProgresoVelocidad()>progresos.get(i));
            
        }
        
        
        em.getTransaction().commit();
        em.close();
        
    }
    
}
