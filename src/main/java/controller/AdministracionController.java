/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import logica.LogicaAdministracion;
import logica.LogicaLiga;
import entidades.Club;
import entidades.Liga;
import entidades.Partido;
import entidades.PosicionLiga;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import utilidades.Simulador;

/**
 *
 * @author alumno
 */
@Named
@ViewScoped
public class AdministracionController implements Serializable {

    @Inject
    LogicaAdministracion logicaAdministracion;

    @Inject
    LogicaLiga logicaLiga;

    List<Club> resultado;

    List<PosicionLiga> posiciones;

    List<Club> clubesInvitados = new ArrayList<>();

    String nombreClub, nombreLiga;

    Liga liga = new Liga();

    public void generarRanking() {

        posiciones = logicaLiga.obtenerPosicionesLiga(liga);

    }

    public void buscarClubesPorNombre() {

        resultado = logicaAdministracion.buscarClubesPorNombre(this.nombreClub);

    }

    public void crearLiga() {

        liga.setEquiposParticipantes(clubesInvitados);
        liga.setNombre(nombreLiga);
        liga.organizar();

        if(logicaLiga.crearLiga(liga)){
            addMessage("La liga "+nombreLiga+" fue creada");
        }else{
            addMessage("Ocurrió un error al crear la liga. Revise que no se repitan los equipos invitados");
        }
        
        
    }

    public void simularPartidos() {

        int diferenciaGoles;
        for (Partido p : liga.getPartidos()) {

            if (!p.isJugado()) { // Si no esta jugado, lo jugamos.
                diferenciaGoles = Simulador.simular(p.getLocal().getTacticas().get(0).getTitulares(), p.getVisitante().getTacticas().get(0).getTitulares());

                if (diferenciaGoles > 0) {
                    p.setGolesLocal(diferenciaGoles);
                    p.setGolesVisitantes(0);
                } else if (diferenciaGoles < 0) {
                    p.setGolesLocal(0);
                    p.setGolesVisitantes(-1 * diferenciaGoles);
                } else {
                    p.setGolesLocal(0);
                    p.setGolesVisitantes(0);
                }
                
                p.setJugado(true);
            }

        }
    }

    public List<PosicionLiga> getPosiciones() {
        return posiciones;
    }

    public void setPosiciones(List<PosicionLiga> posiciones) {
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

    public void eliminarInvitado(Club clubAEliminar) {
        clubesInvitados.remove(clubAEliminar);
    }

    public void invitarCLub(Club clubInvitado) {
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
    
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
