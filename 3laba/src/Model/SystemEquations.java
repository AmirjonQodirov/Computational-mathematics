package Model;

public class SystemEquations {

    private int num;

    public int getNum() {
        return num;
    }

    public String toStringF1() {
        if (num == 1) {
            return "F(x,y) = 0.1*x^2 + x + 0.2*y^2 - 0.3";
        } else if (num == 2) {
            return "F(x,y) = x^2 + y^2 - 9 = 0";
        } else if (num == 3) {
            return "F(x,y) = sin(x) - xy = 0";
        } else {
            return "F(x,y) = x^3 - y^2 = 0";
        }
    }

    public String toStringF2() {
        if (num == 1) {
            return "F(x,y) = 0.2*x^2 + y - 0.1*x*y - 0.7";
        } else if (num == 2) {
            return "F(x,y) = x^3 - y = 0";
        } else if (num == 3) {
            return "F(x,y) = (x + 4)^2 - 3 - y = 0";
        } else {
            return "F(x,y) = y^2 - 5*x";
        }
    }

    public SystemEquations(int num) {
        this.num = num;
    }

    double F1(double x, double y) {
        if (num == 1) {
            return 0.1 * x * x + x + 0.2 * y * y - 0.3;
        } else if (num == 2) {
            return x * x + y * y - 9;
        } else if (num == 3) {
            return Math.sin(x) - x * y;
        } else {
            return x * x * x - y * y;
        }
    }

    double F2(double x, double y) {
        if (num == 1) {
            return 0.2 * x * x + y - 0.1 * x * y - 0.7;
        } else if (num == 2) {
            return x * x * x - y;
        } else if (num == 3) {
            return (x + 4) * (x + 4) - 3 - y;
        } else {
            return y * y - 5 * x;
        }
    }

    double dF1_X(double x, double y) {
        if (num == 1) {
            return 0.2 * x + 1;
        } else if (num == 2) {
            return 2 * x;
        } else if (num == 3) {
            return Math.cos(x) - y;
        } else {
            return 3*x*x;
        }
    }

    double dF1_Y(double x, double y) {
        if (num == 1) {
            return 0.4 * y;
        } else if (num == 2) {
            return 2 * y;
        } else if (num == 3) {
            return -x;
        } else {
            return -2*y;
        }
    }

    double dF2_X(double x, double y) {
        if (num == 1) {
            return 0.4 * x - 0.1 * y;
        } else if (num == 2) {
            return 3 * x * x;
        } else if (num == 3) {
            return 2 * (x + 4);
        } else {
            return -5;
        }
    }

    double dF2_Y(double x, double y) {
        if (num == 1) {
            return 1 - 0.1 * x;
        } else if (num == 2) {
            return -1;
        } else if (num == 3) {
            return -1;
        } else {
            return 2*y;
        }
    }

}