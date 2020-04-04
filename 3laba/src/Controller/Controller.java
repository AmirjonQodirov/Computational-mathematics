package Controller;

import Model.*;
import View.GraphForFunc;
import View.GraphForSys;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private Function f;
    private BisectionMethod bisectionMethod;
    private ChordMethod chordMethod;
    private SystemEquations systemEquations;


    @FXML
    public void Tab1() {
        boolean bool = false;
        double a_double = Double.parseDouble(a.getText());
        double b_double = Double.parseDouble(b.getText());
        double accuracy_double = Double.parseDouble(accuracy.getText());
        accuracy_double /= 10;
        if (b_double < a_double) {
            double t = a_double;
            a_double = b_double;
            b_double = t;
        }
        f = getF();

        if (((f.getNum() == 2) && ((a_double < -1) && (b_double > 0.5))) || ((f.getNum() == 3) && ((a_double < -2) && (b_double > 1)))) {
            bool = true;
            if (f.getNum() == 3) {
                createMethod(a_double, 1, f, accuracy_double);
            } else if (f.getNum() == 2) {
                createMethod(a_double, 0.5, f, accuracy_double);
            }
        } else {
            createMethod(a_double, b_double, f, accuracy_double);
        }

        graph.getData().removeAll(graph.getData());
        String answer;
        if (bool || (f.F(a_double) * f.F(b_double)) < 0 && (Math.abs(a_double) < 1000) && (Math.abs(b_double) < 1000) && (accuracy_double < 1 && accuracy_double > 0.00000001)) {
            double x = getX();
            x = BigDecimal.valueOf(x).setScale(7, RoundingMode.HALF_UP).doubleValue();
            double y = f.F(x);
            y = BigDecimal.valueOf(y).setScale(7, RoundingMode.HALF_UP).doubleValue();
            int iter_num = getNumbIter();
            answer =
                    "X: " + x + "\n" +
                            "F(X): " + y + "\n" +
                            "Количество итерации: " + iter_num;
            if (iter_num == 20000) {
                answer += "\n точность не достигнута!";
            }
            XYChart.Series series = new XYChart.Series();
            series.getData().add(new XYChart.Data(x, y));
            GraphForFunc gr = new GraphForFunc(graph, a_double, b_double, f);
            gr.draw();
            graph = gr.getLineChart();
            graph.getData().remove(series);
            series.setName(" x = " + x);
            graph.getData().add(series);
            graph.setVisible(true);
        } else if (!((Math.abs(a_double) < 1000) && (Math.abs(b_double) < 1000) && (accuracy_double < 1 && accuracy_double > 0.00000001))) {
            answer = "Некоректные данные" +
                    "\n" +
                    "|a,b| < 1000" +
                    "\n" +
                    "1 > n > 0.00000001";
        } else {
            answer = "Нет решерний!";
        }
        Answer.setText(answer);
        Answer.setEditable(false);
        Box_Answer.setVisible(true);
    }

    private void createMethod(double a, double b, Function f, double accuracy) {
        if (Method1.isSelected()) {
            bisectionMethod = new BisectionMethod(a, b, f, accuracy);
        } else {
            chordMethod = new ChordMethod(a, b, f, accuracy);
        }
    }

    private double getX() {
        double x;
        if (Method1.isSelected()) {
            x = bisectionMethod.countX();
        } else {
            x = chordMethod.countX();
        }
        return x;
    }

    private int getNumbIter() {
        int x;
        if (Method1.isSelected()) {
            x = bisectionMethod.getNumber_of_iteration();
        } else {
            x = chordMethod.getNumber_of_iteration();
        }
        return x;
    }

    private Function getF(){
        Function func = null;
        if (F1.isSelected()) {
            func = new Function(1);
        }
        if (F2.isSelected()) {
            func = new Function(2);
        }
        if (F3.isSelected()) {
            func = new Function(3);
        }
        if (F4.isSelected()) {
            func = new Function(4);
        }
        return func;
    }

    private SystemEquations getSystemEquations(){
        SystemEquations systemEquations = null;
        if (S1.isSelected()) {
            systemEquations = new SystemEquations(1);
        }
        if (S2.isSelected()) {
            systemEquations = new SystemEquations(2);
        }
        if (S3.isSelected()) {
            systemEquations = new SystemEquations(3);
        }
        if (S4.isSelected()) {
            systemEquations = new SystemEquations(4);
        }
        return systemEquations;
    }

    @FXML
    private RadioButton F1, F2, F3, F4, Method1;
    @FXML
    private TextField a, b, accuracy;
    @FXML
    private LineChart graph;
    @FXML
    private VBox Box_Answer;
    @FXML
    private TextArea Answer;

    @FXML
    public void Tab2() {
        XYChart.Series series = new XYChart.Series();
        graphForSys.getData().removeAll(graphForSys.getData());
        double x = Double.parseDouble(x0.getText());
        double y = Double.parseDouble(y0.getText());
        double accuracy_for_sys = Double.parseDouble(accuracy_for_systems.getText());
        accuracy_for_sys /= 10;
        systemEquations = getSystemEquations();

        NewtonMethod newtonMethod = new NewtonMethod(systemEquations, accuracy_for_sys, x, y);
        newtonMethod.solve();



        double[] ans = newtonMethod.getAnswer();
        ans[0] = BigDecimal.valueOf(ans[0]).setScale(4, RoundingMode.HALF_UP).doubleValue();
        ans[1] = BigDecimal.valueOf(ans[1]).setScale(4, RoundingMode.HALF_UP).doubleValue();

        series.getData().add(new XYChart.Data(ans[0], ans[1]));
        String answer = "x: " + ans[0] + "" +
                "\n" +
                "y: " + ans[1] + "" +
                "\n" +
                "Количество итерации: " + newtonMethod.getNumber_of_iteration();
        answer_for_sys.setText(answer);
        answer_for_sys.setEditable(false);
        Box_Answer_for_Sys.setVisible(true);

        GraphForSys gr = new GraphForSys(graphForSys,systemEquations);
        gr.draw();
        graphForSys = gr.getLineChart();
        graphForSys.getData().add(series);
        series.getNode().setStyle("-fx-stroke: #000;");
        graphForSys.setVisible(true);

    }

    @FXML
    private RadioButton S1, S2, S3, S4;
    @FXML
    private TextField x0, y0, accuracy_for_systems;
    @FXML
    private VBox Box_Answer_for_Sys;
    @FXML
    private TextArea answer_for_sys;
    @FXML
    private LineChart graphForSys;

    @FXML
    private void drawGraph() {
        graph.getData().removeAll(graph.getData());
        f = getF();
        GraphForFunc gr = new GraphForFunc(graph, -5, 5, f);
        gr.draw();
        graph = gr.getLineChart();
    }


    @FXML
    private void drawGraphForSys(){
        graphForSys.getData().removeAll(graphForSys.getData());
        systemEquations = getSystemEquations();
        GraphForSys gf = new GraphForSys(graphForSys,systemEquations);
        gf.draw();
        graphForSys = gf.getLineChart();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Box_Answer.setVisible(false);
        Box_Answer_for_Sys.setVisible(false);
        graph.getStylesheets().addAll(getClass().getResource("../View/style.css").toString());
        graphForSys.getStylesheets().addAll(getClass().getResource("../View/styleForSys.css").toString());

    }
}
