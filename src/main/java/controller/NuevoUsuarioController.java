/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import logica.LogicaSesion;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author ramiro
 */
@Named(value = "nuevoUsuarioController")
@RequestScoped
public class NuevoUsuarioController {
    
    @Inject
    LogicaSesion miLogicaSesion;
    
    String usuario, password,nombreClub,email;
    
     public String crearUsuario() {

         
        return miLogicaSesion.crearUsuario(usuario, password,nombreClub);

    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombreClub() {
        return nombreClub;
    }

    public void setNombreClub(String nombreClub) {
        this.nombreClub = nombreClub;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    
    

    /**
     * Creates a new instance of NuevoUsuarioController
     */
    public NuevoUsuarioController() {
    }
    
}
