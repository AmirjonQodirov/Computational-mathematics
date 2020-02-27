class Iteration {

    private double[][] matrix;
    private double[] B;
    private int n;
    private int accuracy;
    private int iter = 0;
    private double[] eps;
    private double[] result;

    Iteration(double[][] matrix, int n, double[] B, int accuracy) {
        this.matrix = matrix;
        this.n = n;
        this.B = B;
        this.accuracy = accuracy;
        eps = new double[n];
        result = new double[n];
    }

    double[] getEps() {
        return eps;
    }

    double[] getResult() {
        return result;
    }

    int getIter() {
        return iter;
    }

    int getN() {
        return n;
    }

    boolean checkDiagonal() {
        boolean b = true;
        for (int i = 0; i < n; i++) {
           b = checkString(i);
            if (!b) {
                break;
            }
        }
        return b;
    }

     private boolean checkString(int matrix_str_number) {
        double sum = 0;
        for (int i = 0; i < n; i++) {
            sum += Math.abs(matrix[matrix_str_number][i]);
        }
        sum -= 2 * Math.abs(matrix[matrix_str_number][matrix_str_number]);
        return sum < 0;
    }

    boolean changeMatrix() {
        for (int i = 0; i < n; i++) {
            double[] a = matrix[i];
            int element = -1;
            double max = -1;
            double sum = 0;
            for (int j = 0; j < n; j++) {
                sum += Math.abs(a[j]);
                if (a[j] > max) {
                    element = j;
                    max = a[j];
                }
            }
            sum -= Math.abs(max);
            if (sum < Math.abs(max)) {
                double[] m = matrix[i];
                matrix[i] = matrix[element];
                matrix[element] = m;
                double t = B[i];
                B[i] = B[element];
                B[element] = t;
            } else {
                return false;
            }
        }
        return checkDiagonal();
    }



    void simpleIteration() {
        double[] prev = new double[n];
        double[] next = new double[n];
        double[] ans_B = new double[n];
        double[][] matrix_coefficient = new double[n][n];

        boolean action = true;

        for (int i = 0; i < n; i++) {
            int a = 1;
            if (this.matrix[i][i] > 0) {
                a = -1;
            }
            for (int j = 0; j < n; j++) {
                if (j == i) {
                    matrix_coefficient[i][j] = 0;
                } else {
                    matrix_coefficient[i][j] = this.matrix[i][j] / this.matrix[i][i]*a;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            prev[i] = B[i] / this.matrix[i][i];
        }

        while (action) {
            if (iter == 0) {
                if (n >= 0) System.arraycopy(prev, 0, ans_B, 0, n);
            }

            for (int i = 0; i < n; i++) {
                next[i] = ans_B[i];
                for (int j = 0; j < n; j++) {
                    next[i] += prev[j] * matrix_coefficient[i][j];
                }
            }

            double max = Math.abs(next[1] - prev[1]);
            for (int i = 0; i < n; i++) {
                double epsilon = Math.abs(next[i] - prev[i]);
                eps[i] = epsilon;
                if (max < epsilon) {
                    max = epsilon;
                }
            }


            if (max < Math.pow(10,-accuracy)) {
                action = false;
                if (n >= 0) System.arraycopy(next, 0, result, 0, n);
            }


            if (n >= 0) System.arraycopy(next, 0, prev, 0, n);
            iter++;

        }

    }







}
