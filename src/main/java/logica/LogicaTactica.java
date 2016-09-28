/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import entidades.Club;
import entidades.Jugador;
import entidades.Oferta;
import entidades.Tactica;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author ramiro
 */
@Named
@SessionScoped
public class LogicaTactica implements Serializable {

    void reorganizarTacticas(Oferta oferta) {

        Club comprador = oferta.getOrigen();
        Club vendedor = oferta.getDestino();
        Jugador jugador = oferta.getJugadorObjetivo();
        Tactica tacticaVendedor = vendedor.getTacticas().get(0);

        tacticaVendedor.quitarJugadorDeTactica(jugador.getId());

        jugador.setNumeroCamiseta(comprador.getNumerosLibres().get(0));

    }

}
