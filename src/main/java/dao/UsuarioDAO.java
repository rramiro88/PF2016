/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Club;
import entidades.Usuario;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author ramiro
 */
public class UsuarioDAO {

    public Usuario obtenerUsuario(int id) {
        Usuario usuario;
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();

        usuario = (Usuario) s.get(Usuario.class, id);
        System.out.println(usuario.getClub().getOfertasEnviadas().size());
        System.out.println(usuario.getClub().getOfertasRecibidas().size());

        s.close();

        return usuario;
    }

    public boolean crearUsuario(Usuario u, String nombreClub) {
        boolean respuesta = false;

        ClubDAO clubDAO = new ClubDAO();
        Club club;
        club = clubDAO.crearClub(nombreClub);
        u.setClub(club);

        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();

        try {

            s.save(u);
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

    public boolean validarLoginUsuario(String usuario, String contrasenia) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session sesion = sf.openSession();
        sesion.beginTransaction();

        Usuario usuario1 = null;

        try {
            List<Usuario> resultado = sesion.createCriteria(Usuario.class)
                    .add(Restrictions.eq("nombre", usuario))
                    .list();

            if (!resultado.isEmpty()) {
                usuario1 = resultado.get(0);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            sesion.close();
        }
        if (usuario1 == null) {
            return false;
        }
        return (usuario1.getPassword().equals(contrasenia));
    }

    public Usuario getUsuarioByNombre(String nombre) {

        Usuario u = null;
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();
        List<Usuario> resultado = s.createCriteria(Usuario.class)
                .add(Restrictions.eq("nombre", nombre)).list();
        u = resultado.get(0);

        System.out.println("OFERTAS ENVIADAS---> " + u.getClub().getOfertasEnviadas().size());
        System.out.println("OFERTAS RECIBIDAS---> " + u.getClub().getOfertasRecibidas().size());
        System.out.println("NOTIFICACIONES---> "+ u.getClub().getNotificaciones().size());
        System.out.println("LIGAS---> "+ u.getClub().getLigas().size());
        
        
        if(u.getClub().getLigas().size()>0){
            System.out.println("EQUIPOS--->" +u.getClub().getLigas().get(0).getEquiposParticipantes().size());
            System.out.println("PARTIDOS--->" +u.getClub().getLigas().get(0).getPartidos().size());
        }
        
        if (u.getClub().getTacticas().size() > 0) {
            System.out.println("POSICIONES---> " + u.getClub().getTacticas().get(0).getPosiciones().size());
            System.out.println("TITULARIDADES--->" + u.getClub().getTacticas().get(0).getPosicionesEnCancha().size());
        }else{
            System.out.println("NO HAY TACTICAS CARGADAS");
        }

        s.close();

        return u;
    }

}
