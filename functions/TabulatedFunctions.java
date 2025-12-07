package functions;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.io.Writer;

public class TabulatedFunctions {
    private static final double EPSILON = 1e-9;

    private TabulatedFunctions() {
        // Запрещаем создание экземпляров
    }

    public static TabulatedFunction tabulate(Function function, double leftX, double rightX, int pointsCount) {

        if (leftX >= rightX - EPSILON) {
            throw new IllegalArgumentException("Left border must be less than right border");
        }

        if (pointsCount < 2) {
            throw new IllegalArgumentException("Here must be more than 2 points");
        }

        double domainLeft = function.getLeftDomainBorder();
        double domainRight = function.getRightDomainBorder();

        // Проверка выхода за границы области определения
        if (leftX < domainLeft - EPSILON) { // исправил логику сравнения с эпсилон
            throw new IllegalArgumentException("The left tabulation boundary (" + leftX
                    + ") extends beyond the function's domain (" + domainLeft + ").");
        }

        if (rightX > domainRight + EPSILON) {
            throw new IllegalArgumentException(
                    "The right tabulation boundary (" + rightX + ") extends beyond the function's domain ("
                            + domainRight + ").");
        }

        FunctionPoint[] points = new FunctionPoint[pointsCount];
        double step = (rightX - leftX) / (pointsCount - 1);

        for (int i = 0; i < pointsCount; i++) {
            double x = leftX + i * step;
            // Корректировка последней точки, чтобы избежать погрешности float
            if (i == pointsCount - 1) {
                x = rightX;
            }
            double y = function.getFunctionValue(x);
            points[i] = new FunctionPoint(x, y);
        }

        return new ArrayTabulatedFunction(points);
    }

    // Вывод табулированной функции в байтовый поток
    public static void outputTabulatedFunction(TabulatedFunction function, OutputStream out) throws IOException {
        DataOutputStream dataOut = new DataOutputStream(out);
        int count = function.getPointsCount();
        dataOut.writeInt(count);
        for (int i = 0; i < count; i++) {
            dataOut.writeDouble(function.getPointX(i));
            dataOut.writeDouble(function.getPointY(i));
        }
        dataOut.flush(); // Сбрасываем буфер
        // Поток не закрываем, согласно заданию (оставляем управление потоком
        // вызывающему коду)
    }

    public static TabulatedFunction inputTabulatedFunction(InputStream in) throws IOException {
        DataInputStream dataIn = new DataInputStream(in);
        int count = dataIn.readInt();
        FunctionPoint[] points = new FunctionPoint[count];

        for (int i = 0; i < count; i++) {
            double x = dataIn.readDouble();
            double y = dataIn.readDouble();
            points[i] = new FunctionPoint(x, y);
        }

        return new ArrayTabulatedFunction(points);
    }

    public static void writeTabulatedFunction(TabulatedFunction function, Writer out) {
        PrintWriter writer = new PrintWriter(out);
        int count = function.getPointsCount();
        writer.print(count);
        writer.print(" ");
        for (int i = 0; i < count; i++) {
            writer.print(function.getPointX(i));
            writer.print(" ");
            writer.print(function.getPointY(i));
            writer.print(" ");
        }
        writer.flush();
    }

    public static TabulatedFunction readTabulatedFunction(Reader in) throws IOException {
        StreamTokenizer tokenizer = new StreamTokenizer(in);
        // Настраиваем токенайзер, чтобы он не воспринимал конец строки как токен
        tokenizer.eolIsSignificant(false);

        // Считываем количество точек
        tokenizer.nextToken();
        if (tokenizer.ttype != StreamTokenizer.TT_NUMBER) {
            throw new IOException("Expected number of points");
        }
        int count = (int) tokenizer.nval;

        FunctionPoint[] points = new FunctionPoint[count];

        for (int i = 0; i < count; i++) {
            // Считываем X
            tokenizer.nextToken();
            if (tokenizer.ttype != StreamTokenizer.TT_NUMBER) {
                throw new IOException("Expected X coordinate at index " + i);
            }
            double x = tokenizer.nval;

            // Считываем Y
            tokenizer.nextToken();
            if (tokenizer.ttype != StreamTokenizer.TT_NUMBER) {
                throw new IOException("Expected Y coordinate at index " + i);
            }
            double y = tokenizer.nval;

            points[i] = new FunctionPoint(x, y);
        }

        return new ArrayTabulatedFunction(points);
    }

}
