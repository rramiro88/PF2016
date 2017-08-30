/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.Transient;

/**
 *
 * @author alumno
 */
@Entity
public class Oferta implements Serializable {

    @Transient
    public static final String VENTA = "venta";
    @Transient
    public static final String PRESTAMO = "prestamo";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    
    
    @ManyToOne(fetch = FetchType.EAGER)
    Club origen;
    
    @ManyToOne
    Club destino;
    Double montoDeOperacion;
    @Temporal(javax.persistence.TemporalType.DATE)
    Date desde, hasta;
    
    @ManyToOne
    Jugador jugadorObjetivo;

    public Jugador getJugadorObjetivo() {
        return jugadorObjetivo;
    }

    public void setJugadorObjetivo(Jugador jugadorObjetivo) {
        this.jugadorObjetivo = jugadorObjetivo;
    }
    
    
    
    String condicion;
    
    Double porcentaje;

    public Double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(Double porcentaje) {
        this.porcentaje = porcentaje;
    }
    
    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }
    
    

    public Club getOrigen() {
        return origen;
    }

    public void setOrigen(Club origen) {
        this.origen = origen;
    }

    public Club getDestino() {
        return destino;
    }

    public void setDestino(Club destino) {
        this.destino = destino;
    }

    public Double getMontoDeOperacion() {
        return montoDeOperacion;
    }

    public void setMontoDeOperacion(Double montoDeOperacion) {
        this.montoDeOperacion = montoDeOperacion;
    }

    public Date getDesde() {
        return desde;
    }

    public void setDesde(Date desde) {
        this.desde = desde;
    }

    public Date getHasta() {
        return hasta;
    }

    public void setHasta(Date hasta) {
        this.hasta = hasta;
    }

}
