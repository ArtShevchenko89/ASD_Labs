package org.example;

// 1 рівень
public class IntegrationBasic {
    public void run() {
        double a = 1.0, b = 2.0, h = 0.2;

        double rect = IntegrationMethods.rectangle(a, b, h);
        double trap = IntegrationMethods.trapezoid(a, b, h);
        double simp = IntegrationMethods.simpson(a, b, h);
        double exact = IntegrationMethods.analytical(a, b);

        System.out.printf("Метод прямокутників: %.6f%n", rect);
        System.out.printf("Метод трапецій:      %.6f%n", trap);
        System.out.printf("Метод Сімпсона:     %.6f%n", simp);
        System.out.printf("Аналітичне значення: %.6f%n", exact);
    }
}
