package Model;

public class Function {
    private int num;
    private double[] points;
    private double[] A, B, C, D;
    private int pointsCount;
    private double[][] TriDiagMatrix;
    private boolean isLastPointEqualF;


    public Function(int num, double[] points) {
        this.num = num;
        this.points = points;
        pointsCount = points.length;
        A = new double[pointsCount - 1];
        B = new double[pointsCount];
        C = new double[pointsCount - 1];
        D = new double[pointsCount - 1];
        TriDiagMatrix = new double[3][pointsCount];
        isLastPointEqualF = false;
    }

    public void setLastPointEqualF(boolean lastPointEqualF) {
        isLastPointEqualF = lastPointEqualF;
    }

    public double[] getA() {
        return A;
    }

    public double[] getB() {
        return B;
    }

    public double[] getC() {
        return C;
    }

    public double[] getD() {
        return D;
    }

    public double F(double x) {
        if (num == 1) {
            return Math.sin(1.5 * x);
        } else if (num == 2) {
            return x * x + x - 6;
        } else {
            return x - 4;
        }
    }


    public void countCoefficients() {
        double[] h = new double[pointsCount];
        double[] delta = new double[pointsCount];
        double[] f = new double[pointsCount];
        double x3, xn;
        double[] y = new double[pointsCount];
        for (int i = 0; i < pointsCount; i++) {
            y[i] = F(points[i]);
        }
        if (isLastPointEqualF){
            if(num == 1){
                y[pointsCount/2] = -3;
            }else if (num == 2){
                y[pointsCount/2] = 0.0;
            }else{
                y[pointsCount/2] = 4;
            }
        }

        x3 = points[2] - points[0];
        xn = points[pointsCount - 1] - points[pointsCount - 3];
        for (int i = 0; i < pointsCount - 1; i++) {
            A[i] = (y[i]);
            h[i] = points[i + 1] - points[i];
            delta[i] = ((y[i + 1]) - (y[i])) / h[i];
            TriDiagMatrix[0][i] = i > 0 ? h[i] : x3;
            f[i] = i > 0 ? 3 * (h[i] * delta[i - 1] + h[i - 1] * delta[i]) : 0;
        }
        TriDiagMatrix[1][0] = h[0];
        TriDiagMatrix[2][0] = h[0];
        for (int i = 1; i < pointsCount - 1; i++) {
            TriDiagMatrix[1][i] = 2 * (h[i] + h[i - 1]);
            TriDiagMatrix[2][i] = h[i];
        }
        TriDiagMatrix[1][pointsCount - 1] = h[pointsCount - 2];
        TriDiagMatrix[2][pointsCount - 1] = xn;
        TriDiagMatrix[0][pointsCount - 1] = h[pointsCount - 2];
        f[0] = ((h[0] + 2 * x3) * h[1] * delta[0] + h[0] * h[0] * delta[1]) / x3;
        f[pointsCount - 1] = (h[pointsCount - 2] * h[pointsCount - 2] * delta[pointsCount - 2] + (2 * xn + h[pointsCount - 1]) * h[pointsCount - 2] * delta[pointsCount - 1]) / xn;

        SolveTriDiag(TriDiagMatrix, f);
        for (int j = 0; j < pointsCount - 1; j++) {
            D[j] = (B[j + 1] + B[j] - 2 * delta[j]) / (h[j] * h[j]);
            C[j] = 2 * (delta[j] - B[j]) / h[j] - (B[j + 1] - delta[j]) / h[j];

        }


    }

    public void SolveTriDiag(double[][] TDM, double[] F) {
        double[] alph = new double[pointsCount - 1];
        double[] beta = new double[pointsCount - 1];

        int i;

        alph[0] = -TDM[2][0] / TDM[1][0];
        beta[0] = F[0] / TDM[1][0];

        for (i = 1; i < pointsCount - 1; i++) {
            alph[i] = -TDM[2][i] / (TDM[1][i] + TDM[0][i] * alph[i - 1]);
            beta[i] = (F[i] - TDM[0][i] * beta[i - 1]) / (TDM[1][i] + TDM[0][i] * alph[i - 1]);
        }
        B[pointsCount - 1] = (F[pointsCount - 1] - TDM[0][pointsCount - 1] * beta[pointsCount - 2]) / (TDM[1][pointsCount - 1] + TDM[0][pointsCount - 1] * alph[pointsCount - 2]);

        for (i = pointsCount - 2; i > -1; i--) {
            B[i] = B[i + 1] * alph[i] + beta[i];
        }
    }

    @Override
    public String toString() {
        if(num == 1){
            return "y = sin(1.5x)";
        }else if(num == 2){
            return "y = x^2 + x - 6";
        }else {
            return "y = x - 4";
        }
    }

    public double Interpolate(double x) {
        int i=0;

        while (points[i] < x)
            i++;
        if(x == points[i]){
            return F(x);
        }else{
            i--;
            return A[i] + B[i]*(x-points[i]) + C[i]*Math.pow((x-points[i]),2) + D[i]*Math.pow((x-points[i]),3);
        }
    }


}
