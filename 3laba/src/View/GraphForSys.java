package View;

import Model.SystemEquations;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

public class GraphForSys {
    private LineChart lineChart;
    private SystemEquations systemEquations;

    public GraphForSys(LineChart lineChart, SystemEquations systemEquations) {
        this.lineChart = lineChart;
        this.systemEquations = systemEquations;
    }

    public void draw() {
        XYChart.Series series1 = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();
        XYChart.Series series3 = new XYChart.Series();
        XYChart.Series series4 = new XYChart.Series();
        if (systemEquations.getNum() == 1) {
            for (double i = -10.29; i < 0.29; i += 0.01) {
                double y = Math.pow((1.5 - 0.5 * i * i - 5 * i), 0.5);
                series1.getData().add(new XYChart.Data(i, y));
                series3.getData().add(new XYChart.Data(i, -y));
            }
            for (double i = -11; i < 1; i += 0.01) {
                double y2 = ((-0.2 * i * i + 0.7) / (1 - 0.1 * i));
                series2.getData().add(new XYChart.Data(i, y2));
            }
        } else if (systemEquations.getNum() == 2) {
            for (double i = -3; i < 3; i += 0.01) {
                double y = Math.pow((9 - i * i), 0.5);
                series1.getData().add(new XYChart.Data(i, y));
                series3.getData().add(new XYChart.Data(i, -y));
            }
            for (double i = -1.5; i < 1.5; i += 0.01) {
                double y2 = i * i * i;
                series2.getData().add(new XYChart.Data(i, y2));
            }
        } else if (systemEquations.getNum() == 3) {
            for (double i = -7; i < 1; i += 0.01) {
                double y1 = Math.sin(i) / i;
                double y2 = (i + 4) * (i + 4) - 3;
                series1.getData().add(new XYChart.Data(i, y1));
                series2.getData().add(new XYChart.Data(i, y2));
            }
        }else{
            for (double i = 0.1; i < 4; i += 0.01) {
                double y1 = Math.pow(i,1.5);
                double y2 = Math.pow(5*i,0.5);
                series1.getData().add(new XYChart.Data(i, y1));
                series2.getData().add(new XYChart.Data(i, y2));
                series3.getData().add(new XYChart.Data(i, -y1));
                series4.getData().add(new XYChart.Data(i, -y2));
            }

        }
        series1.setName(systemEquations.toStringF1());
        series2.setName(systemEquations.toStringF2());
        lineChart.getData().add(series1);
        lineChart.getData().add(series2);
        lineChart.getData().add(series3);
        lineChart.getData().add(series4);
    }

    public LineChart getLineChart() {
        return lineChart;
    }
}
