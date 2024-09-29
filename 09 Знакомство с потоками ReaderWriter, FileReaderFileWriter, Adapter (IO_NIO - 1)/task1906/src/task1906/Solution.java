package task1906;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/* 
Четные символы
Считать с консоли 2 имени файла.
Вывести во второй файл все символы с четным порядковым номером (нумерация начинается с 1).

Пример первого файла:
text in file
Вывод во втором файле:
eti ie
Закрыть потоки ввода-вывод


Requirements:
1. Программа должна считывать имена файлов с консоли (используй BufferedReader).
2. BufferedReader для считывания данных с консоли должен быть закрыт.
3. Программа должна считывать содержимое первого файла (используй FileReader c конструктором String).
4. Поток чтения из файла (FileReader) должен быть закрыт.
5. Программа должна записывать во второй файл все символы из первого файла с четным порядковым номером (используй FileWriter).
6. Поток записи в файл (FileWriter) должен быть закрыт.*/

public class Solution {
    public static void main(String[] args) {
        final String path = System.getProperty("user.dir") + "/09 Знакомство с потоками ReaderWriter, FileReaderFileWriter, Adapter (IO_NIO - 1)/task1906/";
        System.out.println(path); // выведет текущий путь от корня диска до корня проекта

        String srcFileName = null;
        String dstFileName = null;
        try (BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));){
            System.out.println("1. Введите имя файла с данными (источник)."
                    + "\n\tПОДСКАЗКА: Сейчас нужно ввести '1.txt'.");
            srcFileName = consoleReader.readLine().trim();
//            srcFileName = "1.txt"; // использую для тестирования

            System.out.println("2. Введите имя файла для записи данных (приемник).\n"
                    + "\tПОДСКАЗКА: Сейчас нужно ввести '2.txt'.");
            dstFileName = consoleReader.readLine().trim();
//            dstFileName = "2.txt"; // использую для тестирования

        } catch (IOException e) {
            System.out.println("Произошла непредвиденная ошибка во время ввода с клавиатуры");
            e.printStackTrace();
        }


        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(path + srcFileName)));
             FileOutputStream fileWriter = new FileOutputStream(path + dstFileName)) {

            String line;
            while ((line = fileReader.readLine()) != null) { // Реализую многострочный вариант ввода, т.е. в файле данные могут распологаться в нескольких строках, НО разделены должны быть ПРОБЕЛОМ!
                String result = processingString(line);
                byte[] byteArray = (result).getBytes();  // Преобразуем каждую строку в байты
                fileWriter.write(byteArray); // Записываем байты в файл
                fileWriter.write(System.lineSeparator().getBytes()); // Записываем символ новой строки
                System.out.println(result);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String processingString(String line) {
        StringBuilder buffer = new StringBuilder();
        char[] charArray = line.toCharArray();

        int currentIndex = 1;
        while (currentIndex < charArray.length) {
            buffer.append(charArray[currentIndex]);
            currentIndex += 2;
        }

        return buffer.toString();
    }
}
