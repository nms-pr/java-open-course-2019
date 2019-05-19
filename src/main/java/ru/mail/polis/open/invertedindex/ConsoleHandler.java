package ru.mail.polis.open.invertedindex;

import java.util.Scanner;

public class ConsoleHandler {

    private static String request;
    private static String outputFilePath;

    /**
     *  asking user for request {@code request}
     *  asking user for output method {@code requestOutput}
     *  asking user for output file name {@code outputFilePath}
     *      (if output to file)
     */
    public static void askForRequest() {

        request = null;
        outputFilePath = null;

        System.out.println("Enter your request:");
        Scanner scanner = new Scanner(System.in);
        request = scanner.nextLine();

        String choice = "";
        while (!choice.equals("1") && !choice.equals("2")) {

            System.out.println(
                    "Where to output the result?\n"
                            + "1)Command line\n"
                            + "2)File"
            );

            choice = scanner.nextLine();
        }

        if (choice.equals("2")) {
            System.out.println("Enter output file name:");
            outputFilePath = scanner.nextLine();
        }
        System.out.flush();

    }

    public static String getRequest() {
        return request;
    }

    public static String getOutputFilePath() {
        return outputFilePath;
    }
}
