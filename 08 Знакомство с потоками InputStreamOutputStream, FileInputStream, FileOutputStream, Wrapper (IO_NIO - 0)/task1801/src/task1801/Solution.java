package task1801;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/* 
Максимальный байт
Ввести с консоли имя файла.
Найти максимальный байт в файле, вывести его на экран.
Закрыть поток ввода-вывода.


Requirements:
1. Программа должна считывать имя файла с консоли.
2. Для чтения из файла используй поток FileInputStream.
3. В консоль должен выводиться максимальный байт, считанный из файла.
4. Поток чтения из файла должен быть закрыт.*/

public class Solution {
    public static void main(String[] args) throws Exception {

        System.out.println(System.getProperty("user.dir")); // выведет текущий путь от корня диска до корня проекта
        final String path = System.getProperty("user.dir") + "/08 Знакомство с потоками InputStreamOutputStream, FileInputStream, FileOutputStream, Wrapper (IO_NIO - 0)/task1801/";

        System.out.println("Введите имя файла с данными.\n"
                + "ПОДСКАЗКА: Сейчас нужно ввести '1.txt'.");

        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        final String fileName = consoleReader.readLine();
//        final String fileName = "1.txt"; // использую для тестирования
        consoleReader.close();

        try (InputStreamReader fileReader = new InputStreamReader(new FileInputStream(path + fileName))) {
            int maxByte = Byte.MIN_VALUE;
            System.out.println("Прочитанные байты:\n---");
            int current;
            while ((current = fileReader.read()) != -1) {
                maxByte = Math.max(maxByte, current);
                System.out.printf("Прочитан байт: %d\t Символ соответствующий байту: %c\n", current, current);
            }
            System.out.printf("---\n\tMax byte: %d\t Символ соответствующий байту: %c", maxByte, + maxByte);
        }
    }
}
