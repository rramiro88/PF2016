/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entidades.Jugador;
import logica.LogicaTactica;
import entidades.Tactica;
import java.io.Serializable;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author ramiro
 */
@Named
@RequestScoped
public class TacticaController implements Serializable {

    @Inject
    LogicaTactica logicaTactica;

    @Inject
    SesionController sesionController;

    String posicion;

    private String posicion1, posicion2, posicion3, posicion4, posicion5, posicion6, posicion7, posicion8, posicion9, posicion10, posicion11;

    private String opcion;

    public void agregarJugador(Jugador jugador, String posicion) {

        sesionController.getUsuarioLogueado().getClub().getTacticas().get(0).agregarJugador(jugador, posicion);

        dibujarJugadores();

    }

    public void cargarTactica() {
        if (sesionController.getUsuarioLogueado().getClub().getTacticas().size() > 0) {

            Tactica tactica = sesionController.getUsuarioLogueado().getClub().getTacticas().get(0);

            posicion1 = tactica.getPosiciones().get(0);
            posicion2 = tactica.getPosiciones().get(1);
            posicion3 = tactica.getPosiciones().get(2);
            posicion4 = tactica.getPosiciones().get(3);
            posicion5 = tactica.getPosiciones().get(4);
            posicion6 = tactica.getPosiciones().get(5);
            posicion7 = tactica.getPosiciones().get(6);
            posicion8 = tactica.getPosiciones().get(7);
            posicion9 = tactica.getPosiciones().get(8);
            posicion10 = tactica.getPosiciones().get(9);
            posicion11 = tactica.getPosiciones().get(10);

        }

        RequestContext.getCurrentInstance().execute("cargarPosicionesActuales()");
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }

    public String getPosicion1() {
        return posicion1;
    }

    public void setPosicion1(String posicion1) {
        this.posicion1 = posicion1;
    }

    public String getPosicion2() {
        return posicion2;
    }

    public void setPosicion2(String posicion2) {
        this.posicion2 = posicion2;
    }

    public String getPosicion3() {
        return posicion3;
    }

    public void setPosicion3(String posicion3) {
        this.posicion3 = posicion3;
    }

    public String getPosicion4() {
        return posicion4;
    }

    public void setPosicion4(String posicion4) {
        this.posicion4 = posicion4;
    }

    public String getPosicion5() {
        return posicion5;
    }

    public void setPosicion5(String posicion5) {
        this.posicion5 = posicion5;
    }

    public String getPosicion6() {
        return posicion6;
    }

    public void setPosicion6(String posicion6) {
        this.posicion6 = posicion6;
    }

    public String getPosicion7() {
        return posicion7;
    }

    public void setPosicion7(String posicion7) {
        this.posicion7 = posicion7;
    }

    public String getPosicion8() {
        return posicion8;
    }

    public void setPosicion8(String posicion8) {
        this.posicion8 = posicion8;
    }

    public String getPosicion9() {
        return posicion9;
    }

    public void setPosicion9(String posicion9) {
        this.posicion9 = posicion9;
    }

    public String getPosicion10() {
        return posicion10;
    }

    public void setPosicion10(String posicion10) {
        this.posicion10 = posicion10;
    }

    public String getPosicion11() {
        return posicion11;
    }

    public void setPosicion11(String posicion11) {
        this.posicion11 = posicion11;
    }

    public void guardarPosiciones() {

        Tactica tactica = sesionController.getUsuarioLogueado().getClub().getTacticas().get(0);

        tactica.setNombre(opcion);

        tactica.getPosiciones().set(0, posicion1);
        tactica.getPosiciones().set(1, posicion2);
        tactica.getPosiciones().set(2, posicion3);
        tactica.getPosiciones().set(3, posicion4);
        tactica.getPosiciones().set(4, posicion5);
        tactica.getPosiciones().set(5, posicion6);
        tactica.getPosiciones().set(6, posicion7);
        tactica.getPosiciones().set(7, posicion8);
        tactica.getPosiciones().set(8, posicion9);
        tactica.getPosiciones().set(9, posicion10);
        tactica.getPosiciones().set(10, posicion11);

        for (int i = 0; i < 11; i++) {

            // tactica.getTitularidad().put(sesionController.getUsuarioLogueado().getClub().getPlantel().get(i).getId(), Boolean.TRUE);
        }

        sesionController.getUsuarioLogueado().getClub().getTacticas().set(0, tactica);

        sesionController.actualizarUsuario();

    }
    
