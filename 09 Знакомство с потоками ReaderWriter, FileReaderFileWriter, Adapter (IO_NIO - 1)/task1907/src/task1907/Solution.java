package task1907;

import java.io.*;

/* 
Считаем слово
Считать с консоли имя файла.
Файл содержит слова, разделенные знаками препинания.
Вывести в консоль количество слов "world", которые встречаются в файле.
Закрыть потоки.


Requirements:
1. Программа должна считывать имя файла с консоли (используй BufferedReader).
2. BufferedReader для считывания данных с консоли должен быть закрыт.
3. Программа должна считывать содержимое файла (используй FileReader c конструктором принимающим String).
4. Поток чтения из файла (FileReader) должен быть закрыт.
5. Программа должна выводить в консоль количество слов "world", которые встречаются в файле.*/

public class Solution {

    private static final String SEARCH_TERM = "world";

    public static void main(String[] args) {
        System.out.println("\nКорень проекта 'user.dir' = \t" + System.getProperty("user.dir"));
        final String path = "09 Знакомство с потоками ReaderWriter, FileReaderFileWriter, Adapter (IO_NIO - 1)/task1907/";
        System.out.println("Относительный путь 'path' = \t" + path); // выведет текущий ОТНОСИТЕЛЬНЫЙ путь от корня проекта
        System.out.print("\nВведите имя файла (источник).\t// ПОДСКАЗКА: Сейчас нужно ввести '1.txt' :   ");

        try (BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
             FileReader fileReader = new FileReader(path + consoleReader.readLine().trim());
             BufferedReader bufferedReader = new BufferedReader(fileReader)) { // hресурсы будут закрыты автоматически в обратном порядке их открытия

            int counter = 0;
            String line;
            while ((line = bufferedReader.readLine()) != null) { // вычитаем все строки
                String noPunctuation = line.replaceAll("[\\p{Punct}]", " "); // Заменяет все знаки препинания на пробелы
                String singleSpase = noPunctuation.replaceAll("\\s+", " ").trim(); // Заменяет все последовательности пробелов на один пробел.
                String[] lexemes = singleSpase.split("\\s+");
                for (String lexeme : lexemes) {
                    if (lexeme.equals(SEARCH_TERM)) {
                        counter++;
                    }
                }
            }
            System.out.printf("\nКоличество слов '%s' встречается в заданном тексте '%d' раз.", SEARCH_TERM, counter);

        } catch (IOException e) {
            System.out.println("Произошла непредвиденная ошибка во время ввода с клавиатуры");
            e.printStackTrace();
        }
    }
}
