import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(System.in);
            System.out.println("Выберите функцию:");
            System.out.println("1) x^3 + x^2 + x + 1");
            System.out.println("2) log(x) + x");
            System.out.println("3) sin(x) + x");
            System.out.println("4) 1/x^2 ");
            int number_of_function = in.nextInt();
            while (number_of_function < 1 || number_of_function > 4) {
                System.out.println("Повторите ввод");
                number_of_function = in.nextInt();
            }
            Function function = new Function(number_of_function);
            System.out.println("Нижний предел:");
            double low = in.nextDouble();
            System.out.println("Вверхний предел:");
            double high = in.nextDouble();
            while (high > 1000) {
                System.out.println("Вверхний предел слишком большой!");
                System.out.println("Вверхний предел:");
                high = in.nextInt();
            }
            while (low > 1000) {
                System.out.println("Нижний предел слишком большой!");
                System.out.println("Нижний предел:");
                low = in.nextInt();
            }

            if (number_of_function == 4) {
                while ((low < 0 && high > 0) || (low > 0 && high < 0) || low == 0 || high == 0) {
                    System.out.println("Интеграл расходится");
                    System.out.println("Нижний предел:");
                    low = in.nextInt();
                    System.out.println("Вверхний предел:");
                    high = in.nextDouble();
                }
            }
            if (number_of_function == 2) {
                while (low <= 0 || high <= 0) {
                    System.out.println("Интеграл расходится");
                    System.out.println("Нижний предел:");
                    low = in.nextInt();
                    System.out.println("Вверхний предел:");
                    high = in.nextDouble();
                }

            }
            System.out.println("Точность:");
            double accuracy = in.nextDouble();
            while (accuracy == 0 ) {
                System.out.println("Повторите ввод точность = нулю");
                System.out.println("Точность:");
                accuracy = in.nextInt();
            }
            while (accuracy <= 0.0000000001 ) {
                System.out.println("Повторите ввод, точность должна быть < 10^(-10)");
                System.out.println("Точность:");
                accuracy = in.nextInt();
            }
            if (high != low) {
                SimpsonMethod simpsonMethod = new SimpsonMethod(function, low, high, accuracy);
                printResult(simpsonMethod);
            }else{
                System.out.println("I: 0");
            }

        } catch (Exception exc) {
            System.out.println("Ошибка при вводе данных. Повторите попытку");
        }
    }


    private static void printResult(SimpsonMethod simpsonMethod) {
        simpsonMethod.Simpson_Method();
        double I = BigDecimal.valueOf(simpsonMethod.getResult()).setScale(5, RoundingMode.HALF_UP).doubleValue();
        System.out.println("I: " + I);
        double count = simpsonMethod.getNumber_of_steps();
        System.out.println("Количество шагов: " + count);
        double err = BigDecimal.valueOf(simpsonMethod.getErr()).setScale(7, RoundingMode.HALF_UP).doubleValue();
        System.out.println("Погрежность: " + err);
    }

}