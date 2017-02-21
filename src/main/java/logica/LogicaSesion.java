/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import dao.ClubDAO;
import dao.UsuarioDAO;
import entidades.Usuario;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author ramiro
 */
@Named
@SessionScoped
public class LogicaSesion implements Serializable {
    
    @Inject
    UsuarioDAO usuarioDAO;
    
    @Inject
    ClubDAO clubDAO;

    public Usuario validarLogin(String user, String pass) {

        Usuario usuarioObj = null;
        

        if (usuarioDAO.validarLoginUsuario(user, pass)) {
            FacesContext context = FacesContext.getCurrentInstance();
            
            usuarioObj=usuarioDAO.getUsuarioByNombre(user);
            
            
            context.getExternalContext().getSessionMap().put("user", usuarioObj);
            
            
            
            
        }
        return usuarioObj;

    }

    public String crearUsuario(String user, String pass, String nombreClub) {

        

        
        
        Usuario u = new Usuario();
        u.setNombre(user);
        u.setPassword(pass);

        if (usuarioDAO.crearUsuario(u, nombreClub)) {
            
            return "exito";
        }
        return "";
    }

    public boolean cargarUsuario(String u) {
        
        
        Usuario usuarioObj = usuarioDAO.getUsuarioByNombre(u);
        
        if(usuarioObj != null){
            
            FacesContext fc = FacesContext.getCurrentInstance();
            HttpServletRequest sr = (HttpServletRequest) fc.getExternalContext().getRequest();
            sr.getSession().setAttribute("usuarioObj", usuarioObj);
            return true;
            
        }else{
            return false;
        }
        
        
        
        
        
        
    }

    public void actualizarUsuario(Usuario usuarioObj) {
        
        clubDAO.actualizarClub(usuarioObj.getClub());
        
    }
    
    

}
