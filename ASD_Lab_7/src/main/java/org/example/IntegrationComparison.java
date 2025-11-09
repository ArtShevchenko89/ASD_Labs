package org.example;

// 3 рівень
public class IntegrationComparison {
    public void run() {
        double a = 1.0, b = 2.0, h = 0.2;

        double rect = IntegrationMethods.rectangle(a, b, h);
        double trap = IntegrationMethods.trapezoid(a, b, h);
        double simp = IntegrationMethods.simpson(a, b, h);
        double exact = IntegrationMethods.analytical(a, b);

        double errRect = Math.abs(rect - exact);
        double errTrap = Math.abs(trap - exact);
        double errSimp = Math.abs(simp - exact);

        System.out.printf("%-20s %-15s %-15s%n", "Метод", "Результат", "Похибка");
        System.out.println("------------------------------------------------");
        System.out.printf("%-20s %-15.8f %-15.8f%n", "Прямокутників", rect, errRect);
        System.out.printf("%-20s %-15.8f %-15.8f%n", "Трапецій", trap, errTrap);
        System.out.printf("%-20s %-15.8f %-15.8f%n", "Сімпсона", simp, errSimp);
        System.out.printf("%nАналітичне значення: %.8f%n", exact);

        System.out.println("\nВисновок:");
        System.out.println("1) Найбільшу точність дає метод Сімпсона.");
        System.out.println("2) Метод трапецій точніший за прямокутники.");
        System.out.println("3) Усі методи збігаються до точного значення при зменшенні h.");
    }
}
