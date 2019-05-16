package ru.mail.polis.open.task9;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;

public class TestClass {
    private static String TEXT = "АНДРОИД ВОССТАНОВИТ ФЕРМУ В ЯПОНИИ\n"
            + "\n"
            + "                \n"
            + "Заброшенную землю рядом с токийским университетом Нисёгакуся передали андроиду\n"
            + "с внешностью известного японского хозяйственника.\n"
            + "\n"
            + "            \n"
            + "http://example.com/2023/07/04/android-happy-farmer\n"
            + "Tue Jul 04 04:20:00 MSK 2023";
    private static String NEWS_LIST = "1. Андроид восстановит ферму в Японии";
    private static String LINK = "https://cache-spb02.cdn.yandex.net/download.cdn.yandex.net/from/yandex.ru/support/ru/zen/files/rss-example.xml";
    private static RssParser parser;

    @BeforeAll
    static void init() {
        parser = new RssParser(LINK);
    }

    @Test
    void readToStringTest() {
        assertEquals(parser.readToString(1), TEXT);
    }

    @Test
    void getNewsListTest() {
        assertEquals(parser.getNewsList().get(0), NEWS_LIST);
    }

    @Test
    void readToFileTest() {
        StringBuilder result = new StringBuilder();
        try {
            parser.readToFile("file", 1);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        try (FileReader reader = new FileReader("file")) {
            int c;
            while ((c = reader.read()) != -1) {
                result.append((char)c);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        assertEquals(result.toString(), TEXT);
    }
}
