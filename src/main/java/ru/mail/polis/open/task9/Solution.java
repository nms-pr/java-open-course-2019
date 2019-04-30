package ru.mail.polis.open.task9;

import java.io.IOException;
import java.net.URL;

public class Solution {
    public static void main(String[] args) throws IOException {
        URL url = new URL("https://rss.stopgame.ru/rss_vpreview.xml");
        FeedReader feedReader = new FeedReader(url, "src\\main\\java\\ru\\mail\\polis\\open\\task9\\output.txt");
        feedReader.read();

    }
}
