/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entidades.TransaccionEconomica;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

/**
 *
 * @author ramiro
 */
@Named
@SessionScoped
public class estadisticasController implements Serializable {

    private LineChartModel modeloAnimadoLineas;

    @Inject
    SesionController sesionController;

    @PostConstruct
    public void init() {
        createAnimatedModels();
    }

    private void createAnimatedModels() {
        modeloAnimadoLineas = initLinearModel();
        modeloAnimadoLineas.setTitle("Grafico economico/financiero");
        modeloAnimadoLineas.setAnimate(true);
        modeloAnimadoLineas.setLegendPosition("se");

    }

    private LineChartModel initLinearModel() {
        LineChartModel model = new LineChartModel();

        model.getAxis(AxisType.X).setLabel("Dias");
        model.getAxis(AxisType.Y).setLabel("$");

        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Capital Actual");
        
        Map<Object,Number> datos = new HashMap<>();

        TransaccionEconomica t = null;
        Double capital = 1000000D;
        
        for (int i = 0; i < sesionController.getUsuarioLogueado().getClub().getTransacciones().size(); i++) {

            t = sesionController.getUsuarioLogueado().getClub().getTransacciones().get(i);
            
            capital += t.getMonto();
           
            
            // si coinciden las fechas, muestro los datos agrupados
            if(datos.get(t.getFecha())!=null){
                datos.put(t.getFecha().toString(), capital + (Double)datos.get(t.getFecha().toString()));
            }else{
                datos.put(t.getFecha().toString(), capital); 
            }
            
            
        }

        series1.setData(datos);

        model.addSeries(series1);


        DateAxis axis = new DateAxis("Fechas");


        model.getAxes().put(AxisType.X, axis);

        return model;
    }

    public LineChartModel getModeloAnimadoLineas() {
        return modeloAnimadoLineas;
    }

    public void setModeloAnimadoLineas(LineChartModel modeloAnimadoLineas) {
        this.modeloAnimadoLineas = modeloAnimadoLineas;
    }

}
