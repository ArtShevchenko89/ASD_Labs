package org.example;

public class IntegrationMethods {

    // Підінтегральна функція f(x) = sin(4x)
    public static double f(double x) {
        return Math.sin(4 * x);
    }

    // Метод прямокутників
    public static double rectangle(double a, double b, double h) {
        double sum = 0;
        for (double x = a; x < b; x += h)
            sum += f(x + h / 2);
        return sum * h;
    }

    // Метод трапецій
    public static double trapezoid(double a, double b, double h) {
        double sum = (f(a) + f(b)) / 2;
        for (double x = a + h; x < b; x += h)
            sum += f(x);
        return sum * h;
    }

    // Метод Сімпсона
    public static double simpson(double a, double b, double h) {
        int n = (int) ((b - a) / h);
        if (n % 2 == 1) n++;
        double sum = f(a) + f(b);
        for (int i = 1; i < n; i++) {
            double x = a + i * h;
            sum += f(x) * (i % 2 == 0 ? 2 : 4);
        }
        return sum * h / 3;
    }

    // Аналітичне значення інтеграла
    public static double analytical(double a, double b) {
        return -(Math.cos(4 * b) - Math.cos(4 * a)) / 4.0;
    }
}
