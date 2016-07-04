/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Tactica implements Serializable {
    @ManyToOne
    private Club club;

    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    
    
    @OneToMany(cascade = CascadeType.ALL)                                                                                   
    List<Jugador> titulares;                                                                                                
   
    @OneToMany(cascade = CascadeType.ALL)                                                                                   
    List<Jugador> suplentes;                                                                                                
    
    

    String nombre;

    @ElementCollection
    private List<String> posiciones;

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

    public List<Jugador> getTitulares() {
        return titulares;
    }

    public void setTitulares(List<Jugador> titulares) {
        this.titulares = titulares;
    }

    public List<Jugador> getSuplentes() {
        return suplentes;
    }

    public void setSuplentes(List<Jugador> suplentes) {
        this.suplentes = suplentes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
