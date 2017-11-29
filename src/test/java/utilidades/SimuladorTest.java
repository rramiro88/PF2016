/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import entidades.Club;
import entidades.Tactica;
import javax.persistence.EntityManager;
import org.hibernate.Hibernate;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ramiro
 */
public class SimuladorTest {
    
    public SimuladorTest() {
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
     * Test of simular method, of class Simulador.
     */
    @Test
    public void testSimular() {
        System.out.println("simular");
        
        EntityManager em = ConexionJPA.getInstance().em();
        
        em.getTransaction().begin();

        Tactica tacticaLocal = em.find(Club.class, 1L).getTacticas().get(0);
        Tactica tacticaVisitante = em.find(Club.class, 2L).getTacticas().get(0);
        
        Hibernate.initialize(tacticaLocal.getClub().getPartidosLocal());
        Hibernate.initialize(tacticaLocal.getClub().getPartidosVisitante());
        Hibernate.initialize(tacticaVisitante.getClub().getPartidosLocal());
        Hibernate.initialize(tacticaVisitante.getClub().getPartidosVisitante());
        
        em.getTransaction().commit();
        em.close();
        
        int[] resultado = Simulador.simular(tacticaLocal, tacticaVisitante);
        
        assertTrue(resultado[0]>-1 && resultado[1]>-1);
        
    }
    
}
