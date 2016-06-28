/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

    @ManyToMany
    List<Club> equiposParticipantes;

    public void organizar() {

        for (int i = 0; i < equiposParticipantes.size(); i++) {

            for (int j = 0; j < equiposParticipantes.size() && j != i; j++) {

                Partido p = new Partido();

                if (i % 2 != 1) {
                    p.setLocal(equiposParticipantes.get(i));
                    p.setVisitante(equiposParticipantes.get(j));
                } else {
                    p.setLocal(equiposParticipantes.get(j));
                    p.setVisitante(equiposParticipantes.get(i));
                }

                p.setFecha(Date.valueOf(LocalDate.now()));

                partidos.add(p);

            }

        }

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

}
