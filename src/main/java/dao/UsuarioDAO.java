/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Club;
import entidades.Liga;
import entidades.Usuario;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.hibernate.Hibernate;

/**
 *
 * @author ramiro
 */
@Stateless
public class UsuarioDAO {

    @PersistenceContext(unitName = "proyecto_pu")
    private EntityManager em;
    
    
    @Inject
    ClubDAO clubDAO;
    
    public Usuario obtenerUsuario(int id) {
        Usuario usuario;
       

        usuario = em.find(Usuario.class, id);
        System.out.println(usuario.getClub().getOfertasEnviadas().size());
        System.out.println(usuario.getClub().getOfertasRecibidas().size());

        

        return usuario;
    }

    public boolean crearUsuario(Usuario u, String nombreClub) {
        Club club = clubDAO.crearClub(nombreClub);
        
        u.setClub(club);
        
        em.persist(club);
        em.persist(u);
        
        return true;
    }


    public boolean validarLoginUsuario(String usuario, String contrasenia) {
       

        Usuario usuario1 = null;

        Query q = em.createQuery("select u from Usuario u where u.nombre = :parametro",Usuario.class);
        q.setParameter("parametro", usuario);
        
        usuario1 = (Usuario) q.getSingleResult();
        return (usuario1.getPassword().equals(contrasenia));
    }

    
    public Usuario getUsuarioByNombre(String nombre) {

        Query q = em.createQuery("select u from Usuario u WHERE u.nombre = :parametro");
        q.setParameter("parametro", nombre);
        
        Usuario u = (Usuario) q.getSingleResult();
        
        System.out.println("UsuarioDAO.getUsuarioByNombre: OFERTAS ENVIADAS---> " + u.getClub().getOfertasEnviadas().size());
        System.out.println("UsuarioDAO.getUsuarioByNombre: OFERTAS RECIBIDAS---> " + u.getClub().getOfertasRecibidas().size());
        System.out.println("UsuarioDAO.getUsuarioByNombre: NOTIFICACIONES---> " + u.getClub().getNotificaciones().size());
        System.out.println("UsuarioDAO.getUsuarioByNombre: LIGAS---> " + u.getClub().getLigas().size());
        System.out.println("UsuarioDAO.getUsuarioByNombre: PRESTAMOS---> " + u.getClub().getPrestamos().size());
        System.out.println("UsuarioDAO.getUsuarioByNombre: PARTIDOS LOCAL-->" + u.getClub().getPartidosLocal().size());
        System.out.println("UsuarioDAO.getUsuarioByNombre: PARTIDOS VISITANTE-->" + u.getClub().getPartidosVisitante().size());
        System.out.println("UsuarioDAO.getUsuarioByNombre: TRANSACCIONES-->" + u.getClub().getTransacciones().size());
        System.out.println("UsuarioDAO.getUsuarioByNombre: TACTICAS-->" + u.getClub().getTacticas().size());
        
        
        for (Liga liga : u.getClub().getLigas()) {
            
            Hibernate.initialize(liga.getPartidos());
            Hibernate.initialize(liga.getEquiposParticipantes());
        }
            
        
        

        
        return u;
        

        
    }

}
