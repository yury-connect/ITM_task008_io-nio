package task1824;

import java.io.*;

/*
Файлы и исключения
Читайте с консоли имена файлов.
Если файла не существует (передано неправильное имя файла), то перехватить исключение FileNotFoundException,
вывести в консоль переданное неправильное имя файла и завершить работу программы.
Закрыть потоки.
Не используй System.exit();


Requirements:
1. Программа должна считывать имена файлов с консоли.
2. Для каждого файла нужно создавать поток для чтения.
3. Если файл не существует, программа должна перехватывать исключение FileNotFoundException.
4. После перехвата исключения, программа должна вывести имя файла в консоль и завершить работу.
5. Потоки для чтения из файла должны быть закрыты.
6. Команду "System.exit();" использовать нельзя.*/


public class Solution {
    private static final String PATH = System.getProperty("user.dir") + "/08 Знакомство с потоками InputStreamOutputStream, FileInputStream, FileOutputStream, Wrapper (IO_NIO - 0)/task1824/";

    public static void main(String[] args) {
        BufferedReader consoleReader = null;
        BufferedReader fileReader = null;
        String fileName = null;
        File file = null;

        try {
            consoleReader = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                System.out.println("\nВведите имя файла:\t");
                fileName = consoleReader.readLine().trim();
                file = new File(PATH + fileName);
                fileReader = new BufferedReader(new FileReader(file));
            }

        } catch (FileNotFoundException e) {
            System.out.printf("Файл с данным именем '%s' в данной директории отсутствует;" +
                            "\nДиректория (каталог), в котором осуществлялся поиск: \n\t'%s';\nСообщение об ошибке:\n\t'%s'"
                    , fileName, file.getPath(), e.getMessage());

        } catch (IOException e) {
            System.out.println("Во время ввода с клавиатуры произошла ошибка. Сообщение об ошибке:\n\t" + e.getMessage());
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
