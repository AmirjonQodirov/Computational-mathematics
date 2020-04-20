package Model;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

public class DrawGraph {
    private LineChart lineChart;
    private double a,b;
    private Function f;


    public DrawGraph(LineChart lineChart, double a, double b, Function f){
        this.lineChart = lineChart;
        this.a = a;
        this.b = b;
        this.f = f;
    }

    public void draw(){
        f.countCoefficients();
        XYChart.Series series = new XYChart.Series();
        XYChart.Series seriesforInterpolation = new XYChart.Series();

        for (double i = a; i < b ; i+=(b-a)/50) {
            series.getData().add(new XYChart.Data(i,f.F(i)));
            seriesforInterpolation.getData().add(new XYChart.Data(i,f.Interpolate(i)));

        }
        series.getData().add(new XYChart.Data(b,f.F(b)));
        seriesforInterpolation.getData().add(new XYChart.Data(b,f.Interpolate(b)));
        series.setName(f.toString());
        seriesforInterpolation.setName("Интерполяционная функция для " + f.toString());

        lineChart.getData().add(series);
        lineChart.getData().add(seriesforInterpolation);

    }

    public LineChart getLineChart() {
        return lineChart;
    }
}