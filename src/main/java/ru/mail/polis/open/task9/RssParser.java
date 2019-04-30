package ru.mail.polis.open.task9;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import org.jetbrains.annotations.NotNull;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

class RssParser {

    private SyndFeed feed;
    private static final String URL_ERROR = "Incorrect link";
    private final StringBuilder builder;

    RssParser(@NotNull String link) {
        changeLink(link);
        builder = new StringBuilder();
    }

    void readToFile(@NotNull String path, int number) throws IOException {
        FileWriter writer = new FileWriter(path);
        writer.write(readToString(number));
        writer.close();
    }

    String readToString(int number) {
        builder.setLength(0);
        var entry = feed.getEntries().get(--number);
        builder.append(entry.getTitle().toUpperCase());
        builder.append("\n");
        builder.append(entry.getDescription().getValue());
        builder.append("\n");
        builder.append(entry.getLink());
        builder.append("\n");
        builder.append(entry.getPublishedDate());
        return builder.toString();
    }

    List<String> getNewsList() {
        List<String> newsList = new ArrayList<>();
        int index = 0;
        for (var entry : feed.getEntries()) {
            newsList.add(++index + ". " + entry.getTitle());
        }
        return newsList;
    }

    private void changeLink(@NotNull String link) {
        SyndFeedInput input;
        try {
            input = new SyndFeedInput();
            feed = input.build(new XmlReader(new URL(link)));
        } catch (Exception e) {
            throw new IllegalArgumentException(URL_ERROR);
        }
    }
}
