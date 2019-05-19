package ru.mail.polis.open.project.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Logs information about user requests to files
 */
public class Logger {

    /**
     * Logs chat history to file
     * @param message - request
     * @param chatId - where thee request was made
     * @param ability - which of the bots ability was used
     */
    public static void addInfoAboutRequest(String message, Long chatId, String ability) {
        LocalDateTime messageRequestTime = LocalDateTime.now();

        try (FileWriter fw = new FileWriter("logs/Statistic-" + chatId + ".txt", true)) {
            fw.write(
                "chatId : "
                    + chatId.toString()
                    + " : Request about " + ability + ". City - "
                    + message + " at "
                    + messageRequestTime.getHour() + ":"
                    + messageRequestTime.getMinute() + " "
                    + messageRequestTime.getDayOfMonth() + "-"
                    + messageRequestTime.getMonth().getValue() + "-"
                    + messageRequestTime.getYear()
                    + "\n"
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized void clearLog(Long chatId) {
        new File("logs/Statistic-" + chatId + ".txt").delete();
    }

    public static synchronized void clearAllLogs() {
        for (File file : new File("logs").listFiles()) {
            file.delete();
        }
    }
}
