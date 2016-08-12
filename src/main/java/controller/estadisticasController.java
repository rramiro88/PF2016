/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import javax.annotation.PostConstruct;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
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

    @PostConstruct
    public void init() {
        createAnimatedModels();
    }

    private void createAnimatedModels() {
        modeloAnimadoLineas = initLinearModel();
        modeloAnimadoLineas.setTitle("Grafico economico/financiero");
        modeloAnimadoLineas.setAnimate(true);
        modeloAnimadoLineas.setLegendPosition("se");
        Axis yAxis = modeloAnimadoLineas.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(10);

    }

    private LineChartModel initLinearModel() {
        LineChartModel model = new LineChartModel();

        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Ingresos por ventas");

        series1.set(1, 2);
        series1.set(2, 1);
        series1.set(3, 3);
        series1.set(4, 6);
        series1.set(5, 8);

        LineChartSeries series2 = new LineChartSeries();
        series2.setLabel("Egresos por compras");

        series2.set(1, 6);
        series2.set(2, 3);
        series2.set(3, 2);
        series2.set(4, 7);
        series2.set(5, 9);

        model.addSeries(series1);
        model.addSeries(series2);

        return model;
    }

    public LineChartModel getModeloAnimadoLineas() {
        return modeloAnimadoLineas;
    }

    public void setModeloAnimadoLineas(LineChartModel modeloAnimadoLineas) {
        this.modeloAnimadoLineas = modeloAnimadoLineas;
    }

}
