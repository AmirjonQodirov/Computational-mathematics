package Model;

public class BisectionMethod {
    private double a;
    private double b;
    private Function function;
    private double accuracy;
    private int number_of_iteration;


    public BisectionMethod(double a, double b, Function function, double accuracy) {
        this.a = a;
        this.b = b;
        this.function = function;
        this.accuracy = accuracy;
    }

    public double countX(){
        double x;
        for (int i = 0; i < 20000; i++) {
            x = a*0.5+b*0.5;
            if (function.F(a) * function.F(x) < 0){
                b = x;
            }else{
                a = x;
            }
            number_of_iteration++;
            if (Math.abs(a-b) < accuracy) {
                break;
            }
        }

        return (a+b)/2;
    }

    public int getNumber_of_iteration() {
        return number_of_iteration;
    }

}
