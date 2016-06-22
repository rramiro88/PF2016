/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.proyectofinal.controller;


import ar.proyectofinal.logica.LogicaTactica;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author ramiro
 */

@Named
@SessionScoped
public class TacticaController implements Serializable{
    
    @Inject
    LogicaTactica logicaTactica;
    
    private String posicion1,posicion2,posicion3,posicion4,posicion5,posicion6,posicion7,posicion8,posicion9,posicion10,posicion11;

    public String getPosicion1() {
        return posicion1;
    }

    public void setPosicion1(String posicion1) {
        this.posicion1 = posicion1;
    }

    public String getPosicion2() {
        return posicion2;
    }

    public void setPosicion2(String posicion2) {
        this.posicion2 = posicion2;
    }

    public String getPosicion3() {
        return posicion3;
    }

    public void setPosicion3(String posicion3) {
        this.posicion3 = posicion3;
    }

    public String getPosicion4() {
        return posicion4;
    }

    public void setPosicion4(String posicion4) {
        this.posicion4 = posicion4;
    }

    public String getPosicion5() {
        return posicion5;
    }

    public void setPosicion5(String posicion5) {
        this.posicion5 = posicion5;
    }

    public String getPosicion6() {
        return posicion6;
    }

    public void setPosicion6(String posicion6) {
        this.posicion6 = posicion6;
    }

    public String getPosicion7() {
        return posicion7;
    }

    public void setPosicion7(String posicion7) {
        this.posicion7 = posicion7;
    }

    public String getPosicion8() {
        return posicion8;
    }

    public void setPosicion8(String posicion8) {
        this.posicion8 = posicion8;
    }

    public String getPosicion9() {
        return posicion9;
    }

    public void setPosicion9(String posicion9) {
        this.posicion9 = posicion9;
    }

    public String getPosicion10() {
        return posicion10;
    }

    public void setPosicion10(String posicion10) {
        this.posicion10 = posicion10;
    }

    public String getPosicion11() {
        return posicion11;
    }

    public void setPosicion11(String posicion11) {
        this.posicion11 = posicion11;
    }

   
    public void guardarPosiciones(){
        System.out.println(posicion1+" - "+posicion2);
    }
    
    
}
