package task1805;

import java.io.*;
import java.util.Set;
import java.util.TreeSet;

/* 
Сортировка байт
Ввести с консоли имя файла.
Считать все байты из файла.
Не учитывая повторений - отсортировать их по байт-коду в возрастающем порядке.
Вывести на экран.
Закрыть поток ввода-вывода.

Пример байт входного файла:
44 83 44

Пример вывода:
44 83


Requirements:
1. Программа должна считывать имя файла с консоли.
2. Для чтения из файла используй поток FileInputStream.
3. В консоль через пробел должны выводиться все уникальные байты из файла в порядке возрастания.
4. Данные в консоль должны выводится в одну строку.
5. Поток чтения из файла должен быть закрыт.*/

public class Solution {
    public static void main(String[] args) throws Exception {
        System.out.println(System.getProperty("user.dir")); // выведет текущий путь от корня диска до корня проекта
        final String path = System.getProperty("user.dir") + "/08 Знакомство с потоками InputStreamOutputStream, FileInputStream, FileOutputStream, Wrapper (IO_NIO - 0)/task1805/";

        System.out.println("Введите имя файла с данными.\n"
                + "ПОДСКАЗКА: Сейчас нужно ввести '1.txt'.");

        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        final String fileName = consoleReader.readLine();
//        final String fileName = "1.txt"; // использую для тестирования
        consoleReader.close();

        Set<Integer> result = new TreeSet<>(); // т.к. сортировать нужно по возрастанию - используем TreeSet, в кот. элементы хранятся отвортированныом виде по возрастанию
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(path + fileName)))) {
            System.out.println("---\n\tПрочитанные байты:\n");
            String line;
            while ((line = fileReader.readLine()) != null) { // Реализую многострочный вариант ввода, т.е. в файле данные могут распологаться в нескольких строках, НО разделены должны быть ПРОБЕЛОМ!
                String[] lexemes = line.split("\\s+"); // Разделение строки на лексемы по пробелам
                for (String lexeme : lexemes) {
                    System.out.print(" " + lexeme);
                    try {
                        result.add(Integer.parseInt(lexeme)); // добавляем ТОЛЬКО уникальные байты в TreeSet
                    } catch (NumberFormatException e) {
                        System.out.println("Некорректный байт: " + lexeme); // Игнорируем некорректные данные, если не удается преобразовать строку в число
                    }
                }
            }
            System.out.printf("\n---\n\tВывожу отсортированную ПО ВОЗРАСТАНИЮ коллекцию:");
            result.stream().forEach(i -> System.out.print(" " + i));
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }
}
