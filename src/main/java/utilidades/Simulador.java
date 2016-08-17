/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import entidades.Jugador;
import java.util.List;

/**
 *
 * @author Alumno
 */
public class Simulador {

    
    
    public static int simular(List<Jugador> jugadoresLocal,List<Jugador> jugadoresVisitante){
        int resultado = 0;
        int l = 0;
        int v = 0;
        
        
        
        
        int cantidad = jugadoresLocal.size();
        
        if(jugadoresLocal.size()<11){
            System.out.println("cantidad inferior a la minima en tactica de "+jugadoresLocal.get(0).getClub().getNombre());
            return -1;
        }
         if(jugadoresVisitante.size()<11){
            System.out.println("cantidad inferior a la minima en tactica de "+jugadoresVisitante.get(0).getClub().getNombre());
            return -1;
         }
        
        for(int i = 0; i<cantidad; i++){
            if(jugadoresLocal.get(i).suma()>jugadoresVisitante.get(i).suma()){
                resultado ++;
                
            }
            if(jugadoresLocal.get(i).suma()<jugadoresVisitante.get(i).suma()){
                resultado --;
                
            }
            
            System.out.println("local "+jugadoresLocal.get(i).suma()+" visitante "+jugadoresVisitante.get(i).suma());
            l+=jugadoresLocal.get(i).suma();
            v+=jugadoresVisitante.get(i).suma();
        }
        
        System.out.println("Total "+l+" - "+v);
        return resultado;
        
    }
    
    
}
    
    
    

