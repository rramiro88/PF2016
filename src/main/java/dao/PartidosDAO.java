/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Partido;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.hibernate.Hibernate;



/**
 *
 * @author alumno
 */
@Stateless
public class PartidosDAO {

    @PersistenceContext
    EntityManager em;
    
    public List<Partido> obtenerPartidosDeHoy() {
        Date hoy = Date.valueOf(LocalDate.now());
        List<Partido> respuesta = null;
        
        

          

            Query consulta = em.createQuery("select p From Partido p where p.fecha = :parametro");
            consulta.setParameter("parametro", hoy);
            respuesta = consulta.getResultList();
            
            for (Partido p : respuesta) {
                Hibernate.initialize(p.getLocal().getTacticas());
                Hibernate.initialize(p.getVisitante().getTacticas());
            }
        

        return respuesta;
    }

    public boolean actualizarPartido(Partido p) {
         
       em.merge(p);
       return true;
    }

}
