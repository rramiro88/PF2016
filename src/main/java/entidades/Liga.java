/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 *
 * @author ramiro
 */
@Entity
public class Liga implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    String nombre;

    @OneToMany(mappedBy = "liga", cascade = CascadeType.ALL)
    List<Partido> partidos;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "liga_id")
    List<Club> equiposParticipantes;

    /**
     * Organiza la liga. Crea los partidos, organiza el fixture, coloca en cada
     * partido una fecha de juego, para que luego puedan simularse ese dia dado.
     *
     */
    public void organizar() {

        int diasAdelante = 1;

        for (int i = 0; i < equiposParticipantes.size(); i++) {

            for (int j = 0; j < equiposParticipantes.size(); j++) {

                if (i != j) {
                    Partido p = new Partido();

                    p.setLocal(equiposParticipantes.get(i));
                    p.setVisitante(equiposParticipantes.get(j));

                    /**
                     * Se programan los dias para que se jueguen en dias 
                     * consecutivos a partir de hoy
                     */
                    p.setFecha(sumarRestarDiasFecha(new Date(), diasAdelante));
                    diasAdelante++;

                    p.setConcurrencia(p.getLocal().getEstadio().getCapacidad());

                    if (!repetido(p)) {
                        partidos.add(p);
                        p.setLiga(this);
                    }
                }

            }

        }

    }

    public Liga() {
        partidos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Partido> getPartidos() {
        return partidos;
    }

    public void setPartidos(List<Partido> partidos) {
        this.partidos = partidos;
    }

    public List<Club> getEquiposParticipantes() {
        return equiposParticipantes;
    }

    public void setEquiposParticipantes(List<Club> equiposParticipantes) {
        this.equiposParticipantes = equiposParticipantes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Comprueba si el partido dado como parametro ya se encuentra calendarizado
     * en la lista de partidos.
     *
     * @param p partido a comprobar si se repite
     * @return true si esta repetido, falso en caso contrario
     */
    private boolean repetido(Partido p) {
        boolean respuesta = false;

        for (Partido partido : partidos) {
            if (p.getLocal().equals(partido.getLocal()) && p.getVisitante().equals(partido.getVisitante())
                    || (p.getLocal().equals(partido.getVisitante()) && p.getVisitante().equals(partido.getLocal()))) {
                respuesta = true;
            }
        }

        return respuesta;
    }

    public Date sumarRestarDiasFecha(Date fecha, int dias) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.DAY_OF_YEAR, dias);  
        return calendar.getTime(); 
    }

}
