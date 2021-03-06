/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import logica.LogicaSesion;
import dao.ClubDAO;
import entidades.Usuario;
import java.io.IOException;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ramiro
 */
@Named
@SessionScoped
public class SesionController implements Serializable {

    @Inject
    private LogicaSesion miLogicaSesion;
    
    @Inject
    ClubDAO clubDAO;

    private Usuario usuarioLogueado;

    private String usuario;

    private String password;

    public Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public void setUsuarioLogueado(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String iniciarSesion() {

        if ("admin".equals(usuario) && "admin".equals(password)) {
            return "escritorioAdministracion";
        }

        usuarioLogueado = miLogicaSesion.validarLogin(usuario, password);

        if (usuarioLogueado != null) {

            return "usuario/escritorio";
        }

        this.addMessage("Login fallido", "");
        return "";

    }

    public void logout() {
        ExternalContext ctx
                = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath
                = ((ServletContext) ctx.getContext()).getContextPath();

        try {

            ((HttpSession) ctx.getSession(false)).invalidate();


            ctx.redirect(ctxPath + "/faces/index.xhtml");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void actualizarUsuario(ActionEvent actionEvent) {

        actualizarUsuario();
        
       
    }

    SesionController() {
    }

    public void actualizarUsuario() {
        miLogicaSesion.actualizarUsuario(usuarioLogueado);
        this.addMessage("Actualizacion exitosa", "");
    }

    public void leerNotificaciones(){
        this.usuarioLogueado.getClub().getNotificaciones().clear();
       
        clubDAO.actualizarClub(usuarioLogueado.getClub());
    }
    
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
}
