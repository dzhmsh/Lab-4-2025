package functions.meta;

import functions.Function;

public class Power implements Function {
    /*
     * Создайте класс `Power`, объекты которого представляют собой функции,
     * являющиеся степенью другой функции. Конструктор класса должен получать ссылку
     * на объекты базовой функции и степень, в которую должны возводиться её
     * значения. Область определения функции можно считать совпадающей с областью
     * определения исходной функции (хотя математически это не всегда так).
     */
    private Function fun;
    private double pow;

    public Power(Function fun, double power) {
        this.fun = fun;
        this.pow = power;
    }

    @Override
    public double getLeftDomainBorder() {
        return (fun.getLeftDomainBorder());
    }

    @Override
    public double getRightDomainBorder() {
        return (fun.getRightDomainBorder());
    }

    @Override
    public double getFunctionValue(double x) {
        return Math.pow(fun.getFunctionValue(x), pow);
    }
}
