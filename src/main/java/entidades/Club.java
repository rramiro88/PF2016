/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;


/**
 *
 * @author ramiro
 */
@Entity
public class Club implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    private String nombre;
    private Float presupuesto;
    private String urlEscudo;
    

    
    @OneToOne(cascade = CascadeType.ALL)
    private Estadio estadio;
    
    
    @OneToMany (mappedBy = "club", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Jugador> plantel;
    @OneToMany(mappedBy = "origen", cascade = CascadeType.ALL)
    private List<Oferta> ofertasEnviadas;
    @OneToMany(mappedBy = "destino", cascade = CascadeType.ALL)
    private List<Oferta> ofertasRecibidas;
    

    public Club(){
        
        
        plantel = new ArrayList<>();
        
    }



    
    public List<Oferta> getOfertasEnviadas() {
        return ofertasEnviadas;
    }

    public void setOfertasEnviadas(List<Oferta> ofertasEnviadas) {
        this.ofertasEnviadas = ofertasEnviadas;
    }

    public List<Oferta> getOfertasRecibidas() {
        return ofertasRecibidas;
    }

    public void setOfertasRecibidas(List<Oferta> ofertasRecibidas) {
        this.ofertasRecibidas = ofertasRecibidas;
    }
    
    
    public long getIdClub() {
        return id;
    }

    public void setIdClub(long idClub) {
        this.id = idClub;
    }

    public String getNombre() {
        return nombre;
    }

    public Estadio getEstadio() {
        return estadio;
    }

    public void setEstadio(Estadio estadio) {
        this.estadio = estadio;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Float getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(Float presupuesto) {
        this.presupuesto = presupuesto;
    }

    public List<Jugador> getPlantel() {
        return plantel;
    }

    public void setPlantel(List<Jugador> plantel) {
        this.plantel = plantel;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrlEscudo() {
        return urlEscudo;
    }

    public void setUrlEscudo(String urlEscudo) {
        this.urlEscudo = urlEscudo;
    }
    
    public void agregarJugador(Jugador j){
        j.setClub(this);
        this.plantel.add(j);
    }
    
    
    
}
