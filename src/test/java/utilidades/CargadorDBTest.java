/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import java.util.ArrayList;
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
public class CargadorDBTest {
    
    public CargadorDBTest() {
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
     * Test of cargarJugadores method, of class CargadorDB.
     */
    @org.junit.Test
    public void testCargarJugadores() {
        System.out.println("cargarJugadores");
        CargadorDB instance = new CargadorDB();

        ArrayList<String> result = instance.cargarJugadores();
        
        assertNotNull(result);
        
    }

   

    
}
