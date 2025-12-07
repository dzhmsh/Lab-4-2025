package functions;

import functions.meta.*;

public class Functions {
    /*
     * В пакете `functions` создайте класс `Functions`, содержащий вспомогательные
     * статические методы для работы с функциями. Сделайте так, чтобы в программе
     * вне этого класса нельзя было создать его объект. Класс должен содержать
     * следующие методы:
     * 
     * 
     *  При написании методов следует воспользоваться созданными ранее классами из
     * пакета `functions.meta`.
     */
    // Приватный конструктор
    private Functions() {

    }
     /* `public static Function shift(Function f, double shiftX, double shiftY)` –
     * возвращает объект функции, полученной из исходной сдвигом вдоль осей;
     */
     public static Function shift(Function f, double shiftX, double shiftY) {
         return new Shift(f, shiftX, shiftY);
    }

     /*
     * `public static Function scale(Function f, double scaleX, double scaleY)` –
     * возвращает объект функции, полученной из исходной масштабированием вдоль
     * осей;
     */
     public static Function scale(Function f, double scaleX, double scaleY) {
         return new Scale(f, scaleX, scaleY);
    }
     /*
     * `public static Function power(Function f, double power)` – возвращает объект
     * функции, являющейся заданной степенью исходной;
     */
     public static Function power(Function f, double power) {
         return new Power(f, power);
    }
    /*
     * `public static Function sum(Function f1, Function f2)` – возвращает объект
     * функции, являющейся суммой двух исходных;
     */
    public static Function sum(Function f1, Function f2) {
        return new Sum(f1, f2);
    }
    /*
     * `public static Function mult(Function f1, Function f2)` – возвращает объект
     * функции, являющейся произведением двух исходных;
     */
    public static Function mult(Function f1, Function f2) {
        return new Mult(f1, f2);
    }
    /*
     * `public static Function composition(Function f1, Function f2)` – возвращает
     * объект функции, являющейся композицией двух исходных.
     */

    public static Function composition(Function f1, Function f2) {
        return new Composition(f1, f2);
    }

}
