/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entidades.Liga;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author alumno
 */

@Named
@SessionScoped
public class LigaController implements Serializable{
    
    /**
     * Refiere a la liga seleccionada actualmente,
     * que se visualiza en detalleLiga.xhtml
     */
    private Liga liga;
    
    public String verDetalles(Liga ligaADetallar){
        
        liga = ligaADetallar;
        
        return "detalleLiga";
        
        
    }

    public Liga getLiga() {
        return liga;
    }

    public void setLiga(Liga liga) {
        this.liga = liga;
    }
    
    
}
