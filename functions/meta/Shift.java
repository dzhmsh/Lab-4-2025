package functions.meta;

import functions.Function;

public class Shift implements Function {
    /*
     * Аналогично, создайте класс `Shift`, объекты которого описывают функции,
     * полученные из исходных функций путём сдвига вдоль осей координат.
     */
    private Function fun;
    private double shiftX;
    private double shiftY;

    public Shift(Function fun, double shiftX, double shiftY) {
        this.fun = fun;
        this.shiftX = shiftX;
        this.shiftY = shiftY;
    }

    @Override
    public double getLeftDomainBorder() {
        return (fun.getLeftDomainBorder() + shiftX);
    }

    @Override
    public double getRightDomainBorder() {
        return (fun.getRightDomainBorder() + shiftX);
    }

    @Override
    public double getFunctionValue(double x) {
        return fun.getFunctionValue(x - shiftX) + shiftY;
    }

}