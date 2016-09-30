/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Oferta;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ramiro
 */
@Stateless
public class OfertaDAO {

    @PersistenceContext
    EntityManager em;
    
    public boolean crearOferta(Oferta oferta) {
        em.persist(oferta);
        return true;
    }
    
     public boolean eliminarOferta(Oferta oferta) {
      em.remove(em.contains(oferta) ? oferta : em.merge(oferta));
      return true;
    }
}
