package functions.meta;

import functions.Function;

public class Composition implements Function {
    /*
     * Также создайте класс `Composition`, объекты которого описывают композицию
     * двух исходных функций. Конструктор класса должен получать ссылки на объекты
     * первой и второй функции. Область определения функции можно считать
     * совпадающей с областью определения исходной функции (хотя математически это
     * не всегда так).
     */
    private Function fun1;
    private Function fun2;

    public Composition(Function fun1, Function fun2) {
        this.fun1 = fun1;
        this.fun2 = fun2;
    }

    @Override
    public double getLeftDomainBorder() {
        return (fun2.getLeftDomainBorder());
    }

    @Override
    public double getRightDomainBorder() {
        return (fun2.getRightDomainBorder());
    }

    @Override
    public double getFunctionValue(double x) {
        double x1 = fun2.getFunctionValue(x);
        return fun1.getFunctionValue(x1);
    }

}
