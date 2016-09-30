/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Liga;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author alumno
 */
@Stateless
public class LigaDAO {

    @PersistenceContext
    EntityManager em;

    public boolean persistirLiga(Liga liga) {
        
        
        em.merge(liga);

        return true;
    }
}
