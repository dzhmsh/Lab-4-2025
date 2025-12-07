package functions.basic;

import functions.Function;

public class Log implements Function {

    private double foundation;

    public Log(double foundation) {
        // Основание логарифма должно быть строго больше 0 и не равно 1
        if (foundation <= 0 || foundation == 1) {
            throw new IllegalArgumentException("Foundation must be > 0 and != 1");
        }
        this.foundation = foundation;
    }

    @Override
    public double getLeftDomainBorder() {
        return 0;
    }

    @Override
    public double getRightDomainBorder() {
        return Double.POSITIVE_INFINITY;
    }

    @Override
    public double getFunctionValue(double x) {
        // Используем формулу смены основания: log_base(x) = ln(x) / ln(base)
        return Math.log(x) / Math.log(foundation);
    }
    /*
     * Аналогично, создайте класс Log, объекты которого должны вычислять значение
     * логарифма по заданному основанию. Основание должно передаваться как параметр
     * конструктора. Для вычисления логарифма следует воспользоваться методом
     * Math.log().
     */
}
