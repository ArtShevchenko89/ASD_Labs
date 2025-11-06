package org.example;

import java.util.*;
import java.util.function.DoubleUnaryOperator;

public class ASD_Lab_7 {

    // Меню
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("ASD_Lab_7 — Варіант 13");
            System.out.println("1) Чисельне інтегрування");
            System.out.println("2) Пошук коренів рівняння f(x)=0");
            System.out.println("0) Вихід");
            System.out.print("Ваш вибір: ");
            int choice = safeInt(sc);
            if (choice == 0) break;
            if (choice == 1) runIntegration(sc);
            if (choice == 2) runRoots(sc);
            System.out.println();
        }
    }

    // Завдання 1
    // integrand: sqrt(1 - (1/4) * sin^2 x)
    static double integrand(double x) {
        double s = Math.sin(x);
        return Math.sqrt(Math.max(0.0, 1.0 - 0.25 * s * s));
    }

    static void runIntegration(Scanner sc) {
        System.out.println("\nЗАВДАННЯ 1: Інтеграл ∫ sqrt(1 - (1/4) sin^2 x) dx");
        System.out.print("Введіть нижню межу a (наприклад 1): ");
        double a = safeDouble(sc);
        System.out.print("Введіть верхню межу b (наприклад " + (Math.PI/2) + "): ");
        double b = safeDouble(sc);
        System.out.print("Введіть крок h (за замовчуванням 0.2): ");
        double h = safeDouble(sc);

        if (a > b) { double t = a; a = b; b = t; }

        int n = Math.max(1, (int)Math.round((b - a) / h));
        h = (b - a) / n;
        if (n % 2 == 1) { n++; h = (b - a) / n; }

        DoubleUnaryOperator f = ASD_Lab_7::integrand;

        double rect = rectangleMid(f, a, b, n);
        double trap = trapezoid(f, a, b, n);
        double simp = simpson(f, a, b, n);

        double exact = adaptiveSimpson(f, a, b, 1e-12);

        System.out.println("\nРЕЗУЛЬТАТИ (n=" + n + ", h=" + h + "):");
        System.out.printf("Метод прямокутників (середні точки): %.12f%n", rect);
        System.out.printf("Метод трапецій:                      %.12f%n", trap);
        System.out.printf("Метод Сімпсона:                      %.12f%n", simp);
        System.out.printf("Точне (референс, адапт. Сімпсон):    %.12f%n", exact);

        System.out.printf("Похибка прямокутники: %.2e%n", Math.abs(exact - rect));
        System.out.printf("Похибка трапеції:     %.2e%n", Math.abs(exact - trap));
        System.out.printf("Похибка Сімпсона:     %.2e%n", Math.abs(exact - simp));

        System.out.println("\nАналітична форма: I(a,b) = E( b | m ) - E( a | m ), де m = 1/4 — неповний еліптичний інтеграл 2-го роду.");
    }

    static double rectangleMid(DoubleUnaryOperator f, double a, double b, int n) {
        double h = (b - a) / n;
        double sum = 0.0;
        for (int i = 0; i < n; i++) {
            double xMid = a + (i + 0.5) * h;
            sum += f.applyAsDouble(xMid);
        }
        return sum * h;
    }

    static double trapezoid(DoubleUnaryOperator f, double a, double b, int n) {
        double h = (b - a) / n;
        double sum = 0.5 * (f.applyAsDouble(a) + f.applyAsDouble(b));
        for (int i = 1; i < n; i++) sum += f.applyAsDouble(a + i * h);
        return sum * h;
    }

    static double simpson(DoubleUnaryOperator f, double a, double b, int n) {
        if (n % 2 == 1) n++;
        double h = (b - a) / n;
        double sum1 = 0.0, sum2 = 0.0;
        for (int i = 1; i < n; i += 2) sum1 += f.applyAsDouble(a + i * h);
        for (int i = 2; i < n; i += 2) sum2 += f.applyAsDouble(a + i * h);
        return (h / 3.0) * (f.applyAsDouble(a) + 4.0 * sum1 + 2.0 * sum2 + f.applyAsDouble(b));
    }

    static double adaptiveSimpson(DoubleUnaryOperator f, double a, double b, double eps) {
        double c = (a + b) / 2.0;
        double fa = f.applyAsDouble(a), fb = f.applyAsDouble(b), fc = f.applyAsDouble(c);
        double S = simpsonRaw(fa, fb, fc, a, b);
        return adaptiveSimpsonRec(f, a, b, eps, S, fa, fb, fc, 20);
    }

    static double simpsonRaw(double fa, double fb, double fc, double a, double b) {
        return (b - a) / 6.0 * (fa + 4.0 * fc + fb);
    }

    static double adaptiveSimpsonRec(DoubleUnaryOperator f, double a, double b, double eps,
                                     double S, double fa, double fb, double fc, int depth) {
        double c = (a + b) / 2.0;
        double d = (a + c) / 2.0;
        double e = (c + b) / 2.0;
        double fd = f.applyAsDouble(d);
        double fe = f.applyAsDouble(e);
        double Sleft = simpsonRaw(fa, fc, fd, a, c);
        double Sright = simpsonRaw(fc, fb, fe, c, b);
        double S2 = Sleft + Sright;
        if (depth <= 0 || Math.abs(S2 - S) <= 15 * eps) {
            return S2 + (S2 - S) / 15.0;
        }
        return adaptiveSimpsonRec(f, a, c, eps / 2.0, Sleft, fa, fc, fd, depth - 1)
             + adaptiveSimpsonRec(f, c, b, eps / 2.0, Sright, fc, fb, fe, depth - 1);
    }

    // Завдання 2
    static double f(double x) {
        return 3.0 - x * x * x + Math.sin(Math.PI * x / 2.0);
    }

    static double df(double x) {
        return -3.0 * x * x + (Math.PI / 2.0) * Math.cos(Math.PI * x / 2.0);
    }

    static void runRoots(Scanner sc) {
        System.out.println("\nЗАВДАННЯ 2: Розв'язання f(x)=0 для f(x)=3 - x^3 + sin(pi*x/2)");
        System.out.print("Введіть ліву межу a: ");
        double a = safeDouble(sc);
        System.out.print("Введіть праву межу b: ");
        double b = safeDouble(sc);
        if (a > b) { double t = a; a = b; b = t; }
        System.out.print("Введіть точність epsilon (напр. 1e-6): ");
        double eps = safeDouble(sc);
        System.out.print("Максимум ітерацій (напр. 100): ");
        int maxIt = safeInt(sc);

        Double rootBis = null;
        if (Math.signum(f(a)) * Math.signum(f(b)) <= 0) {
            rootBis = bisection(a, b, eps, maxIt);
        }

        double x0 = (a + b) / 2.0;
        Double rootNewt = newton(x0, eps, maxIt);
        Double rootSec = secant(a, b, eps, maxIt);

        System.out.println("\nРЕЗУЛЬТАТИ:");
        if (rootBis != null) System.out.printf("Половинне ділення: x = %.12f, f(x)=%.2e%n", rootBis, f(rootBis));
        else System.out.println("Половинне ділення: неможливо — на [a,b] немає зміни знака.");

        if (rootNewt != null) System.out.printf("Метод дотичних:   x = %.12f, f(x)=%.2e%n", rootNewt, f(rootNewt));
        else System.out.println("Метод дотичних:   збіжність не досягнуто або похідна ~ 0.");

        if (rootSec != null) System.out.printf("Метод хорд:       x = %.12f, f(x)=%.2e%n", rootSec, f(rootSec));
        else System.out.println("Метод хорд:       збіжність не досягнуто.");

        int count = countSignChanges(a, b, 1000);
        System.out.println("Оцінка кількості коренів за зміною знака на сітці: " + count);
    }

    static Double bisection(double a, double b, double eps, int maxIt) {
        double fa = f(a), fb = f(b);
        if (fa * fb > 0) return null;
        for (int i = 0; i < maxIt; i++) {
            double c = 0.5 * (a + b);
            double fc = f(c);
            if (Math.abs(fc) < eps || 0.5 * (b - a) < eps) return c;
            if (fa * fc <= 0) { b = c; fb = fc; } else { a = c; fa = fc; }
        }
        return 0.5 * (a + b);
    }

    static Double newton(double x0, double eps, int maxIt) {
        double x = x0;
        for (int i = 0; i < maxIt; i++) {
            double fx = f(x);
            double dfx = df(x);
            if (Math.abs(dfx) < 1e-12) return null;
            double x1 = x - fx / dfx;
            if (Math.abs(x1 - x) < eps) return x1;
            x = x1;
        }
        return null;
    }

    static Double secant(double x0, double x1, double eps, int maxIt) {
        double f0 = f(x0), f1 = f(x1);
        for (int i = 0; i < maxIt; i++) {
            double denom = (f1 - f0);
            if (Math.abs(denom) < 1e-12) return null;
            double x2 = x1 - f1 * (x1 - x0) / denom;
            if (Math.abs(x2 - x1) < eps) return x2;
            x0 = x1; f0 = f1;
            x1 = x2; f1 = f(x1);
        }
        return null;
    }

    static int countSignChanges(double a, double b, int steps) {
        double prev = f(a);
        int cnt = 0;
        for (int i = 1; i <= steps; i++) {
            double x = a + i * (b - a) / steps;
            double cur = f(x);
            if (prev == 0 || cur == 0 || prev * cur < 0) cnt++;
            prev = cur;
        }
        return cnt;
    }

    // прокидуємо ексепшн
    static int safeInt(Scanner sc) {
        while (true) {
            try { return Integer.parseInt(sc.next()); }
            catch (Exception e) { System.out.print("Невірне ціле. Спробуйте ще: "); }
        }
    }

    static double safeDouble(Scanner sc) {
        while (true) {
            try { return Double.parseDouble(sc.next().replace(",", ".")); }
            catch (Exception e) { System.out.print("Невірне число. Спробуйте ще: "); }
        }
    }
}
