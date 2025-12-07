package functions.meta;

import functions.Function;

public class Scale implements Function {
    /*
     * Создайте класс `Scale`, объекты которого описывают функции, полученные из
     * исходных функций путём масштабирования вдоль осей координат. Конструктор
     * класса должен получать ссылку на объект исходной функции, а также
     * коэффициенты масштабирования вдоль оси абсцисс и оси ординат. Область
     * определения функции должна получаться из области определения исходной функции
     * масштабированием вдоль оси абсцисс, а значение функции – масштабированием
     * значения исходной функции вдоль оси ординат. Коэффициенты масштабирования
     * могут быть отрицательными.
     */
    private Function fun;
    private double scaleX;
    private double scaleY;

    public Scale(Function fun, double scaleX, double scaleY) {
        this.fun = fun;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }

    @Override
    public double getLeftDomainBorder() {
        return (fun.getLeftDomainBorder() * scaleX);
    }

    @Override
    public double getRightDomainBorder() {
        return (fun.getRightDomainBorder() * scaleX);
    }

    @Override
    public double getFunctionValue(double x) {
        return fun.getFunctionValue(x / scaleX) * scaleY;
    }

}
