package task1822;

import java.io.*;
import java.util.Set;
import java.util.TreeSet;

/* 
Поиск данных внутри файла
Считать с консоли имя файла.
Найти в файле информацию, которая относится к заданному id, и вывести ее на экран в виде, в котором она записана в файле.
Программа запускается с одним параметром: id (int).
Закрыть потоки.

В файле данные разделены пробелом и хранятся в следующей последовательности:
id productName price quantity
где id - int.
productName - название товара, может содержать пробелы, String.
price - цена, double.
quantity - количество, int.

Информация по каждому товару хранится в отдельной строке.

Пример содержимого файла:
194 хлеб 12.6 25
195 масло сливочное 52.2 12
196 молоко 22.9 19

Пример вывода для id = 195:
195 масло сливочное 52.2 12


Requirements:
1. Программа должна считать имя файла с консоли.
2. Создай для файла поток для чтения.
3. Программа должна найти в файле и вывести информацию о id, который передается первым параметром.
4. Поток для чтения из файла должен быть закрыт.*/


public class Solution {
    private static final String PATH = System.getProperty("user.dir") + "/08 Знакомство с потоками InputStreamOutputStream, FileInputStream, FileOutputStream, Wrapper (IO_NIO - 0)/task1822/";

    public static void main(String[] args) {
        BufferedReader consoleReader = null;
        String fileName;
        int id;
        BufferedReader fileReader = null;
        try {
            consoleReader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("\nВведите имя файла с данными.\n\t"
                    + "ПОДСКАЗКА: Сейчас нужно ввести 'input_data.txt'.");
            fileName = "input_data.txt"; // использую для тестирования
//            fileName = consoleReader.readLine().trim();

            System.out.println("Введите id искомого товара: ");
            id = 195; // использую для тестирования
//            id = Integer.parseInt(consoleReader.readLine().trim());

            File file = new File(PATH + fileName);
            fileReader = new BufferedReader(new FileReader(file));

            String line;
            while ((line = fileReader.readLine()) != null) {
                String[] lexemes = line.split("\\s+");
                int idCurrent = Integer.parseInt(lexemes[0]);
                if (idCurrent == id) {
                    System.out.printf("\nНайдено совпадение по id = '%d':\n\t%s\n",id, line);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);

        } catch (NumberFormatException e) {
            System.out.println("Не корректный формат id. GПервой лексемой в строке должна идти числовая величина.");
            throw new RuntimeException(e);

        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            if (consoleReader != null) {
                try {
                    consoleReader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
