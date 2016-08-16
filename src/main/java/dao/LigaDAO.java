/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Liga;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author alumno
 */
public class LigaDAO {
    
    
    public boolean persistirLiga(Liga liga){
         boolean respuesta = true;

        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();

        try {
            s.save(liga);
            s.getTransaction().commit();

        } catch (Exception e) {
            respuesta = false;
            s.getTransaction().rollback();
            System.out.println("ROLLBACK EN TRANSACCION");

        } finally {
            s.close();
        }

        return respuesta;
    }
}
