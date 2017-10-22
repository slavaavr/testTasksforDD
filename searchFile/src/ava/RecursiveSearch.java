package ava;

import javafx.util.Pair;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 *  RecursiveSearch - класс, предназначенный для рукурсивного поиска zip-архивов, в которых хранятся xml файлы.
 */
public class RecursiveSearch {
    private List<Entity> entities;

    public RecursiveSearch() {
        this.entities = new ArrayList<>();
    }

    /**
     * Метод search(String path) - производит запуск поиска в указанном каталоге path.
     */
    public void search(String path) {
        File dir = new File(path);
        if (dir.exists()) {
            recSearch(dir);
            this.entities.forEach(System.out::println);
        } else {
            System.out.println("Путь не найден!");
        }
    }

    /**
     * Метод recSearch(File dir) - рекурсивно обходит все каталоки, собирая информацию вида: путь до zip-архива,
     * путь до xml-файла в архиве, номера строк на которых расположены url и сами url.
     */
    private void recSearch(File dir) {
        if (dir.isFile()) {
            if (dir.getName().contains(".zip")) {  // Проверка, что файл - это zip-архив.
                try (ZipInputStream zin = new ZipInputStream(new FileInputStream(dir.getAbsoluteFile()))) { // Получение потока для чтения архива.
                    ZipEntry ze;
                    while ((ze = zin.getNextEntry()) != null) { // Считывание элементов архива.
                        if (ze.getName().contains(".txt")) { // Проверка, что элемент архива - это xml-файл.
                            List<Pair<Integer, String>> lineNumberAndUrl = new ArrayList<>(); // Коллекция для сбора номеров строк и url-ссылок.
                            Scanner in = new Scanner(zin); // Использование потокового фильтра для удобства считывания данных из xml-файла.
                            int lineNumber = 1; // Т.к. считывание происходит построчно, то эта переменная служит для подсчета кол-ва строк.
                            String url; // Переменная для потецниального хранения url-ссылки.
                            while (in.hasNextLine()) {
                                url = in.nextLine(); // Считывание строки.
                                if (url.contains("http")) // Проверка наличия в строке подстроки "http".
                                    lineNumberAndUrl.add(new Pair<>(lineNumber, url.trim())); // Добавление в коллекцию номера строки и url.
                                lineNumber++;
                            }
                            this.entities.add(new Entity(dir.getAbsolutePath(), dir.getAbsolutePath() + "\\" + ze.getName(), lineNumberAndUrl)); // Добавление в коллекцию данных, собранных из одного xml-файла.
                            zin.closeEntry();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            File[] subDirectories = dir.listFiles(); // Получение поддерикторий.
            for (File subDirectory : subDirectories) {
                recSearch(subDirectory); // Рекурсивный обход.
            }
        }
    }
}
