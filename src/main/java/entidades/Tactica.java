/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Tactica implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<PosicionEnCancha> posicionesEnCancha;

    String nombre;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> posicionesGraficas;

    @ManyToOne
    private Club club;

    public Tactica() {
        posicionesEnCancha = new ArrayList<>();

    }

    public List<Jugador> getTitulares() {
        List<Jugador> respuesta = new ArrayList<>();

        for (PosicionEnCancha pos : posicionesEnCancha) {
            if (!pos.getPosicionDetallada().equals(PosicionEnCancha.SUPLENTE)) {

                for (Jugador jugador : club.getPlantel()) {
                    if (jugador.getId().equals(pos.getIdJugador())) {
                        respuesta.add(jugador);
                    }
                }

            }
        }

        return respuesta;
    }

    public List<Jugador> getConvocados() {
        List<Jugador> respuesta = new ArrayList<>();

        for (PosicionEnCancha pos : posicionesEnCancha) {

            for (Jugador jugador : club.getPlantel()) {
                if (jugador.getId().equals(pos.getIdJugador())) {
                    respuesta.add(jugador);
                }
            }

        }

        return respuesta;
    }

    public List<Jugador> getNoConvocados() {
        ArrayList<Jugador> copiaPlantel = (ArrayList)((ArrayList)club.getPlantel()).clone();

        
        copiaPlantel.removeAll(getConvocados());
        return copiaPlantel;
        
        
        
    }

    public String getTopPosicion(int indice) {

        String sub = null;

        sub = posicionesGraficas.get(indice).split("a")[1];

        return sub;
    }

    public String getLeftPosicion(int indice) {

        String sub = null;

        sub = posicionesGraficas.get(indice).split("a")[0];

        return sub;
    }

    public void agregarJugador(Jugador jugador, String posicion) {

        int arq = 0, li = 0, ld = 0, ct = 0, md = 0, mi = 0, mc = 0, mp = 0, dd = 0, di = 0, dc = 0, sup = 0;

        for (PosicionEnCancha pos : posicionesEnCancha) {
            switch (pos.getPosicionDetallada()) {
                case PosicionEnCancha.ARQUERO: {
                    arq++;
                    break;
                }
                case PosicionEnCancha.SUPLENTE: {
                    sup++;
                    break;
                }
                case PosicionEnCancha.LATERAL_IZQUIERDO: {
                    li++;
                    break;
                }
                case PosicionEnCancha.LATERAL_DERECHO: {
                    ld++;
                    break;
                }
                case PosicionEnCancha.DEFENSA_CENTRAL: {
                    ct++;
                    break;
                }
                case PosicionEnCancha.MEDIO_DERECHO: {
                    md++;
                    break;
                }
                case PosicionEnCancha.MEDIO_IZQUIERDO: {
                    mi++;
                    break;
                }
                case PosicionEnCancha.MEDIO_CENTRO: {
                    mc++;
                    break;
                }
                case PosicionEnCancha.MEDIAPUNTA: {
                    mp++;
                    break;
                }
                case PosicionEnCancha.DELANTERO_DERECHO: {
                    dd++;
                    break;
                }
                case PosicionEnCancha.DELANTERO_IZQUIERDO: {
                    di++;
                    break;
                }
                case PosicionEnCancha.DELANTERO_CENTRO: {
                    dc++;
                    break;
                }
            }

            switch (posicion) {
                case PosicionEnCancha.ARQUERO: {
                    if (arq > 0) {
                        reemplazar(jugador, posicion);
                    } else {
                        posicionesEnCancha.add(new PosicionEnCancha(jugador.getId(), posicion));
                    }
                    break;
                }
                case PosicionEnCancha.SUPLENTE: {
                    if (sup > 4) {
                        reemplazar(jugador, posicion);
                    } else {
                        posicionesEnCancha.add(new PosicionEnCancha(jugador.getId(), posicion));
                    }
                    break;
                }
                case PosicionEnCancha.LATERAL_IZQUIERDO: {
                    if (li > 0) {
                        reemplazar(jugador, posicion);
                    } else {
                        posicionesEnCancha.add(new PosicionEnCancha(jugador.getId(), posicion));
                    }
                    break;
                }
                case PosicionEnCancha.LATERAL_DERECHO: {
                    if (ld > 0) {
                        reemplazar(jugador, posicion);
                    } else {
                        posicionesEnCancha.add(new PosicionEnCancha(jugador.getId(), posicion));
                    }
                    break;
                }
                case PosicionEnCancha.DEFENSA_CENTRAL: {
                    if (ct > 1) {
                        reemplazar(jugador, posicion);
                    } else {
                        posicionesEnCancha.add(new PosicionEnCancha(jugador.getId(), posicion));
                    }
                    break;
                }
                case PosicionEnCancha.MEDIO_DERECHO: {
                    if (md > 0) {
                        reemplazar(jugador, posicion);
                    } else {
                        posicionesEnCancha.add(new PosicionEnCancha(jugador.getId(), posicion));
                    }
                    break;
                }
                case PosicionEnCancha.MEDIO_IZQUIERDO: {
                    if (mi > 0) {
                        reemplazar(jugador, posicion);
                    } else {
                        posicionesEnCancha.add(new PosicionEnCancha(jugador.getId(), posicion));
                    }
                    break;
                }
                case PosicionEnCancha.MEDIO_CENTRO: {
                    if (mc > 1) {
                        reemplazar(jugador, posicion);
                    } else {
                        posicionesEnCancha.add(new PosicionEnCancha(jugador.getId(), posicion));
                    }
                    break;
                }
                case PosicionEnCancha.MEDIAPUNTA: {
                    if (mp > 1) {
                        reemplazar(jugador, posicion);
                    } else {
                        posicionesEnCancha.add(new PosicionEnCancha(jugador.getId(), posicion));
                    }
                    break;
                }
                case PosicionEnCancha.DELANTERO_DERECHO: {
                    if (dd > 0) {
                        reemplazar(jugador, posicion);
                    } else {
                        posicionesEnCancha.add(new PosicionEnCancha(jugador.getId(), posicion));
                    }
                    break;
                }
                case PosicionEnCancha.DELANTERO_IZQUIERDO: {
                    if (di > 0) {
                        reemplazar(jugador, posicion);
                    } else {
                        posicionesEnCancha.add(new PosicionEnCancha(jugador.getId(), posicion));
                    }
                    break;
                }
                case PosicionEnCancha.DELANTERO_CENTRO: {
                    if (dc > 0) {
                        reemplazar(jugador, posicion);
                    } else {
                        posicionesEnCancha.add(new PosicionEnCancha(jugador.getId(), posicion));
                    }
                    break;
                }
            }
        }
        System.out.println("FIN DEL METODO AGREGARJUGADOR. TAMAÑO DE POSICIONES: " + posicionesEnCancha.size());
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public List<PosicionEnCancha> getPosicionesEnCancha() {
        return posicionesEnCancha;
    }

    public void setPosicionesEnCancha(List<PosicionEnCancha> posicionesEnCancha) {
        this.posicionesEnCancha = posicionesEnCancha;
    }

    public List<String> getPosicionesGraficas() {
        return posicionesGraficas;
    }

    public void setPosicionesGraficas(List<String> posicionesGraficas) {
        this.posicionesGraficas = posicionesGraficas;
    }

    public List<String> getPosiciones() {
        return posicionesGraficas;
    }

    public void setPosiciones(List<String> posiciones) {
        this.posicionesGraficas = posiciones;
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

    private void reemplazar(Jugador jugador, String posicion) {
        for (PosicionEnCancha pos : posicionesEnCancha) {
            if (pos.getPosicionDetallada().equals(posicion)) {
                pos.setIdJugador(jugador.getId());
            }
        }
    }

}
