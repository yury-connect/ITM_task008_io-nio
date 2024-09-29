package task1902;

/*
Адаптер
Используй класс AdapterFileOutputStream, чтобы адаптировать FileOutputStream к новому интерфейсу AmigoStringWriter.


Requirements:
1. AmigoStringWriter должен быть интерфейсом.
2. Класс AdapterFileOutputStream должен реализовывать интерфейс AmigoStringWriter.
3. Класс AdapterFileOutputStream должен содержать приватное поле fileOutputStream типа FileOutputStream.
4. Класс AdapterFileOutputStream должен содержать конструктор с параметром FileOutputStream.
5. Метод flush() класса AdapterFileOutputStream должен делегировать полномочие соответствующему методу fileOutputStream.
6. Метод writeString(String s) класса AdapterFileOutputStream должен делегировать полномочие соответствующему методу write() объекта fileOutputStream.
7. Метод close() класса AdapterFileOutputStream должен делегировать полномочие соответствующему методу fileOutputStream.
*/

import java.io.FileOutputStream;
import java.io.IOException;

public class AdapterFileOutputStream implements AmigoStringWriter {

    private FileOutputStream fileOutputStream;

    public AdapterFileOutputStream(FileOutputStream fileOutputStream) {
        this.fileOutputStream = fileOutputStream;
    }

    public static void main(String[] args) {
        // по заданию прописывать логику не требуется.
    }


    @Override
    public void flush() throws IOException {
        fileOutputStream.flush();
    }

    @Override
    public void writeString(String s) throws IOException {
        fileOutputStream.write(s.getBytes());
    }

    @Override
    public void close() throws IOException {
        fileOutputStream.close();
    }
}

