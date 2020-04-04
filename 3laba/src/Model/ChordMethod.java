package Model;

public class ChordMethod {
    private double a;
    private double b;
    private Function function;
    private double accuracy;
    private int number_of_iteration;

    public ChordMethod(double a, double b, Function function, double accuracy) {
        this.a = a;
        this.b = b;
        this.function = function;
        this.accuracy = accuracy;
    }

    public double countX(){
        double c;
        double x_next = 0;
        double x;
        if(function.F(a)*function.D2F(a) < 0){
            x = a;
            c = b;
        }else{
            x = b;
            c = a;
        }
        for (int i = 0; i < 20000; i++) {
            x_next = x - (function.F(x)*(c-x)) / (function.F(c) - function.F(x));
            number_of_iteration ++;
            if(Math.abs(function.F(x_next)) < accuracy){
                break;
            }
            x = x_next;
        }
        return x_next;

    }

    public int getNumber_of_iteration() {
        return number_of_iteration;
    }

}

