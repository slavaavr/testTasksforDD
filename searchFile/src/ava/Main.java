package ava;

import java.util.Scanner;

public class Main {

    public static void main(String[] arg) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите директорию: ");
        String path = scanner.nextLine();
        RecursiveSearch rs = new RecursiveSearch();
        rs.search(path);
    }

}

