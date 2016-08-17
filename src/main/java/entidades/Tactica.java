/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Tactica implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ElementCollection(fetch = FetchType.EAGER)
    Map<Long, Boolean> titularidad;

    String nombre;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> posiciones;

    @ManyToOne
    private Club club;
    
    
    

    public Tactica() {
        this.titularidad = new HashMap<>();
    }

    
    public String getTopPosicion(int indice){
        
        
        String sub = null;
        
        sub = posiciones.get(indice).split("a")[1];
        
        
        return sub;
    }
    public String getLeftPosicion(int indice){
        
        
        String sub = null;
        
        sub = posiciones.get(indice).split("a")[0];
        
        
        return sub;
    }
    
    
    public Map<Long, Boolean> getTitularidad() {
        return titularidad;
    }

    public void setTitularidad(Map<Long, Boolean> titularidad) {
        this.titularidad = titularidad;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public List<String> getPosiciones() {
        return posiciones;
    }

    public void setPosiciones(List<String> posiciones) {
        this.posiciones = posiciones;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Jugador> getTitulares() {
        List<Jugador> respuesta = new ArrayList<>();

        for (Jugador j : club.getPlantel()) {
            if (titularidad.get(j.getId()) != null) {
                respuesta.add(j);
            }
        }

        return respuesta;

    }
    
    public List<Jugador> getNoTitulares() {
        List<Jugador> respuesta = new ArrayList<>();

        for (Jugador j : club.getPlantel()) {
            if (titularidad.get(j.getId()) == null || titularidad.get(j.getId()) == false) {
                respuesta.add(j);
            }
        }

        return respuesta;

    }

}
