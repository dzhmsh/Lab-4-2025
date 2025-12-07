package functions;

public interface Function {

    /*
     * • public double getLeftDomainBorder() – возвращает значение левой границы
     * области определения функции;
     * 
     * • public double getRightDomainBorder() – возвращает значение правой границы
     * области определения функции;
     * 
     * • public double getFunctionValue(double x) – возвращает значение функции в
     * заданной точке.
     * 
     * Исключите соответствующие методы из интерфейса TabulatedFunction и сделайте
     * так, чтобы он расширял интерфейс Function. Теперь табулированные функции буду
     * частным случаем функций одной переменной.
     */
    public double getLeftDomainBorder();

    public double getRightDomainBorder();

    public double getFunctionValue(double x);

}
