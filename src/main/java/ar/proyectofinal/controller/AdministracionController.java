/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.proyectofinal.controller;

import ar.proyectofinal.logica.LogicaAdministracion;
import ar.proyectofinal.logica.LogicaLiga;
import entidades.Club;
import entidades.Liga;
import entidades.Partido;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import utilidades.Simulador;

/**
 *
 * @author alumno
 */

@Named
@ApplicationScoped
public class AdministracionController implements Serializable{
    
    @Inject
    LogicaAdministracion logicaAdministracion;
    
    @Inject
    LogicaLiga logicaLiga;
    
    
    List<Club> resultado;
    
    List<String> posiciones;
    
    List<Club> clubesInvitados = new ArrayList<>();
    
    String nombreClub, nombreLiga;
    
    Liga liga = new Liga();
    
    
    public void generarRanking(){
        
        
        posiciones = logicaLiga.obtenerPosicionesLiga(liga);
        
        
    }
    
    
    
    
    public void buscarClubesPorNombre(){
        
        
       resultado = logicaAdministracion.buscarClubesPorNombre(this.nombreClub);
        
    }

    public void crearLiga(){
        
        
        
        liga.setEquiposParticipantes(clubesInvitados);
        liga.setNombre(nombreLiga);
        liga.organizar();

    }
    
    
    public void simularPartidos(){
        
        int diferenciaGoles;
         for (Partido p : liga.getPartidos()) {
            
            diferenciaGoles = Simulador.simular(p.getLocal().getTacticas().get(0).getTitulares(), p.getVisitante().getTacticas().get(0).getTitulares());
            
            if(diferenciaGoles > 0 ){
                p.setGolesLocal(diferenciaGoles);
                p.setGolesVisitantes(0);
            }else if(diferenciaGoles < 0 ){
                p.setGolesLocal(0);
                p.setGolesVisitantes(-1*diferenciaGoles);
            }else{
                p.setGolesLocal(0);
                p.setGolesVisitantes(0);
            }
            
            
            
        }
    }

    public List<String> getPosiciones() {
        return posiciones;
    }

    public void setPosiciones(List<String> posiciones) {
        this.posiciones = posiciones;
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
