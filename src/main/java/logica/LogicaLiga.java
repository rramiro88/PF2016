/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import dao.LigaDAO;
import entidades.Club;
import entidades.Liga;
import entidades.Partido;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author ramiro
 */
@Named
@ApplicationScoped
public class LogicaLiga {
    
    
    
    private LigaDAO ligaDAO = new LigaDAO();
    
    

    public List<String> obtenerPosicionesLiga(Liga liga) {

        ArrayList<String> respuesta = new ArrayList<>();

        HashMap<Long, Integer> puntajes = new HashMap<>();

        for (Club participante : liga.getEquiposParticipantes()) {
            puntajes.put(participante.getId(), 0);
        }

        for (Partido partido : liga.getPartidos()) {
            
            if(partido.getGolesLocal()>partido.getGolesVisitantes()){
                puntajes.put(partido.getLocal().getId(), puntajes.get(partido.getLocal().getId())+3);
            }else if(partido.getGolesLocal() < partido.getGolesVisitantes()){
                puntajes.put(partido.getVisitante().getId(), puntajes.get(partido.getVisitante().getId())+3);
            }else{
                puntajes.put(partido.getLocal().getId(), puntajes.get(partido.getLocal().getId())+1);
                puntajes.put(partido.getVisitante().getId(), puntajes.get(partido.getVisitante().getId())+1);
            }
            
            
        }

        
        
        
        
        List<Entry<Long,Integer>> ordenada = ordenarRanking(puntajes);
        
        for (Entry<Long, Integer> o : ordenada) {
            respuesta.add(obtenerClubParticipantePorId(o.getKey(), liga).getNombre() + " - " + o.getValue()+" puntos");
        }
        
        
        

        
        return respuesta;
    }
    
    
    private List<Entry<Long,Integer>> ordenarRanking(Map<Long,Integer> mapaDesordenado){
        
        
        List<Entry<Long,Integer>> lista = new LinkedList<>(mapaDesordenado.entrySet());
        
        Collections.sort(lista, new Comparator<Entry<Long,Integer>>() {

            @Override
            public int compare(Entry<Long, Integer> o1, Entry<Long, Integer> o2) {
                return (-1) * o1.getValue().compareTo(o2.getValue());
            }
        });
        
        
        return lista;
        
    }
    
    private Club obtenerClubParticipantePorId(Long id, Liga liga){
        
        
        for (Club club : liga.getEquiposParticipantes()) {
            if(id.equals(club.getId())){
                return club;
            }
        }
        
        return null;
    }

    public void crearLiga(Liga liga) {
        ligaDAO.persistirLiga(liga);
    }

}
