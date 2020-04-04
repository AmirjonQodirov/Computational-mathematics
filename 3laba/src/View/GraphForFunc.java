package View;
import Model.Function;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

public class GraphForFunc {
    private LineChart lineChart;
    private double a,b;
    private Function f;


    public GraphForFunc(LineChart lineChart, double a, double b, Function f){
        this.lineChart = lineChart;
        this.a = a;
        this.b = b;
        this.f = f;

    }

    public void draw(){
        XYChart.Series series = new XYChart.Series();
        for (double i = a; i < b ; i+=(b-a)/50) {
            series.getData().add(new XYChart.Data(i,f.F(i)));
        }
        series.getData().add(new XYChart.Data(b,f.F(b)));
        series.setName(f.toString());
        lineChart.getData().add(series);
    }

    public LineChart getLineChart() {
        return lineChart;
    }
}
