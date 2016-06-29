/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.proyectofinal.controller;

import ar.proyectofinal.logica.LogicaSesion;
import entidades.Usuario;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author ramiro
 */
@Named
@SessionScoped
public class SesionController implements Serializable {

    @Inject
    private LogicaSesion miLogicaSesion;


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

        if("admin".equals(usuario) && "admin".equals(password))
            return "escritorioAdministracion";
        
        usuarioLogueado = miLogicaSesion.validarLogin(usuario, password);
        
        
        
        
        if (usuarioLogueado != null) {

            
            
            
            return "escritorio";
        }

        return "";

    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index";

    }

    public void actualizarUsuario(ActionEvent actionEvent) {

    

        actualizarUsuario();

        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualizacion exitosa", null);
        FacesContext.getCurrentInstance().addMessage(null, message);

    }

    SesionController() {
    }

    public void actualizarUsuario() {
        miLogicaSesion.actualizarUsuario(usuarioLogueado);
    }
    
  

}
