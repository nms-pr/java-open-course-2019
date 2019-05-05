package ru.mail.polis.open.task9;

import java.io.IOException;
import java.net.URL;

public class Answer {
    public static void main(String[] args) throws IOException {
        URL url = new URL("https://www.gazeta.ru/export/rss/tech_articles.xml");
        GetReader feedReader = new GetReader(url, "src\\main\\java\\ru\\mail\\polis\\open\\task9\\output.txt");
        feedReader.read();

    }
}
