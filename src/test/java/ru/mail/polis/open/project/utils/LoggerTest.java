package ru.mail.polis.open.project.utils;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class LoggerTest {

    @Test
    void testResetRequest() {
        Logger.addInfoAboutRequest(
            "Moscow",
            444563L,
            "News"
        );
        Logger.addInfoAboutRequest(
            "Moscow",
            444563L,
            "Weather"
        );
        Logger.addInfoAboutRequest(
            "Moscow",
            444563L,
            "Weather"
        );

        try (BufferedReader br = new BufferedReader(
            new FileReader(
                new File("logs/Statistic-" + 444563L + ".txt")
            )
        )) {
            assertEquals(3, br.lines().count());

        } catch (IOException e) {
            e.printStackTrace();
        }
        Logger.clearLog(444563L);
        assertFalse(Files.exists(Paths.get("logs/Statistic-" + 444563L + ".txt")));
    }
}