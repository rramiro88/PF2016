/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import entidades.Jugador;
import entidades.Tactica;
import java.util.List;

/**
 *
 * @author Ramiro
 */
public class Simulador {

    public static int[] simular(Tactica tacticaLocal, Tactica tacticaVisitante) {

        int[] error = {-1, -1};
        int[] resultado = {0, 0};

        List<Jugador> titularesLocal = tacticaLocal.getTitulares();
        List<Jugador> titularesVisitante = tacticaVisitante.getTitulares();

        List<Jugador> defensoresLocal = tacticaLocal.getDefensores();
        List<Jugador> defensoresVisitante = tacticaVisitante.getDefensores();

        List<Jugador> mediosLocal = tacticaLocal.getMediocampistas();
        List<Jugador> mediosVisitante = tacticaVisitante.getMediocampistas();

        List<Jugador> delanterosLocal = tacticaLocal.getDelanteros();
        List<Jugador> delanterosVisitante = tacticaVisitante.getDelanteros();

        //comienzo reglas
        
        //regla 1 - regate delanteros vs entradas defensores
        if(calcularPromedio(delanterosLocal,"REGATE") > calcularPromedio(defensoresVisitante,"ENTRADAS")){
            resultado[0]++;
        }
        
        if(calcularPromedio(delanterosVisitante,"REGATE") > calcularPromedio(defensoresLocal,"ENTRADAS")){
            resultado[1]++;
        }
        
        //regla 2 - pases cortos mediocampo
        
        if(calcularPromedio(mediosLocal,"PASES_CORTOS") > calcularPromedio(mediosVisitante,"PASES_CORTOS")){
            resultado[0]++;
        }else if(calcularPromedio(mediosLocal,"PASES_CORTOS") < calcularPromedio(mediosVisitante,"PASES_CORTOS")){
            resultado[1]++;
        }
        
        //TODO seguir agregando reglas y aleatoriedad
        
        
        return resultado;

    }

    private static double calcularPromedio(List<Jugador> jugadores, String habilidad) {

        int suma = 0;
        int cantidad = jugadores.size();

        switch (habilidad) {

            case "REGATE":
                for (Jugador jugador : jugadores) {
                    suma += jugador.getRegate();
                }
                break;

            case "ENTRADAS":
                for (Jugador jugador : jugadores) {
                    suma += jugador.getEntradas();
                }
                break;

            case "ARQUERO":
                for (Jugador jugador : jugadores) {
                    suma += jugador.getArquero();
                }
                break;

            case "CABEZAZO":
                for (Jugador jugador : jugadores) {
                    suma += jugador.getCabezazo();
                }
                break;
            case "MARCA":
                for (Jugador jugador : jugadores) {
                    suma += jugador.getMarca();
                }
                break;
            case "MENTALIDAD":
                for (Jugador jugador : jugadores) {
                    suma += jugador.getMentalidad();
                }
                break;
            case "PASES_CORTOS":
                for (Jugador jugador : jugadores) {
                    suma += jugador.getPasesCortos();
                }
                break;
            case "PASES_LARGOS":
                for (Jugador jugador : jugadores) {
                    suma += jugador.getPasesLargos();
                }
                break;
            case "PELOTA_PARADA":
                for (Jugador jugador : jugadores) {
                    suma += jugador.getPelotaParada();
                }
                break;
            case "POTENCIA_TIRO":
                for (Jugador jugador : jugadores) {
                    suma += jugador.getPotenciaTiro();
                }
                break;
            case "RESISITENCIA":
                for (Jugador jugador : jugadores) {
                    suma += jugador.getResistencia();
                }
                break;
            case "VELOCIDAD":
                for (Jugador jugador : jugadores) {
                    suma += jugador.getVelocidad();
                }
                break;

        }

        return suma / cantidad;
    }

}
