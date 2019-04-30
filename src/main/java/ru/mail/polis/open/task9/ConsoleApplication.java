package ru.mail.polis.open.task9;

import java.util.List;
import java.util.Scanner;

public class ConsoleApplication {

    private static Scanner in;

    public static void main(String[] args) {
        RssParser parser;
        in = new Scanner(System.in);
        System.out.println("Укажите ссылку:");
        String link = in.nextLine();
        try {
            parser = new RssParser(link);
            while (showMenu(parser));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void showList(List<String> list) {
        for (String str : list) {
            System.out.println(str);
        }
    }

    private static boolean showMenu(RssParser parser) {
        int number;
        System.out.println("Введите число соответствующее пункту меню:");
        System.out.println("1. Показать список новостей");
        System.out.println("2. Записать в файл");
        System.out.println("3. Вывести в консоль");
        System.out.println("0. Выход");
        switch (in.nextInt()) {
            case 1:
                showList(parser.getNewsList());
                break;
            case 2:

                try {
                    System.out.println("Введите номер новости:");
                    number = in.nextInt();
                    System.out.println("Укажите путь к файлу:");
                    in.nextLine();
                    parser.readToFile(in.nextLine(), number);
                    System.out.println("Запись завершена");
                } catch (Exception e) {
                    System.out.println("Ошибка записи");
                }
                break;
            case 3:
                System.out.println("Введите номер новости:");
                System.out.println(parser.readToString(in.nextInt()));
                break;
            case 0:
                return false;
            default:
                System.out.println("Некорректный ввод.");
                break;
        }
        System.out.println();
        return true;
    }
}
