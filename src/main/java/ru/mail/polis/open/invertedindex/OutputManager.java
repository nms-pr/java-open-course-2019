package ru.mail.polis.open.invertedindex;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

public class OutputManager {

    public static void write(Set<String> outputInformation, String outputFileName, String request) {

        if (outputFileName == null) {
            writeToConsole(outputInformation);
        } else {
            writeToFile(outputInformation, outputFileName, request);
        }

    }

    private static void writeToFile(Set<String> outputInformation, String outputFileName, String request) {

        try (FileWriter writer = new FileWriter(outputFileName)) {

            if (outputInformation == null) {
                writer.write("Не найдено результатов по запросу: \"" + request + "\"");
            } else {
                writer.write("Request:");
                writer.write("\n\t" + request);
                writer.write("\nResult:");

                for (String line : outputInformation) {
                    writer.write("\n\t" + line);
                }
            }

            writer.flush();
            System.out.println("Done.");

        } catch (IOException e) {
            System.out.println("Error writing result to file.");
        }

    }

    private static void writeToConsole(Set<String> outputInformation) {

        if (outputInformation == null) {
            System.out.println("По вашму запросу ничего не найдено");
            return;
        }

        System.out.print("Result:\n");

        for (String line: outputInformation) {
            System.out.print("\t" + line + "\n");
        }
        System.out.flush();
    }

}
