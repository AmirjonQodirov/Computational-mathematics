package Model;

public class NewtonMethod {
    SystemEquations s;
    private double accuracy;
    private double x0;
    private double y0;
    private int number_of_iteration;
    private double[] answer = new double[2];

    public NewtonMethod(SystemEquations s, double accuracy, double x0, double y0) {
        this.s = s;
        this.accuracy = accuracy;
        this.x0 = x0;
        this.y0 = y0;
    }

    public void solve() {
        double x = x0;
        double y = y0;
        double x_next = 0;
        double y_next = 0;

        for (int i = 0; i < 20000; i++) {
            x_next = x - determinant(s.F1(x, y), s.dF1_Y(x, y), s.F2(x, y), s.dF2_Y(x, y)) / determinant(s.dF1_X(x, y), s.dF1_Y(x, y), s.dF2_X(x, y), s.dF2_Y(x, y));
            y_next = y - determinant(s.dF1_X(x, y), s.F1(x, y), s.dF2_X(x, y), s.F2(x, y)) / determinant(s.dF1_X(x, y), s.dF1_Y(x, y), s.dF2_X(x, y), s.dF2_Y(x, y));

            number_of_iteration++;

            if (Math.max((Math.abs(x_next - x)), (Math.abs(y_next - y))) < accuracy) {
                break;
            }

            x = x_next;
            y = y_next;
        }
        answer[0] = x_next;
        answer[1] = y_next;

    }

    public int getNumber_of_iteration() {
        return number_of_iteration;
    }

    public double[] getAnswer() {
        return answer;
    }

    private double determinant(double a00, double a01, double a10, double a11) {
        return (a00 * a11 - a01 * a10);
    }


}
