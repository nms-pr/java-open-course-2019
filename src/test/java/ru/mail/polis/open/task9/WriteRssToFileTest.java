package ru.mail.polis.open.task9;

import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;

class WriteRssToFileTest {

    @Test
    void test () throws MalformedURLException {
//        URL rss = new URL("https://lenta.ru/news/2019/04/22/nokia/");
        URL rss = new URL("https://lenta.ru/rss/news");
        String fileName = "testFile";
        WriteRssToFile writeRssToFile = new WriteRssToFile(rss, fileName);
    }
}