/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.ArrayList;
import javax.persistence.EntityManager;
import static org.junit.Assert.assertTrue;
import org.junit.Ignore;
import utilidades.ConexionJPA;

/**
 *
 * @author ramiro
 */
public class LigaTest {
    
    @Ignore
    public void testOrganizar(){
        
        Liga liga = new Liga();
        
        EntityManager em = ConexionJPA.getInstance().em();
        
        em.getTransaction().begin();
        
        Club uno = em.find(Club.class, 1L);
        Club dos = em.find(Club.class, 2L);
        Club tres = em.find(Club.class, 3L);
        
        liga.setEquiposParticipantes(new ArrayList<Club>());
        liga.getEquiposParticipantes().add(uno);
        liga.getEquiposParticipantes().add(dos);
        liga.getEquiposParticipantes().add(tres);
        
        liga.setNombre("Liga de prueba");
        
        liga.organizar();
        
        int localUno=0, localDos=0, localTres=0;
        
        for (Partido p : liga.getPartidos()) {
            
            if(p.getLocal().getId()==uno.getId()){
                localUno++;
            }
            if(p.getLocal().getId()==dos.getId()){
                localDos++;
            }
            if(p.getLocal().getId()==tres.getId()){
                localTres++;
            }
            
        }
        
        System.out.println("Local uno: "+localUno);
        System.out.println("Local dos: "+localDos);
        System.out.println("Local tres: "+localTres);
        
        assertTrue("No hay equilibrio entre locales",localUno>0 && localDos>0 && localTres>0);
        
        
        
        
        
        
        em.getTransaction().commit();
        em.close();
        
        
    }
    
    
}
