import functions.*;
import functions.basic.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {

        // 1. Тестирование аналитических функций и табулирования
        System.out.println(" Тест аналитических функций и их табулирования ");
        Function sin = new Sin();
        Function cos = new Cos();

        TabulatedFunction tabSin = TabulatedFunctions.tabulate(sin, 0, Math.PI, 10);
        TabulatedFunction tabCos = TabulatedFunctions.tabulate(cos, 0, Math.PI, 10);

        System.out.println("Сравнение значений:");
        for (double x = 0; x <= Math.PI; x += 0.5) {
            System.out.printf("x = %.2f | Sin: %.4f, TabSin: %.4f | Cos: %.4f, TabCos: %.4f%n",
                    x, sin.getFunctionValue(x), tabSin.getFunctionValue(x),
                    cos.getFunctionValue(x), tabCos.getFunctionValue(x));
        }

        // 2. Тестирование операций над функциями
        System.out.println("\n Тест операций (Sin^2 + Cos^2) ");
        Function sqSin = Functions.power(tabSin, 2);
        Function sqCos = Functions.power(tabCos, 2);
        Function sumSq = Functions.sum(sqSin, sqCos);

        for (double x = 0; x <= Math.PI; x += 0.5) {
            System.out.printf("x = %.2f: sin^2 + cos^2 = %.4f%n", x, sumSq.getFunctionValue(x));
        }

        // 3. Тестирование символьного ввода/вывода (Exp)
        System.out.println("\n Тест символьного ввода/вывода (Exp) ");
        Function exp = new Exp();
        TabulatedFunction tabExp = TabulatedFunctions.tabulate(exp, 0, 10, 11);
        File fileChar = new File("exp_char.txt");

        // Запись в символьный поток
        try (BufferedWriter out = new BufferedWriter(new FileWriter(fileChar))) {
            TabulatedFunctions.writeTabulatedFunction(tabExp, out);
            System.out.println("Функция записана в файл " + fileChar.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Чтение из символьного потока
        try (BufferedReader in = new BufferedReader(new FileReader(fileChar))) {
            TabulatedFunction readExp = TabulatedFunctions.readTabulatedFunction(in);
            System.out.println("Функция считана из файла. Сравнение значений:");

            for (double x = 0; x <= 10; x += 2) {
                System.out.printf("x = %.1f: Исходная = %.4f, Считанная = %.4f%n",
                        x, tabExp.getFunctionValue(x), readExp.getFunctionValue(x));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 4. Тестирование байтового ввода/вывода (Log)
        System.out.println("\n Тест байтового ввода/вывода (Log) ");
        Function log = new Log(Math.E);
        TabulatedFunction tabLog = TabulatedFunctions.tabulate(log, 0.1, 10, 11);
        File fileByte = new File("log_byte.bin");

        // Запись в байтовый поток
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(fileByte))) {
            TabulatedFunctions.outputTabulatedFunction(tabLog, out);
            System.out.println("Функция записана в файл " + fileByte.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Чтение из байтового потока
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(fileByte))) {
            TabulatedFunction readLog = TabulatedFunctions.inputTabulatedFunction(in);
            System.out.println("Функция считана из файла. Сравнение значений:");

            for (double x = 1; x <= 10; x += 2) {
                System.out.printf("x = %.1f: Исходная = %.4f, Считанная = %.4f%n",
                        x, tabLog.getFunctionValue(x), readLog.getFunctionValue(x));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 5. Тестирование сериализации (Externalizable)
        System.out.println("\n Тест сериализации (Externalizable: Log(Exp(x))) ");

        // Создаем сложную функцию
        Function complexFunc = Functions.composition(new Log(Math.E), new Exp());

        // Используем LinkedListTabulatedFunction, так как именно в нем реализован
        // Externalizable
        TabulatedFunction linkedListFunc = new LinkedListTabulatedFunction(0, 10, 11);

        // Заполняем функцию значениями
        for (int i = 0; i < linkedListFunc.getPointsCount(); i++) {
            double x = linkedListFunc.getPointX(i);
            linkedListFunc.setPointY(i, complexFunc.getFunctionValue(x));
        }

        File fileSer = new File("complex_ser.bin");

        // Сериализация
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileSer))) {
            out.writeObject(linkedListFunc);
            System.out.println("Объект (Externalizable) сериализован в файл " + fileSer.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Десериализация
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileSer))) {
            TabulatedFunction deserializedFunc = (TabulatedFunction) in.readObject();
            System.out.println("Объект десериализован. Проверка значений:");

            for (double x = 0; x <= 10; x += 2) {
                System.out.printf("x = %.1f: Значение = %.4f%n", x, deserializedFunc.getFunctionValue(x));
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Удаление временных файлов
        fileChar.deleteOnExit();
        fileByte.deleteOnExit();
        fileSer.deleteOnExit();
    }

}