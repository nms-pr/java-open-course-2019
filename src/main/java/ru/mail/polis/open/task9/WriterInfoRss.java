package ru.mail.polis.open.task9;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class WriterInfoRss {

    private URL url;
    private File file;
    private SyndFeed feed;

    WriterInfoRss(String link, String file) {
        try {
            this.url = new URL(link);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        this.file = new File(file);
        SyndFeedInput input = new SyndFeedInput();

        try {
            if (url == null) {
                throw new IllegalArgumentException("URL cannot be a NULL");
            }

            feed = input.build(new XmlReader(url));
        } catch (IOException | FeedException e) {
            e.printStackTrace();
        }
    }

    void writeInFile() {
        try (FileWriter fw = new FileWriter(file)) {
            fw.write(
                (feed.getTitle() != null
                    ? feed.getTitle()
                    : "title : NULL")
                + "\n");

            fw.write(
                (feed.getDescription() != null
                    ? feed.getDescription()
                    : "description : NULL")
                + "\n");

            fw.write(
                (feed.getPublishedDate() != null
                    ? feed.getPublishedDate().toString()
                    : "time : NULL")
                + "\n");

            fw.write(feed.getLink() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        WriterInfoRss writer = new WriterInfoRss(
            "https://www.fontanka.ru/fontanka.rss",
            "result.txt"
        );
        writer.writeInFile();
    }
}
