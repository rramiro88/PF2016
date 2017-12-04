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
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import utilidades.ConexionJPA;

/**
 *
 * @author ramiro
 */
public class LogicaMercadoTest {
    
    public LogicaMercadoTest() {
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
     * Test of transferir method, of class LogicaMercado.
     */
    @Ignore
    public void testTransferir() {
        System.out.println("transferir");
        
        EntityManager em = ConexionJPA.getInstance().em();
        em.getTransaction().begin();

        Oferta oferta = new Oferta();
        Jugador jugador = em.find(Jugador.class, 1L);
        oferta.setJugadorObjetivo(jugador);
        oferta.setDestino(jugador.getClub());
        oferta.setOrigen(em.find(Club.class, 2L));
        oferta.setMontoDeOperacion(2000D);
        oferta.setCondicion(Oferta.VENTA);
        
        Double presupuestoAnteriorComprador = oferta.getOrigen().getPresupuesto();
        Double presupuestoAnteriorVendedor = oferta.getDestino().getPresupuesto();
        
        LogicaMercado instance = new LogicaMercado();
        
        boolean result = instance.transferir(oferta);
        
        assertTrue(result);
        assertTrue("El jugador no fue transferido", oferta.getOrigen().getPlantel().contains(oferta.getJugadorObjetivo()));
        assertTrue("El dinero no fue transferido",presupuestoAnteriorComprador==oferta.getMontoDeOperacion()+oferta.getOrigen().getPresupuesto());
        assertTrue("El dinero no fue recibido",presupuestoAnteriorVendedor==oferta.getDestino().getPresupuesto()-oferta.getMontoDeOperacion());
        
        em.getTransaction().rollback();
        em.close();
        
    }

    

    
}
