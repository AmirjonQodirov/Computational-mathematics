class SimpsonMethod {
    private double low;
    private double high;
    private double accuracy;
    private Function function;
    private int number_of_steps;
    private double err;
    private double result;
    private int b = 1;

    SimpsonMethod(Function function, double low, double high, double accuracy) {
        this.function = function;
        if (high > low) {
            this.low = low;
            this.high = high;
        } else {
            b = -1;
            this.low = high;
            this.high = low;
        }
        this.accuracy = accuracy;
    }

    private double calculate_integral(int number_of_steps) {
        double sum = 0;
        double h = (high - low) / number_of_steps;
        for (int i = 1; i < number_of_steps; i++) {
            sum += 4 * function.y(low + i * h);
            ++i;
            sum += 2 * function.y(low + i * h);
        }
        return (sum + function.y(low) - function.y(high)) * h / 3;
    }

    void Simpson_Method() {
        double In;
        double I2n;
        for (int n = 4; n <= 10000; n += 2) {
            In = calculate_integral(n);
            I2n = calculate_integral(2*n);
            if ((Math.abs(I2n - In) / 15) < accuracy) {
                result = I2n;
                number_of_steps = n;
                err = Math.abs(I2n - In) / 15;
                break;
            }
        }
        result *= b;
    }

    double getNumber_of_steps() {
        return number_of_steps;
    }

    double getErr() {
        return err;
    }

    double getResult() {
        return result;
    }
}