    public void guardarTactica(){
        
        Tactica tactica = sesionController.getUsuarioLogueado().getClub().getTacticas().get(0);
        
        if(tactica.getTitulares().size()>11){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No puede haber mas de once jugadores en cancha"));
        }else{
            sesionController.actualizarUsuario();
        }
        
        
    }
    
   

    public void dibujarJugadores() {
        RequestContext.getCurrentInstance().execute("limpiarCanvas()");
       
        

        Tactica tactica = sesionController.getUsuarioLogueado().getClub().getTacticas().get(0);

        for (Map.Entry<Long, String> pos : tactica.getPosicionesEnCancha().entrySet()) {
            
            
            Jugador j = tactica.obtenerJugadorPorID(pos.getKey());
            

            switch (pos.getValue()) {

                case Tactica.ARQUERO: {
                    RequestContext.getCurrentInstance().execute("dibujarJugador(160,470,"+j.getNumeroCamiseta()+")");
                    break;
                }
                case Tactica.DEFENSA_CENTRAL1: {
                    RequestContext.getCurrentInstance().execute("dibujarJugador(110,420,"+j.getNumeroCamiseta()+")");
                    break;
                }
                case Tactica.DEFENSA_CENTRAL2: {
                    RequestContext.getCurrentInstance().execute("dibujarJugador(210,420,"+j.getNumeroCamiseta()+")");
                    break;
                }
                case Tactica.LATERAL_DERECHO: {
                    RequestContext.getCurrentInstance().execute("dibujarJugador(280,400,"+j.getNumeroCamiseta()+")");
                    break;
                }
                case Tactica.LATERAL_IZQUIERDO: {
                    RequestContext.getCurrentInstance().execute("dibujarJugador(40,400,"+j.getNumeroCamiseta()+")");
                    break;
                }
                case Tactica.MEDIO_CENTRO1: {
                    RequestContext.getCurrentInstance().execute("dibujarJugador(140,290,"+j.getNumeroCamiseta()+")");
                    break;
                }
                case Tactica.MEDIO_CENTRO2: {
                    RequestContext.getCurrentInstance().execute("dibujarJugador(180,290,"+j.getNumeroCamiseta()+")");
                    break;
                }
                case Tactica.MEDIO_DERECHO: {
                    RequestContext.getCurrentInstance().execute("dibujarJugador(240,220,"+j.getNumeroCamiseta()+")");
                    break;
                }
                case Tactica.MEDIO_IZQUIERDO: {
                    RequestContext.getCurrentInstance().execute("dibujarJugador(80,220,"+j.getNumeroCamiseta()+")");
                    break;
                }
                case Tactica.MEDIAPUNTA: {
                    RequestContext.getCurrentInstance().execute("dibujarJugador(160,180,"+j.getNumeroCamiseta()+")");
                    break;
                }
                case Tactica.DELANTERO_CENTRO1: {
                    RequestContext.getCurrentInstance().execute("dibujarJugador(130,90,"+j.getNumeroCamiseta()+")");
                    break;
                }
                case Tactica.DELANTERO_CENTRO2: {
                    RequestContext.getCurrentInstance().execute("dibujarJugador(190,90,"+j.getNumeroCamiseta()+")");
                    break;
                }
                case Tactica.DELANTERO_DERECHO: {
                    RequestContext.getCurrentInstance().execute("dibujarJugador(270,90,"+j.getNumeroCamiseta()+")");
                    break;
                }
                case Tactica.DELANTERO_IZQUIERDO: {
                    RequestContext.getCurrentInstance().execute("dibujarJugador(50,90,"+j.getNumeroCamiseta()+")");
                    break;
                }
                case Tactica.SUPLENTE1: {
                    RequestContext.getCurrentInstance().execute("dibujarJugador(340,60,"+j.getNumeroCamiseta()+")");
                    break;
                }
                case Tactica.SUPLENTE2: {
                    RequestContext.getCurrentInstance().execute("dibujarJugador(340,120,"+j.getNumeroCamiseta()+")");
                    break;
                }
                case Tactica.SUPLENTE3: {
                    RequestContext.getCurrentInstance().execute("dibujarJugador(340,180,"+j.getNumeroCamiseta()+")");
                    break;
                }
                case Tactica.SUPLENTE4: {
                    RequestContext.getCurrentInstance().execute("dibujarJugador(340,240,"+j.getNumeroCamiseta()+")");
                    break;
                }
                case Tactica.SUPLENTE5: {
                    RequestContext.getCurrentInstance().execute("dibujarJugador(340,300,"+j.getNumeroCamiseta()+")");
                    break;
                }
                

            }

        }

    }

}
