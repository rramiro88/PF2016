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
import entidades.TransaccionEconomica;
import java.io.Serializable;
import java.util.Calendar;
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
    
    @Inject
    LogicaFinanzas logicaFinanzas;
    
    @Inject
    JugadorDAO jugadorDAO;
    
    @Inject
    ClubDAO clubDAO;
    
    @Inject
    PartidosDAO partidosDAO;
    
    public void cargarJugadoresDB() {
        
        jugadorDAO.crearJugadoresAlAzar();
        
    }
    
    public void avanzarUnDia() {
        
        Calendar calendario = Calendar.getInstance();
        
        List<Partido> partidosASimular = partidosDAO.obtenerPartidosDeHoy();
        
        this.simularPartidos(partidosASimular);
        
        List<Club> clubes = buscarClubesPorNombre("");
        
        for (Club club : clubes) {
            entrenar(club);
            revisarPrestamos(club);

            //Si es primero de mes, pagamos los sueldos
            if (calendario.get(Calendar.DAY_OF_MONTH) == 1) {
                pagarSueldos(club);
            }
            
            clubDAO.actualizarClub(club);
        }
        
    }
    
    public void simularPartidos(List<Partido> partidosASimular) {
        
        int[] resultado;
        
        for (Partido p : partidosASimular) {
            
            if (!p.isJugado()) {
                resultado = Simulador.simular(p.getLocal().getTacticas().get(0), p.getVisitante().getTacticas().get(0));
                
                p.setGolesLocal(resultado[0]);
                p.setGolesVisitantes(resultado[1]);
                
                p.setJugado(true);
            }
            
            repartirDineroEntradas(p);
            
            partidosDAO.actualizarPartido(p);
            
        }
    }
    
    public List<Club> buscarClubesPorNombre(String nombreClub) {
        
        return clubDAO.obtenerClubesPorNombre(nombreClub);
    }
    
    private void entrenar(Club club) {
        
        LogicaEntrenamiento logicaEntrenamiento = new LogicaEntrenamiento();
        
        logicaEntrenamiento.calcularProgresos(club.getPlantel());
        
    }
    
    private void revisarPrestamos(Club club) {
        List<Prestamo> prestamos = club.getPrestamos();
        
        for (Prestamo prestamo : prestamos) {
            
            System.out.println("COMPARE:::::::::::::::::::::::" + prestamo.getHasta().compareTo(new Date()));
            if (prestamo.getHasta().compareTo(new Date()) <= 0) {
                logicaMercado.devolverJugador(prestamo, club);
                prestamos.remove(prestamo);
            }
            if (prestamo.getDesde().compareTo(new Date()) <= 0) {
                logicaMercado.prestarJugador(prestamo, club);
                
            }
            
        }
    }
    
    private void pagarSueldos(Club club) {
        
        Double montoSueldos = logicaFinanzas.calcularGastoMensual(club);
        club.setPresupuesto(club.getPresupuesto() - montoSueldos);
        club.getTransacciones().add(new TransaccionEconomica("Pago de sueldos", -montoSueldos, new Date()));
        
    }
    
    private void repartirDineroEntradas(Partido p) {
        
        int concurrencia = p.getConcurrencia();
        Double montoEntradas = concurrencia * 150.0;
        p.getLocal().setPresupuesto(p.getLocal().getPresupuesto() + montoEntradas);
        p.getLocal().getTransacciones().add(new TransaccionEconomica("Ingreso por entradas", montoEntradas, new Date()));
        
    }
    
}
