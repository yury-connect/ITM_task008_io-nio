package task1810;

import java.io.*;

/* 
DownloadException
1 Считывать с консоли имена файлов.
2 Если файл меньше 1000 байт, то:
2.1 Закрыть потоки работы с файлами.
2.2 Выбросить исключение DownloadException.


Requirements:
1. Программа должна считать имена файлов с консоли.
2. Для чтения из файлов используй поток FileInputStream.
3. Программа должна работать, пока введенный файл не окажется меньше 1000 байт.
4. Программа должна завершиться исключением DownloadException.
5. Поток FileInputStream должен быть закрыт.*/

public class Solution {
    private static final int MINIMUM_SIZE = 1_000; // минимально возможный размер файла в байтах

    public static void main(String[] args) throws DownloadException {

        try {
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            String pathDefault = System.getProperty("user.dir") + "/08 Знакомство с потоками InputStreamOutputStream, FileInputStream, FileOutputStream, Wrapper (IO_NIO - 0)/task1810/";
            System.out.println("\nВведите рабочую директорию.\nПо умолчанию рабочая директория назначена:\n\t" + pathDefault);

            String path;
            if ((path = consoleReader.readLine().trim()).length() == 0) { // Если не ввели ничего - то директория назначится по умолчанию.
                path = pathDefault;
            }

            String fileName;
            long fileSize;
            while (true) {
                System.out.println("Введите имя проверяемого файла: ");
                fileName = consoleReader.readLine().trim();

                File file = new File(path + fileName); // Получение размера файла через File
                fileSize = file.length();
                System.out.println("Директория файла: " + file.getPath());
                System.out.println("Имя файла: " + file.getName());
                System.out.println("Размер файла: " + fileSize + " байт");
                if (fileSize < MINIMUM_SIZE) {
                    throw new DownloadException("\n\nРазмер файл '" + file.getPath() + file.getName() + "' составляет '" + fileSize + "' байт, сто меньше '" + MINIMUM_SIZE + "' байт.");
                }
                System.out.println("\tФайл прошел проверку!\n");
            }
        } catch (IOException e) {
            System.out.println("Произошла непредвиденная ошибка во время ввода с клавиатуры");
            e.printStackTrace();
        }
    }

    public static class DownloadException extends Exception {

        public DownloadException(String message) {
            super(message);
        }
    }
}
