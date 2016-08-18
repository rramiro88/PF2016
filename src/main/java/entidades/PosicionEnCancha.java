/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 *
 * @author alumno
 */
@Entity
public class PosicionEnCancha implements Serializable {

    @Transient
    public static final String ARQUERO = "ARQ";
    @Transient
    public static final String LATERAL_IZQUIERDO = "LI";
    @Transient
    public static final String DEFENSA_CENTRAL = "CT";
    @Transient
    public static final String LATERAL_DERECHO = "LD";
    @Transient
    public static final String MEDIO_IZQUIERDO = "MI";
    @Transient
    public static final String MEDIO_CENTRO = "MC";
    @Transient
    public static final String MEDIO_DERECHO = "MD";
    @Transient
    public static final String MEDIAPUNTA = "MP";
    @Transient
    public static final String DELANTERO_IZQUIERDO = "DI";
    @Transient
    public static final String DELANTERO_DERECHO = "DD";
    @Transient
    public static final String DELANTERO_CENTRO = "DC";
    @Transient
    public static final String SUPLENTE = "SUP";

    private Long idJugador;
    private String posicionDetallada;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public PosicionEnCancha(Long id, String pos) {
        this.idJugador = id;
        this.posicionDetallada = pos;
    }

    public PosicionEnCancha() {

    }

    public Long getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(Long idJugador) {
        this.idJugador = idJugador;
    }

    public String getPosicionDetallada() {
        return posicionDetallada;
    }

    public void setPosicionDetallada(String posicionDetallada) {
        this.posicionDetallada = posicionDetallada;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
