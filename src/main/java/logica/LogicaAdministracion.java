/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import dao.ClubDAO;
import dao.JugadorDAO;
import dao.PartidosDAO;
import entidades.Club;
import entidades.Jugador;
import entidades.Partido;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import utilidades.Simulador;

/**
 *
 * @author ramiro
 */
@Named
@ApplicationScoped
public class LogicaAdministracion implements Serializable {

    public void cargarJugadoresDB() {

        JugadorDAO jugadorDAO = new JugadorDAO();

        jugadorDAO.crearJugadoresAlAzar();

    }

    /**
     * Metodo no testeado 28/06/2016
     */
    public void avanzarUnDia() {

        PartidosDAO partidosDAO = new PartidosDAO();

        List<Partido> partidosASimular = partidosDAO.obtenerPartidosDeHoy();

        this.simularPartidos(partidosASimular);

        entrenar();

    }

    public void simularPartidos(List<Partido> partidosASimular) {
        PartidosDAO partidosDAO = new PartidosDAO();
        int diferenciaGoles;

        for (Partido p : partidosASimular) {

            diferenciaGoles = Simulador.simular(p.getLocal().getTacticas().get(0).getTitulares(), p.getVisitante().getTacticas().get(0).getTitulares());

            if (diferenciaGoles > 0) {
                p.setGolesLocal(diferenciaGoles);
                p.setGolesVisitantes(0);
            } else if (diferenciaGoles < 0) {
                p.setGolesLocal(0);
                p.setGolesVisitantes(-1 * diferenciaGoles);
            } else {
                p.setGolesLocal(0);
                p.setGolesVisitantes(0);
            }

            partidosDAO.actualizarPartido(p);

        }
    }

    public List<Club> buscarClubesPorNombre(String nombreClub) {
        ClubDAO clubDAO = new ClubDAO();

        return clubDAO.obtenerClubesPorNombre(nombreClub);
    }

    private void entrenar() {

        List<Club> clubes;
        LogicaEntrenamiento logicaEntrenamiento = new LogicaEntrenamiento();

        ClubDAO clubDAO = new ClubDAO();
        clubes = clubDAO.obtenerClubesPorNombre("");

        for (Club club : clubes) {

            logicaEntrenamiento.calcularProgresos(club.getPlantel());

        }

    }

}
