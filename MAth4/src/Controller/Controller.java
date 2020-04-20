package Controller;

import Model.DrawGraph;
import Model.Function;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    Function f;
    double[] points;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AnswerBox.setVisible(false);
        graph.getStylesheets().addAll(getClass().getResource("../View/style.css").toString());
    }

    @FXML
    public void Solve() {
        XYChart.Series series = new XYChart.Series();
        XYChart.Series series_answer = new XYChart.Series();

        DrawGraph gr;
        Double X = Double.parseDouble(x.getText());
        graph.getData().removeAll(graph.getData());
        if (f1p1.isSelected() || f1p2.isSelected() || f1p3.isSelected()) {
            if (f1p1.isSelected()) {
                points = new double[]
                        {-2 * Math.PI, -Math.PI, 0.0, Math.PI, 2 * Math.PI};
            } else {
                points = new double[]
                        {-2 * Math.PI, -1.6 * Math.PI, -1.2 * Math.PI, -0.8 * Math.PI, -0.4 * Math.PI, 0.0, 0.4 * Math.PI, 0.8 * Math.PI, 1.2 * Math.PI, 1.6 * Math.PI, 2 * Math.PI};
            }
            f = new Function(1, points);
            if (f1p3.isSelected()) {
                f.setLastPointEqualF(true);
                for (int i = 0; i < points.length/2; i++) {
                    series.getData().add(new XYChart.Data(points[i], f.F(points[i])));
                }
                series.getData().add(new XYChart.Data(0, -3));
                for (int i = points.length/2 + 1; i < points.length; i++) {
                    series.getData().add(new XYChart.Data(points[i], f.F(points[i])));
                }
            }else{
                for (int i = 0; i < points.length; i++) {
                    series.getData().add(new XYChart.Data(points[i], f.F(points[i])));
                }
            }
            gr = new DrawGraph(graph, -2 * Math.PI, 2 * Math.PI, f);

        }
        else if (f2p1.isSelected() || f2p2.isSelected() || f2p3.isSelected()) {
            if (f2p1.isSelected()) {
                points = new double[]{-6.0, -4.0, -2.0, 0.0, 2.0, 4.0};
            } else {
                points = new double[]{-6.0, -5.0, -4.0, -3.0, -2.0, -1.0, 0.0, 1.0, 2.0, 3.0, 4.0};
            }
            f = new Function(2, points);
            if (f2p3.isSelected()) {
                f.setLastPointEqualF(true);
                for (int i = 0; i < points.length/2; i++) {
                    series.getData().add(new XYChart.Data(points[i], f.F(points[i])));
                }
                series.getData().add(new XYChart.Data(-1, 0));
                for (int i = points.length/2 + 1; i < points.length; i++) {
                    series.getData().add(new XYChart.Data(points[i], f.F(points[i])));
                }
            }else{
                for (int i = 0; i < points.length; i++) {
                    series.getData().add(new XYChart.Data(points[i], f.F(points[i])));
                }
            }
            gr = new DrawGraph(graph, -6, 4, f);
        } else {
            if (f3p1.isSelected()) {
                points = new double[]{-5.0, 4.0, 13.0};
            } else {
                points = new double[]{-5.0, -2.0, 1.0, 4.0, 7.0, 10.0, 13.0};
            }
            f = new Function(3,points);
            if (f3p3.isSelected()) {
                f.setLastPointEqualF(true);
                for (int i = 0; i < points.length/2; i++) {
                    series.getData().add(new XYChart.Data(points[i], f.F(points[i])));
                }
                series.getData().add(new XYChart.Data(4, 4));
                for (int i = points.length/2 + 1; i < points.length; i++) {
                    series.getData().add(new XYChart.Data(points[i], f.F(points[i])));
                }
            }else{
                for (int i = 0; i < points.length; i++) {
                    series.getData().add(new XYChart.Data(points[i], f.F(points[i])));
                }
            }
            gr = new DrawGraph(graph, -5, 13, f);
        }
        series.setName("Набор точек");
        gr.draw();
        graph = gr.getLineChart();
        graph.getData().add(series);
        double Y;
        if (X != null){
            if(!(f1p3.isSelected() || f2p3.isSelected() || f3p3.isSelected())) {
                Y = f.Interpolate(X);
            }else{
                if(f1p3.isSelected()){
                    if(X == 0){
                        Y = -3;
                    }else{
                        Y = f.Interpolate(X);
                    }
                }else if(f2p3.isSelected()){
                    if(X == -1){
                        Y = 0;
                    }else{
                        Y = f.Interpolate(X);
                    }
                }else{
                    if(X == 4){
                        Y = 4;
                    }else{
                        Y = f.Interpolate(X);
                    }
                }
            }
            String Answer = "x: " + X +
                    "\n" +
                    "y: " + Y;


            series_answer.getData().add(new XYChart.Data(X,Y));
            series_answer.setName("Ответ");
            graph.getData().add(series_answer);
            graph.setVisible(true);
            answer.setText(Answer);
            answer.setEditable(false);
            AnswerBox.setVisible(true);
        }
    }

    @FXML
    private LineChart graph;
    @FXML
    private TextField x;
    @FXML
    private RadioButton f1p1, f1p2, f1p3, f2p1, f2p2,f2p3, f3p1,f3p2,f3p3;
    @FXML
    private VBox AnswerBox;
    @FXML
    private TextArea answer;

}
