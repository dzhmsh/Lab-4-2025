package functions.basic;

import functions.Function;

public class Exp implements Function {

    @Override
    public double getLeftDomainBorder() {
        return Double.NEGATIVE_INFINITY;
    }

    @Override
    public double getRightDomainBorder() {
        return Double.POSITIVE_INFINITY;
    }

    @Override
    public double getFunctionValue(double x) {
        return Math.exp(x);
    }

    /*
     * Создайте в пакете публичный класс Exp, объекты которого должны вычислять
     * значение экспоненты. Класс должен реализовывать интерфейс Function. Для
     * вычисления экспоненты следует воспользоваться методом Math.exp(), а для
     * возвращения значений границ области определения – константами из класса
     * Double.
     */
}
