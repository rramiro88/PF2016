/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import entidades.Jugador;
import entidades.Partido;
import entidades.Tactica;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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

        List<Partido> ultimosPartidosLocal = new ArrayList<>();
        List<Partido> ultimosPartidosVisitante = new ArrayList<>();

        //comienzo reglas
        //regla 1 - regate delanteros vs entradas defensores
        if (calcularPromedio(delanterosLocal, "REGATE") > calcularPromedio(defensoresVisitante, "ENTRADAS")) {
            resultado[0]++;
        }

        if (calcularPromedio(delanterosVisitante, "REGATE") > calcularPromedio(defensoresLocal, "ENTRADAS")) {
            resultado[1]++;
        }

        //regla 2 - pases cortos mediocampo        
        if (calcularPromedio(mediosLocal, "PASES_CORTOS") > calcularPromedio(mediosVisitante, "PASES_CORTOS")) {
            resultado[0]++;
        } else if (calcularPromedio(mediosLocal, "PASES_CORTOS") < calcularPromedio(mediosVisitante, "PASES_CORTOS")) {
            resultado[1]++;
        }

        //regla 3 - mentalidad general
        if (calcularPromedio(titularesLocal, "MENTALIDAD") > calcularPromedio(titularesVisitante, "MENTALIDAD")) {
            resultado[0]++;
        } else if (calcularPromedio(titularesLocal, "MENTALIDAD") < calcularPromedio(titularesVisitante, "MENTALIDAD")) {
            resultado[1]++;
        }

        //regla 4 - arqueros vs precision delanteros
        if (calcularPromedio(delanterosLocal, "PRECISION_TIRO") > tacticaVisitante.getArquero().getArquero()) {
            resultado[0]++;
        } else if (calcularPromedio(delanterosLocal, "PRECISION_TIRO") < tacticaVisitante.getArquero().getArquero()) {
            resultado[0]--;
        }

        if (calcularPromedio(delanterosVisitante, "PRECISION_TIRO") > tacticaLocal.getArquero().getArquero()) {
            resultado[1]++;
        } else if (calcularPromedio(delanterosVisitante, "PRECISION_TIRO") < tacticaLocal.getArquero().getArquero()) {
            resultado[1]--;
        }

        //regla 5 - racha de triunfos/derrotas
        int longitudRacha = 3;

        List<Integer> rachaLocal = tacticaLocal.getClub().racha(longitudRacha);
        List<Integer> rachaVisitante = tacticaVisitante.getClub().racha(longitudRacha);
        int sumaLocal = 0, sumaVisitante = 0;

       for (Integer m : rachaLocal) {
           sumaLocal+=m;
        }

        for (Integer n : rachaVisitante) {
            sumaVisitante+=n;
        }

        

        if (sumaLocal == longitudRacha) { //esto significa 3 partidos ganados al hilo
            resultado[0]++;
        }
        if (sumaLocal == -longitudRacha) { //esto significa 3 partidos perdidos al hilo
            resultado[0]--;
        }

        if (sumaVisitante == longitudRacha) { //esto significa 3 partidos ganados al hilo
            resultado[1]++;
        }
        if (sumaVisitante == -longitudRacha) { //esto significa 3 partidos perdidos al hilo
            resultado[1]--;
        }

        //regla 6 - factor suerte
        resultado[0] += ThreadLocalRandom.current().nextInt(0, 3);
        resultado[1] += ThreadLocalRandom.current().nextInt(0, 3);

        //me aseguro que no vaya a devolver resultado negativo
        if (resultado[0] < 0) {
            resultado[0] = 0;
        }
        if (resultado[1] < 1) {
            resultado[1] = 0;
        }

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
            case "PRECISION_TIRO":
                for (Jugador jugador : jugadores) {
                    suma += jugador.getPrecisionTiro();
                }
                break;

        }

        return suma / cantidad;
    }

}
