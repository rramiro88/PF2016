/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Club;
import entidades.Estadio;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author ramiro
 */
public class ClubDAO {

    public Club crearClub(String nombreClub) {
        Club club = new Club();
        club.setNombre(nombreClub);
        club.setPresupuesto(0F);
        club.setUrlEscudo("https://image.freepik.com/iconos-gratis/variante-escudo-con-bordes-blancos-y-negros_318-48076.png");


        
        Estadio estadio = new Estadio();
        club.setEstadio(estadio);

        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();

        try {

            s.save(estadio);

            s.save(club);
            s.getTransaction().commit();
            

        } catch (Exception ex) {
            s.getTransaction().rollback();
            ex.printStackTrace();
        }finally{
            s.close();
        }

        return club;
    }


    public void actualizarClub(Club club) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();

        try {

            s.update(club);
            s.getTransaction().commit();
            

        } catch (Exception ex) {
            s.getTransaction().rollback();
            ex.printStackTrace();
        }finally{
            s.close();
        }
    }

}
