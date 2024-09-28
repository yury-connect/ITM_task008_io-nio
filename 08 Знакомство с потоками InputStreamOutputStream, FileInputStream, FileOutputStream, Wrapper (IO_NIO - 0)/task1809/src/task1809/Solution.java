package task1809;

import java.io.*;
import java.util.*;

/* 
Реверс файла
Считать с консоли 2 имени файла: файл1, файл2.
Записать в файл2 все байты из файл1, но в обратном порядке.
Закрыть потоки.


Requirements:
1. Программа должна два раза считать имена файлов с консоли.
2. Для чтения из файла используй поток FileInputStream, для записи в файл - FileOutputStream
3. Во второй файл нужно записать все байты из первого в обратном порядке.
4. Потоки FileInputStream и FileOutputStream должны быть закрыты.*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir")); // выведет текущий путь от корня диска до корня проекта
        final String path = System.getProperty("user.dir") + "/08 Знакомство с потоками InputStreamOutputStream, FileInputStream, FileOutputStream, Wrapper (IO_NIO - 0)/task1809/";

        String srcFileName = null;
        String dstFileName = null;
        try (BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));){
            System.out.println("1. Введите имя файла с данными (источник)."
                    + "\n\tПОДСКАЗКА: Сейчас нужно ввести '1.txt'.");
            srcFileName = consoleReader.readLine();

            System.out.println("2. Введите имя файла для записи данных (приемник).\n"
                    + "\tПОДСКАЗКА: Сейчас нужно ввести '2.txt'.");
            dstFileName = consoleReader.readLine();
        } catch (IOException e) {
            System.out.println("Произошла непредвиденная ошибка во время ввода с клавиатуры");
            e.printStackTrace();
        }

//        final String srcFileName = "1.txt"; // использую для тестирования
//        final String dstFileName = "2.txt"; // использую для тестирования

        List<Integer> result = new ArrayList<>(); // Результат будем засовывать в List
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(path + srcFileName)));
             FileOutputStream fileWriter = new FileOutputStream(path + dstFileName)) {

            String line;
            while ((line = fileReader.readLine()) != null) { // Реализую многострочный вариант ввода, т.е. в файле данные могут распологаться в нескольких строках, НО разделены должны быть ПРОБЕЛОМ!
                String[] lexemes = line.split("\\s+"); // Разделение строки на лексемы по пробелам
                for (String lexeme : lexemes) {
                    try {
                        result.add(Integer.parseInt(lexeme)); // добавляем ТОЛЬКО уникальные байты в TreeSet
                    } catch (NumberFormatException e) {
                        System.out.println("Некорректный байт: " + lexeme); // Игнорируем некорректные данные, если не удается преобразовать строку в число
                    }
                }
            }

            System.out.println("---\n\tВывожу коллекцию прочитанных байт:");
            result.stream().forEach(i -> System.out.print(" " + i));

            result.sort(Comparator.reverseOrder());
            System.out.println("\n\n\tВывожу коллекцию записываемых байт (в обратном порядке):");
            result.stream().forEach(i -> System.out.print(" " + i));

            for (Integer i: result) {
                byte[] byteArray = (i + " ").getBytes();  // Преобразуем каждую строку в байты
                fileWriter.write(byteArray); // Записываем байты в файл
            }
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

