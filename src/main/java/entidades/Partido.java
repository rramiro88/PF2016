/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author alumno
 */

@Entity
public class Partido implements Serializable {
    @ManyToOne
    private Liga liga;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    
    @ManyToOne(cascade = CascadeType.MERGE)
    Club local;
    
    @ManyToOne
    Club visitante;
    
    int golesLocal, golesVisitantes, concurrencia;

    boolean jugado;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    Date fecha;

    public boolean isJugado() {
        return jugado;
    }

    public void setJugado(boolean jugado) {
        this.jugado = jugado;
    }

    
    
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Liga getLiga() {
        return liga;
    }

    public void setLiga(Liga liga) {
        this.liga = liga;
    }
    
    
    
    public Club getLocal() {
        return local;
    }

    public void setLocal(Club local) {
        this.local = local;
    }

    public Club getVisitante() {
        return visitante;
    }

    public void setVisitante(Club visitante) {
        this.visitante = visitante;
    }

    public int getGolesLocal() {
        return golesLocal;
    }

    public void setGolesLocal(int golesLocal) {
        this.golesLocal = golesLocal;
    }

    public int getGolesVisitantes() {
        return golesVisitantes;
    }

    public void setGolesVisitantes(int golesVisitantes) {
        this.golesVisitantes = golesVisitantes;
    }

    public int getConcurrencia() {
        return concurrencia;
    }

    public void setConcurrencia(int concurrencia) {
        this.concurrencia = concurrencia;
    }

    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getIdGanador(){
        
        if(jugado){
            if(golesLocal>golesVisitantes){
                return local.getId();
            }
            if(golesVisitantes>golesLocal){
                return visitante.getId();
            }
            if(golesLocal==golesVisitantes){
                return -1L;
            }
        }
        
        return null;
    }
    
    
    
    
}
