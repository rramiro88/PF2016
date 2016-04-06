/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.proyectofinal.logica;

import dao.ClubDAO;
import dao.UsuarioDAO;
import entidades.Usuario;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author ramiro
 */
@Named
@SessionScoped
public class LogicaSesion implements Serializable {

    public boolean validarLogin(String user, String pass) {

        Usuario usuarioObj;
        UsuarioDAO p = new UsuarioDAO();

        if (p.validarLoginUsuario(user, pass)) {
            FacesContext context = FacesContext.getCurrentInstance();
            
            usuarioObj=p.getUsuarioByNombre(user);
            
            
            context.getExternalContext().getSessionMap().put("user", usuarioObj);
            
            
            
            return true;
        }
        return false;

    }

    public String crearUsuario(String user, String pass, String nombreClub) {

        UsuarioDAO p = new UsuarioDAO();

        
        
        Usuario u = new Usuario();
        u.setNombre(user);
        u.setPassword(pass);

        if (p.crearUsuario(u, nombreClub)) {
            
            return "exito";
        }
        return "";
    }

    public boolean cargarUsuario(String u) {
        UsuarioDAO p = new UsuarioDAO();
        
        Usuario usuarioObj = p.getUsuarioByNombre(u);
        
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
        ClubDAO clubDAO = new ClubDAO();
        clubDAO.actualizarClub(usuarioObj.getClub());
        
    }
    
    

}
