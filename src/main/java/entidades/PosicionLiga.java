/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author ramiro
 */

@Entity
public class PosicionLiga implements Serializable {
    
    @Id
    @GeneratedValue
    Long id;
    
    Integer golesAFavor=0, golesEnContra=0, pj=0, puntaje=0,pg=0,pe=0,pp=0;
    
    @OneToOne
    Club club;
    


    public PosicionLiga(Integer golesAFavor, Integer golesEnContra, Integer pj, Integer puntaje, Club club) {
        this.golesAFavor = golesAFavor;
        this.golesEnContra = golesEnContra;
        this.pj = pj;
        this.puntaje = puntaje;

    }

    public PosicionLiga() {
    }

    public Integer getPg() {
        return pg;
    }

    public void setPg(Integer pg) {
        this.pg = pg;
    }

    public Integer getPe() {
        return pe;
    }

    public void setPe(Integer pe) {
        this.pe = pe;
    }

    public Integer getPp() {
        return pp;
    }

    public void setPp(Integer pp) {
        this.pp = pp;
    }
    
    

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }
    
    

    public Integer getGolesAFavor() {
        return golesAFavor;
    }

    public void setGolesAFavor(Integer golesAFavor) {
        this.golesAFavor = golesAFavor;
    }

    public Integer getGolesEnContra() {
        return golesEnContra;
    }

    public void setGolesEnContra(Integer golesEnContra) {
        this.golesEnContra = golesEnContra;
    }

    public Integer getPj() {
        return pj;
    }

    public void setPj(Integer pj) {
        this.pj = pj;
    }

    public Integer getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Integer puntaje) {
        this.puntaje = puntaje;
    }


    
    
    
    
    
}
