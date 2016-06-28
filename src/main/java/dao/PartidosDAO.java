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
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author alumno
 */
public class PartidosDAO {

    public List<Partido> obtenerPartidosDeHoy() {
        Date hoy = Date.valueOf(LocalDate.now());
        List<Partido> respuesta = null;
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.openSession();
        try {

            s.beginTransaction();

            Query consulta = s.createQuery("From Partido where fecha = :parametro");
            consulta.setParameter("parametro", hoy.toString());
            respuesta = consulta.list();
        } catch (Exception e) {
        } finally {
            s.close();
        }

        return respuesta;
    }

    public boolean actualizarPartido(Partido p) {
         
        boolean respuesta = true;

        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();

        try {
            s.update(p);
            System.out.println("update exitoso de partido! DAO");
            s.getTransaction().commit();
            
        } catch (Exception e) {
            respuesta = false;
            s.getTransaction().rollback();

        }finally{
            s.close();
        }

        return respuesta;
    }

}
