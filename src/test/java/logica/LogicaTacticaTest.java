/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import entidades.Club;
import entidades.Jugador;
import entidades.Oferta;
import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;
import org.junit.Ignore;
import utilidades.ConexionJPA;

/**
 *
 * @author ramiro
 */
public class LogicaTacticaTest {

    public LogicaTacticaTest() {
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
     * Test of reorganizarTacticas method, of class LogicaTactica.
     */
    @Ignore
    public void testReorganizarTacticas() {
        System.out.println("reorganizarTacticas");

        EntityManager em = ConexionJPA.getInstance().em();
        em.getTransaction().begin();

        Oferta oferta = new Oferta();
        Jugador jugador = em.find(Jugador.class, 1L);
        oferta.setJugadorObjetivo(jugador);
        oferta.setDestino(jugador.getClub());
        oferta.setOrigen(em.find(Club.class, 2L));
        oferta.setMontoDeOperacion(2000D);
        

        LogicaTactica logicaTactica = new LogicaTactica();
        logicaTactica.reorganizarTacticas(oferta);

        
        assertTrue("La cantidad de jugadores en la tactica es distinta de once",oferta.getDestino().getTacticas().get(0).getTitulares().size() == 11);

        em.getTransaction().commit();
        em.close();

    }

}
