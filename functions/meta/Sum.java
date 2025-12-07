package functions.meta;

import functions.Function;

public class Sum implements Function{
/*
     * Создайте класс Sum, объекты которого представляют собой функции, являющиеся
     * суммой двух других функций. Класс должен реализовывать интерфейс Function.
     * Конструктор класса должен получать ссылки типа Function на объекты
     * суммируемых функций, а область определения функции должна получаться как
     * пересечение областей определения исходных функций.
     */
    //Конструктор класса должен получать ссылки типа Function на объекты суммируемых функций
    private Function fun1;
    private Function fun2;

    public Sum(Function fun1, Function fun2) {
        this.fun1 = fun1;
        this.fun2 = fun2;
    }

    //область определения функции должна получаться как пересечение областей определения исходных функций.
    @Override
    public double getLeftDomainBorder() {
        return Math.max(fun1.getLeftDomainBorder(), fun2.getLeftDomainBorder());
    }
    @Override
    public double getRightDomainBorder() {
        return Math.min(fun1.getRightDomainBorder(), fun2.getRightDomainBorder());
    }

    @Override
    public double getFunctionValue(double x) {
        return fun1.getFunctionValue(x) + fun2.getFunctionValue(x);
    }
        



}
