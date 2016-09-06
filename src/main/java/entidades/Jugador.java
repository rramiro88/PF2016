/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

/**
 *
 * @author ramiro
 */
@Entity
public class Jugador implements Serializable {

    @Transient
    public static final String DEFENSA = "defensa",
            CABEZA = "cabeza",
            VELOCIDAD = "velocidad",
            RESISTENCIA = "resistencia",
            PASES_CORTOS = "pases",
            PASES_LARGOS = "pelotazos",
            REMATES = "remates",
            ARQUERO = "arquero",
            MENTALIDAD = "mentalidad",
            REGATE = "regate",
            DESCANSO = "descanso";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombre;

    private String posicionTactica;

    private Integer numeroCamiseta;

    @ManyToOne
    @JoinColumn(name = "club_id", nullable = true)
    private Club club;

    private Double cotizacion;
    private Double salario;

    private int velocidad;
    private int resistencia;
    private int marca;
    private int entradas;
    private int precisionTiro;
    private int potenciaTiro;
    private int cabezazo;
    private int mentalidad;
    private int regate;
    private int arquero;
    private int pelotaParada;
    private int pasesCortos;
    private int pasesLargos;
    

    private int progresoVelocidad;
    private int progresoResistencia;
    private int progresoMarca;
    private int progresoEntradas;
    private int progresoPrecisionTiro;
    private int progresoPotenciaTiro;
    private int progresoCabezazo;
    private int progresoMentalidad;
    private int progresoRegate;
    private int progresoArquero;
    private int progresoPelotaParada;
    private int progresoPases;
    private int progresoPasesLargos;
    
    private String entrenando;

    public Jugador() {

        nombre = "Jugador de prueba";
        cotizacion = 0D;
        salario = 0D;

    }

    public int getProgresoPases() {
        return progresoPases;
    }

    public void setProgresoPases(int progresoPases) {
        this.progresoPases = progresoPases;
    }

    public int getProgresoPasesLargos() {
        return progresoPasesLargos;
    }

    public void setProgresoPasesLargos(int progresoPasesLargos) {
        this.progresoPasesLargos = progresoPasesLargos;
    }
    
    

    public int getPasesCortos() {
        return pasesCortos;
    }

    public void setPasesCortos(int pasesCortos) {
        this.pasesCortos = pasesCortos;
    }

    public int getPasesLargos() {
        return pasesLargos;
    }

    public void setPasesLargos(int pasesLargos) {
        this.pasesLargos = pasesLargos;
    }
    
    
   

    public String getEntrenando() {
        return entrenando;
    }

    public void setEntrenando(String entrenando) {
        this.entrenando = entrenando;
    }

    
    
    

    public int getProgresoVelocidad() {
        return progresoVelocidad;
    }

    public void setProgresoVelocidad(int progresoVelocidad) {
        this.progresoVelocidad = progresoVelocidad;
    }

    public int getProgresoResistencia() {
        return progresoResistencia;
    }

    public void setProgresoResistencia(int progresoResistencia) {
        this.progresoResistencia = progresoResistencia;
    }

    public int getProgresoMarca() {
        return progresoMarca;
    }

    public void setProgresoMarca(int progresoMarca) {
        this.progresoMarca = progresoMarca;
    }

    public int getProgresoEntradas() {
        return progresoEntradas;
    }

    public void setProgresoEntradas(int progresoEntradas) {
        this.progresoEntradas = progresoEntradas;
    }

    public int getProgresoPrecisionTiro() {
        return progresoPrecisionTiro;
    }

    public void setProgresoPrecisionTiro(int progresoPrecisionTiro) {
        this.progresoPrecisionTiro = progresoPrecisionTiro;
    }

    public int getProgresoPotenciaTiro() {
        return progresoPotenciaTiro;
    }

    public void setProgresoPotenciaTiro(int progresoPotenciaTiro) {
        this.progresoPotenciaTiro = progresoPotenciaTiro;
    }

    public int getProgresoCabezazo() {
        return progresoCabezazo;
    }

    public void setProgresoCabezazo(int progresoCabezazo) {
        this.progresoCabezazo = progresoCabezazo;
    }

    public int getProgresoMentalidad() {
        return progresoMentalidad;
    }

    public void setProgresoMentalidad(int progresoMentalidad) {
        this.progresoMentalidad = progresoMentalidad;
    }

    public int getProgresoRegate() {
        return progresoRegate;
    }

    public void setProgresoRegate(int progresoRegate) {
        this.progresoRegate = progresoRegate;
    }

    public int getProgresoArquero() {
        return progresoArquero;
    }

    public void setProgresoArquero(int progresoArquero) {
        this.progresoArquero = progresoArquero;
    }

    public int getProgresoPelotaParada() {
        return progresoPelotaParada;
    }

    public void setProgresoPelotaParada(int progresoPelotaParada) {
        this.progresoPelotaParada = progresoPelotaParada;
    }

    public Integer getNumeroCamiseta() {
        return numeroCamiseta;
    }

    public void setNumeroCamiseta(Integer numeroCamiseta) {
        this.numeroCamiseta = numeroCamiseta;
    }

    public String getPosicionTactica() {
        return posicionTactica;
    }

    public void setPosicionTactica(String posicionTactica) {
        this.posicionTactica = posicionTactica;
    }

    public int suma() {
        return arquero + cabezazo + entradas + marca + mentalidad + pelotaParada + potenciaTiro + precisionTiro + regate + resistencia + velocidad;
    }

    public String getPosicion() {
        return posicionTactica;
    }

    public void setPosicion(String posicionTactica) {
        this.posicionTactica = posicionTactica;
    }

    public String getNombre() {
        return nombre;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public Double getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(Double cotizacion) {
        this.cotizacion = cotizacion;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public int getResistencia() {
        return resistencia;
    }

    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }

    public int getMarca() {
        return marca;
    }

    public void setMarca(int marca) {
        this.marca = marca;
    }

    public int getEntradas() {
        return entradas;
    }

    public void setEntradas(int entradas) {
        this.entradas = entradas;
    }

    public int getPrecisionTiro() {
        return precisionTiro;
    }

    public void setPrecisionTiro(int precisionTiro) {
        this.precisionTiro = precisionTiro;
    }

    public int getPotenciaTiro() {
        return potenciaTiro;
    }

    public void setPotenciaTiro(int potenciaTiro) {
        this.potenciaTiro = potenciaTiro;
    }

    public int getCabezazo() {
        return cabezazo;
    }

    public void setCabezazo(int cabezazo) {
        this.cabezazo = cabezazo;
    }

    public int getMentalidad() {
        return mentalidad;
    }

    public void setMentalidad(int mentalidad) {
        this.mentalidad = mentalidad;
    }

    public int getRegate() {
        return regate;
    }

    public void setRegate(int regate) {
        this.regate = regate;
    }

    public int getArquero() {
        return arquero;
    }

    public void setArquero(int arquero) {
        this.arquero = arquero;
    }

    public int getPelotaParada() {
        return pelotaParada;
    }

    public void setPelotaParada(int pelotaParada) {
        this.pelotaParada = pelotaParada;
    }

}
