/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entidades.TransaccionEconomica;
import java.io.Serializable;
import java.util.Date;
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
        series1.setLabel("Gastos / Ingresos");

        TransaccionEconomica t = null;
        for (int i = 0; i < sesionController.getUsuarioLogueado().getClub().getTransacciones().size(); i++) {

            t = sesionController.getUsuarioLogueado().getClub().getTransacciones().get(i);

            series1.set(t.getFecha().toString(), t.getMonto());

        }

//        LineChartSeries series2 = new LineChartSeries();
//        series2.setLabel("Egresos por compras");
//
//        series2.set(1, 6);
//        series2.set(2, 3);
//        series2.set(3, 2);
//        series2.set(4, 7);
//        series2.set(5, 9);
        model.addSeries(series1);
//        model.addSeries(series2);

        DateAxis axis = new DateAxis("Dates");
        axis.setTickAngle(-50);

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
