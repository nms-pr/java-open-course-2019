package ru.mail.polis.open.invertedIndex;

import java.io.IOException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Wrong number of arguments.");
        }

        try {
            ConfigFileProvider.readFile(args[0]);
        } catch (IOException e) {
            System.out.println("Error reading configuration file.");
        }

        try {
            DatabaseProvider.createConnection();
        } catch (SQLException e) {
            System.out.println("Ошибка подключения к базе.");
            e.printStackTrace();
        }

        try {
            ParsersManager.parse(ConfigFileProvider.getNumberOfStreams(), ConfigFileProvider.getDepthOfSearch());
        } catch (SQLException e) {
            System.out.println("Не могу достать ссылки.");
        }

        for (;;) {
            ConsoleHandler.askForRequest();
            try {
                OutputManager.write(
                        DatabaseProvider.selectRequest(ConsoleHandler.getRequest()),
                        ConsoleHandler.getOutputFilePath(),
                        ConsoleHandler.getRequest()
                );
            } catch (SQLException e) {
                System.out.println("Не могу достать данные!");
            }
        }

    }


}
