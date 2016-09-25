/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 *
 * @author ramiro
 */

@Entity
public class TransaccionEconomica implements Serializable {
    
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    
    
    
    private String descripcion;
    private Double monto;
    
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public TransaccionEconomica(String descripcion, Double monto, Date fecha) {
        this.descripcion = descripcion;
        this.monto = monto;
        this.fecha = fecha;
    }
    
    public TransaccionEconomica(){
        
    }
    
    
}
