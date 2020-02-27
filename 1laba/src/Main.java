import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Iteration simpleIteration;
        int n;
        int accuracy;
        double[][] matrix;
        double[] B;

        try {
            Scanner in = new Scanner(System.in);
            System.out.println("Выберите способ ввода:\n f - ввод из файла, \n c - ввод с клавиатуры.");
            String check = in.nextLine();
            check = check.trim();
            while (!check.equals("f") && !(check.equals("c"))) {
                System.out.println("Повторите ввод");
                check = in.nextLine();
                check = check.trim();
            }
            if (check.equals("f")) {
                System.out.println("Введите путь к файлу");
                String filename = in.nextLine();
                try {
                    Scanner inFile = new Scanner(new File(filename));
                    accuracy = inFile.nextInt();
                    n = inFile.nextInt();
                    matrix = new double[n][n];
                    B = new double[n];
                    System.out.println("Введите матрицу");
                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < n; j++) {
                            matrix[i][j] = inFile.nextDouble();
                        }
                        B[i] = inFile.nextDouble();
                    }
                    simpleIteration = new Iteration(matrix, n, B, accuracy);
                    check(simpleIteration,accuracy);
                } catch (Exception exc) {
                    System.out.println("Ошибка при чтении с файла");
                }
            } else {
                System.out.println("Введите точность");
                accuracy = in.nextInt();
                System.out.println("Введите количество неизвестных");
                n = in.nextInt();
                matrix = new double[n][n];
                B = new double[n];
                System.out.println("Выберите способ ввода:\n r - случайные коэффициенты, \n c - ввод с клавиатуры.");
                in.nextLine();
                check = in.nextLine();
                while (!check.equals("r") && !(check.equals("c"))) {
                    System.out.println("Повторите ввод");
                    check = in.nextLine();
                }
                if (check.equals("c")) {
                    System.out.println("Введите матрицу");
                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < n; j++) {
                            matrix[i][j] = in.nextDouble();
                        }
                        B[i] = in.nextDouble();
                    }
                    simpleIteration = new Iteration(matrix, n, B,accuracy);
                    check(simpleIteration,accuracy );
                } else {
                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < n; j++) {
                            Random r = new Random();
                            if (i == j) {
                                matrix[i][j] = 40 + 50 * r.nextDouble();
                            } else {
                                matrix[i][j] = -10 + 10 * r.nextDouble();
                            }
                        }
                        Random r = new Random();
                        B[i] = -20 + 20 * r.nextDouble();
                    }
                    simpleIteration = new Iteration(matrix,  n, B,accuracy);
                    check(simpleIteration,accuracy );
                }
            }
        } catch (Exception exc) {
            System.out.println("Ошибка при вводе данных. Повторите попытку");
        }
    }
    private static void printResult(Iteration simpleIteration, int t) {
        simpleIteration.simpleIteration();
        System.out.println("Количество итераций: " + simpleIteration.getIter());
        System.out.println("Столбец неизвестных: " );
        double[] res = simpleIteration.getResult();
        for (int i = 0; i < simpleIteration.getN(); i++) {
            double truncatedDouble = BigDecimal.valueOf(res[i]).setScale(t+2, RoundingMode.HALF_UP).doubleValue();
            System.out.println(truncatedDouble);
        }
        System.out.println("Столбец погрешностей: ");
        double[] eps = simpleIteration.getEps();
        for (int i = 0; i < simpleIteration.getN(); i++) {
            double truncatedDouble = BigDecimal.valueOf(eps[i]).setScale(t+2, RoundingMode.HALF_UP).doubleValue();
            System.out.println(truncatedDouble);
        }
    }

    private static  void check(Iteration simpleIteration, int t) {
        if (!simpleIteration.checkDiagonal()) {
            if (!simpleIteration.changeMatrix()) {
                System.out.println("Матрицу невозможно привести к диагональному виду");
            } else {
                printResult(simpleIteration, t);
            }
        } else {
            printResult(simpleIteration, t);
        }
    }
}