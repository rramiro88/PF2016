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
import entidades.PosicionLiga;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author ramiro
 */
@Named
@ApplicationScoped
public class LogicaLiga {

    @Inject
    LigaDAO ligaDAO;

    public List<PosicionLiga> obtenerPosicionesLiga(Liga liga) {

        ArrayList<PosicionLiga> respuesta = new ArrayList<>();

        HashMap<Long, PosicionLiga> puntajes = new HashMap<>();

        for (Club participante : liga.getEquiposParticipantes()) {
            puntajes.put(participante.getId(), new PosicionLiga());
        }

        for (Partido partido : liga.getPartidos()) {

            if (partido.isJugado()) {

                PosicionLiga plLocal = puntajes.get(partido.getLocal().getId());
                PosicionLiga plVisitante = puntajes.get(partido.getVisitante().getId());

                //actualizo goles a favor, en contra y partidos jugados del local
                plLocal.setGolesAFavor(plLocal.getGolesAFavor() + partido.getGolesLocal());
                plLocal.setGolesEnContra(plLocal.getGolesEnContra() + partido.getGolesVisitantes());
                plLocal.setPj(plLocal.getPj() + 1);
                
                
                //actualizo goles a favor, en contra y partidos jugados del visitante
                plVisitante.setGolesAFavor(plVisitante.getGolesAFavor() + partido.getGolesVisitantes());
                plVisitante.setGolesEnContra(plVisitante.getGolesEnContra() + partido.getGolesLocal());
                plVisitante.setPj(plVisitante.getPj() + 1);
                
                
                //si ganó el local:
                if (partido.getGolesLocal() > partido.getGolesVisitantes()) {

                    plLocal.setPg(plLocal.getPg() + 1);
                    plLocal.setPuntaje(plLocal.getPuntaje() + 3);
                    
                    plVisitante.setPp(plVisitante.getPp() + 1);
                    
                } else if (partido.getGolesLocal() < partido.getGolesVisitantes()) {
                    
                    plVisitante.setPg(plVisitante.getPg() + 1);
                    plVisitante.setPuntaje(plVisitante.getPuntaje()+ 3);
                    
                    
                    plLocal.setPp(plLocal.getPp() + 1);
                } else {
                    plLocal.setPuntaje(plLocal.getPuntaje() + 1);
                    plLocal.setPe(plLocal.getPe() + 1);
                    
                    plVisitante.setPuntaje(plVisitante.getPuntaje()+ 1);
                    plVisitante.setPe(plVisitante.getPe() + 1);
                }
                
                
                puntajes.put(partido.getLocal().getId(), plLocal);
                puntajes.put(partido.getVisitante().getId(), plVisitante);
                
                
                
            }

        }

        List<Entry<Long, PosicionLiga>> ordenada = ordenarRanking(puntajes);

        for (Entry<Long, PosicionLiga> o : ordenada) {
            
            PosicionLiga pl = o.getValue();
            
            pl.setClub(obtenerClubParticipantePorId(o.getKey(), liga));
            
            respuesta.add(pl);
            
        }

        return respuesta;
    }

    private List<Entry<Long, PosicionLiga>> ordenarRanking(Map<Long, PosicionLiga> mapaDesordenado) {

        List<Entry<Long, PosicionLiga>> lista = new LinkedList<>(mapaDesordenado.entrySet());

        Collections.sort(lista, new Comparator<Entry<Long, PosicionLiga>>() {

            @Override
            public int compare(Entry<Long, PosicionLiga> o1, Entry<Long, PosicionLiga> o2) {
                return (-1) * o1.getValue().getPuntaje().compareTo(o2.getValue().getPuntaje());
                
            }
        });

        return lista;

    }

    private Club obtenerClubParticipantePorId(Long id, Liga liga) {

        for (Club club : liga.getEquiposParticipantes()) {
            if (id.equals(club.getId())) {
                return club;
            }
        }

        return null;
    }

    public void crearLiga(Liga liga) {
        ligaDAO.persistirLiga(liga);
    }

}
