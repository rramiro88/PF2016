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
import javax.persistence.Transient;

@Entity
public class Tactica implements Serializable {

    
    @Transient
    public static final String ARQUERO = "ARQ";
    @Transient
    public static final String LATERAL_IZQUIERDO = "LI";
    @Transient
    public static final String DEFENSA_CENTRAL1 = "CT1";
    @Transient
    public static final String DEFENSA_CENTRAL2 = "CT2";
    @Transient
    public static final String LATERAL_DERECHO = "LD";
    @Transient
    public static final String MEDIO_IZQUIERDO = "MI";
    @Transient
    public static final String MEDIO_CENTRO1 = "MC1";
    @Transient
    public static final String MEDIO_CENTRO2 = "MC2";
    @Transient
    public static final String MEDIO_DERECHO = "MD";
    @Transient
    public static final String MEDIAPUNTA = "MP";
    @Transient
    public static final String DELANTERO_IZQUIERDO = "DI";
    @Transient
    public static final String DELANTERO_DERECHO = "DD";
    @Transient
    public static final String DELANTERO_CENTRO1 = "DC1";
    @Transient
    public static final String DELANTERO_CENTRO2 = "DC2";
    @Transient
    public static final String SUPLENTE = "SUP";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @ElementCollection(fetch = FetchType.EAGER)
    private Map<Long, String> posicionesEnCancha;

    String nombre;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> posicionesGraficas;

    @ManyToOne
    private Club club;

    public Tactica() {
        posicionesEnCancha = new HashMap<>();

    }

    public List<Jugador> getTitulares() {
        List<Jugador> respuesta = new ArrayList<>();

        for (Map.Entry<Long, String> pos : posicionesEnCancha.entrySet()) {

            if (!pos.getValue().equals(Tactica.SUPLENTE)) {
                for (Jugador jugador : club.getPlantel()) {
                    if (jugador.getId().equals(pos.getKey())) {
                        respuesta.add(jugador);
                    }
                }
            }

        }

        return respuesta;
    }

    public List<Jugador> getConvocados() {
        List<Jugador> respuesta = new ArrayList<>();

        for (Map.Entry<Long, String> pos : posicionesEnCancha.entrySet()) {

            for (Jugador jugador : club.getPlantel()) {
                if (jugador.getId().equals(pos.getKey())) {
                    respuesta.add(jugador);
                }
            }

        }

        return respuesta;
    }

    public List<Jugador> getNoConvocados() {
        ArrayList<Jugador> copiaPlantel = (ArrayList) ((ArrayList) club.getPlantel()).clone();

        copiaPlantel.removeAll(getConvocados());
        return copiaPlantel;

    }

   

    public void agregarJugador(Jugador jugador, String posicion) {

        String posicionAntigua = posicionesEnCancha.get(jugador.getId());
        
        if (posicionAntigua != null) { //si ya estaba en la tactica
            intercambiar(jugador,posicion,posicionAntigua);
        }else{
            posicionar(jugador, posicion);
        }


        System.out.println("TACTICA:" + this);
        

        
    }

   

   

    @Override
    public String toString() {
        
        
        String cadena = "Cantidad de titulares: " +getTitulares().size()+"\n";
        
        for (Map.Entry<Long, String> pos : posicionesEnCancha.entrySet()) {

            for (Jugador jugador : club.getPlantel()) {
                if (jugador.getId().equals(pos.getKey())) {
                    cadena+="\n"+jugador.getNombre() + " - " + pos.getValue();
                }
            }

        }
        
        
        return cadena;
    }



    
   
    private void intercambiar(Jugador jugador, String posicion, String posicionAnterior) {
        
        
        posicionesEnCancha.remove(jugador.getId());
        
        for (Map.Entry<Long, String> pos : posicionesEnCancha.entrySet()) {
            if(posicion.equals(pos.getValue())){
        
                pos.setValue(posicionAnterior);
            }
        }
        
        posicionesEnCancha.put(jugador.getId(), posicion);
      
        
    }

    private void posicionar(Jugador jugador, String posicion) {
        
        Long key=null;
         for (Map.Entry<Long, String> pos : posicionesEnCancha.entrySet()) {
            if(posicion.equals(pos.getValue())){
                
                key = pos.getKey();
                
            }
        }
         
         posicionesEnCancha.remove(key);
         posicionesEnCancha.put(jugador.getId(), posicion);
        
        
    }

    
     public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public Map<Long, String> getPosicionesEnCancha() {
        return posicionesEnCancha;
    }

    public void setPosicionesEnCancha(HashMap<Long, String> posicionesEnCancha) {
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

}

