/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.proyectofinal.controller;

import ar.proyectofinal.logica.LogicaAdministracion;
import entidades.Club;
import entidades.Liga;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author alumno
 */

@Named
@ApplicationScoped
public class AdministracionController implements Serializable{
    
    @Inject
    LogicaAdministracion logicaAdministracion;
    
    
    List<Club> resultado;
    
    List<Club> clubesInvitados = new ArrayList<>();
    
    String nombreClub, nombreLiga;
    
    Liga liga = new Liga();
    
    
    public void buscarClubesPorNombre(){
        
        
       resultado = logicaAdministracion.buscarClubesPorNombre(this.nombreClub);
        
    }

    public void crearLiga(){
        
        
        
        liga.setEquiposParticipantes(clubesInvitados);
        liga.setNombre(nombreLiga);
        liga.organizar();
        
        
        
        
    }
    
    public String getNombreLiga() {
        return nombreLiga;
    }

    public void setNombreLiga(String nombreLiga) {
        this.nombreLiga = nombreLiga;
    }

    public Liga getLiga() {
        return liga;
    }

    public void setLiga(Liga liga) {
        this.liga = liga;
    }
    
    
    
    public void  eliminarInvitado(Club clubAEliminar){
        clubesInvitados.remove(clubAEliminar);
    }
    public void invitarCLub(Club clubInvitado){
        clubesInvitados.add(clubInvitado);
    }

    public List<Club> getClubesInvitados() {
        return clubesInvitados;
    }

    public void setClubesInvitados(List<Club> clubesInvitados) {
        this.clubesInvitados = clubesInvitados;
    }
    
    

    public List<Club> getResultado() {
        return resultado;
    }

    public void setResultado(List<Club> resultado) {
        this.resultado = resultado;
    }
    

    
    public String getNombreClub() {
        return nombreClub;
    }

    public void setNombreClub(String nombreClub) {
        this.nombreClub = nombreClub;
    }
    
    
    
    
}
