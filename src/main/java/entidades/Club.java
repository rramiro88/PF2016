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
import javax.persistence.ManyToMany;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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
    private Double presupuesto;
    private String urlEscudo;
    
    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Notificacion> notificaciones;

    @OneToOne(cascade = CascadeType.ALL)
    private Estadio estadio;

    @OneToMany(mappedBy = "club", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Jugador> plantel;

    @OneToMany(mappedBy = "origen", cascade = CascadeType.ALL)
    private List<Oferta> ofertasEnviadas;

    @OneToMany(mappedBy = "destino", cascade = CascadeType.ALL)
    private List<Oferta> ofertasRecibidas;

   
    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL)
    List<Tactica> tacticas;

    @OneToMany(mappedBy = "visitante", cascade = CascadeType.ALL)
    private List<Partido> partidos;

    @ManyToMany(mappedBy = "equiposParticipantes", cascade = CascadeType.ALL)
    private List<Liga> ligas;

    public Club() {

        plantel = new ArrayList<>();
        tacticas = new ArrayList<>();
        ofertasEnviadas = new ArrayList<>();
        ofertasRecibidas = new ArrayList<>();
        notificaciones = new ArrayList<>();
        ligas = new ArrayList<>();
        presupuesto = 0D;
    }

    public List<Liga> getLigas() {
        return ligas;
    }

    public void setLigas(List<Liga> ligas) {
        this.ligas = ligas;
    }
    
    

    public List<Notificacion> getNotificaciones() {
        return notificaciones;
    }

    public void setNotificaciones(List<Notificacion> notificaciones) {
        this.notificaciones = notificaciones;
    }

    public List<Partido> getPartidos() {
        return partidos;
    }

    public void setPartidos(List<Partido> partidos) {
        this.partidos = partidos;
    }
    
    

    public List<Tactica> getTacticas() {
        return tacticas;
    }

    public void setTacticas(List<Tactica> tacticas) {
        this.tacticas = tacticas;
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

    public Double getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(Double presupuesto) {
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

    public void agregarJugador(Jugador j) {
        j.setClub(this);
        this.plantel.add(j);
    }
    
    public void agregarNotificacion(String mensaje){
        Notificacion n = new Notificacion();
        n.setMensaje(mensaje);
        n.setLeida(false);
        
        notificaciones.add(n);
    }
    
    public List<Integer> getNumerosLibres(){
        ArrayList<Integer> respuesta = new ArrayList();
        ArrayList<Integer> numerosPosibles = new ArrayList();
        
        for (int i = 0; i < 50; i++) {
            numerosPosibles.add(i);
        }
        
        for (Jugador jugador : plantel) {
            numerosPosibles.remove(jugador.getNumeroCamiseta());
        }
        
        
        
        
        return numerosPosibles;
    }

}
