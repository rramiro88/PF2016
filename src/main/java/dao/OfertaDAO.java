/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Oferta;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author ramiro
 */
public class OfertaDAO {

    public boolean crearOferta(Oferta oferta) {
        boolean respuesta = false;

        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();

        try {

            s.save(oferta);
            s.getTransaction().commit();

            respuesta = true;

        } catch (Exception ex) {
            s.getTransaction().rollback();
            ex.printStackTrace();
        } finally {
            s.close();
        }

        return respuesta;
    }
    
     public boolean eliminarOferta(Oferta oferta) {
        boolean respuesta = false;

        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();

        try {

            s.delete(oferta);
            s.getTransaction().commit();

            respuesta = true;

        } catch (Exception ex) {
            s.getTransaction().rollback();
            ex.printStackTrace();
        } finally {
            s.close();
        }

        return respuesta;
    }
}
