/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.proyectofinal.controller;

import ar.proyectofinal.logica.LogicaMercado;
import entidades.Club;
import entidades.Jugador;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.inject.Inject;

/**
 *
 * @author ramiro
 */
@Named(value = "mercadoController")
@ApplicationScoped
public class MercadoController {

    @Inject
    private LogicaMercado miLogicaMercado;
    
    
    private List<Jugador> listaDeLibres;
    
    public MercadoController() {
        
    }
    
    public List<Jugador> listarLibres(){
        return miLogicaMercado.listarLibres();
    }

    public List<Jugador> getListaDeLibres() {
        return listaDeLibres;
    }

    public void setListaDeLibres(List<Jugador> listaDeLibres) {
        this.listaDeLibres = listaDeLibres;
    }
    
    public void transferir(Jugador j, Club c, int condicion){
        
        miLogicaMercado.transferir(j,c,condicion);
        System.out.println("en controller!");
        
        this.listaDeLibres.remove(j);

        
    }

    public void liberarJugador(Jugador j, Club c){
        
        
        miLogicaMercado.liberarJugador(j,c);
        this.listaDeLibres = listarLibres();
        
    }
    
    @PostConstruct
    public void inicializar(){
        listaDeLibres = listarLibres();
    }
    
    
    
}
