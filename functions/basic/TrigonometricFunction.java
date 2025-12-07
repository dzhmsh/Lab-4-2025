package functions.basic;

import functions.Function;

public abstract class TrigonometricFunction implements Function {

    @Override
    public double getLeftDomainBorder() {
        return Double.NEGATIVE_INFINITY;
    }

    @Override
    public double getRightDomainBorder() {
        return Double.POSITIVE_INFINITY;
    }

    /*
     * Создайте класс TrigonometricFunction, реализующий интерфейс Function и
     * описывающий методы получения границ области определения.
     * 
     * Создайте наследующие от него публичные классы Sin, Cos и Tan, объекты которых
     * вычисляют, соответственно, значения синуса, косинуса и тангенса. Для
     * получения значений следует воспользоваться методами Math.sin(), Math.cos() и
     * Math.tan().
     */

}
