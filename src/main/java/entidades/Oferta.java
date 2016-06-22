/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
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
    public static final int VENTA = 1;
    @Transient
    public static final int PRESTAMO = 2;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    
    
    @ManyToOne
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
    
    
    
    Integer condicion;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getCondicion() {
        return condicion;
    }

    public void setCondicion(Integer condicion) {
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
