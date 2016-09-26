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
import entidades.Partido;
import entidades.Prestamo;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import utilidades.Simulador;

/**
 *
 * @author ramiro
 */
@Named
@ApplicationScoped
public class LogicaAdministracion implements Serializable {
    
    
    @Inject
    LogicaMercado logicaMercado;

    public void cargarJugadoresDB() {

        JugadorDAO jugadorDAO = new JugadorDAO();

        jugadorDAO.crearJugadoresAlAzar();

    }

    public void avanzarUnDia() {

        PartidosDAO partidosDAO = new PartidosDAO();

        List<Partido> partidosASimular = partidosDAO.obtenerPartidosDeHoy();

        this.simularPartidos(partidosASimular);

        List<Club> clubes = buscarClubesPorNombre("");

        for (Club club : clubes) {
            entrenar(club);
            revisarPrestamos(club);
        }

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

    private void entrenar(Club club) {

        LogicaEntrenamiento logicaEntrenamiento = new LogicaEntrenamiento();

        logicaEntrenamiento.calcularProgresos(club.getPlantel());

    }

    private void revisarPrestamos(Club club) {
        List<Prestamo> prestamos = club.getPrestamos();
        
        for (Prestamo prestamo : prestamos) {
            
            System.out.println("COMPARE:::::::::::::::::::::::"+   prestamo.getHasta().compareTo(new Date()));
            if(prestamo.getHasta().compareTo(new Date())<=0){
                logicaMercado.devolverJugador(prestamo, club);
                prestamos.remove(prestamo);
            }
            if(prestamo.getDesde().compareTo(new Date())<=0){
                logicaMercado.prestarJugador(prestamo, club);
                
            }
            
            
            
        }
    }

}
