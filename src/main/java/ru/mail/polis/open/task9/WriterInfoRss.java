package ru.mail.polis.open.task9;

import com.rometools.rome.feed.synd.SyndContent;
import com.rometools.rome.feed.synd.SyndEntry;
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
    private boolean building;

    WriterInfoRss(String link, String file) {
        try {
            this.url = new URL(link);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        this.file = new File(file);
        this.building = false;
    }

    void build() {
        SyndFeedInput input = new SyndFeedInput();

        try {
            if (url == null) {
                throw new IllegalArgumentException("URL cannot be a NULL");
            }

            feed = input.build(new XmlReader(url));
            building = true;
        } catch (IOException | FeedException e) {
            e.printStackTrace();
        }
    }

    void writeToFile() {
        if (!building) {
            throw new NotDoneBuildException("You didn't build");
        }
        try (FileWriter fw = new FileWriter(file)) {
            for (SyndEntry entry : feed.getEntries()) {
                fw.write(
                    (entry.getTitle() != null
                        ? entry.getTitle()
                        : "title : NULL") + "\n"
                        + (entry.getDescription() != null
                        ? entry.getDescription()
                        : "description : NULL") + "\n"
                        + (entry.getPublishedDate() != null
                        ? entry.getPublishedDate().toString()
                        : "time : NULL") + "\n"
                        + entry.getLink() + "\n"
                );

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        public static void main(String[] args) {
        WriterInfoRss writer = new WriterInfoRss(
            "https://www.fontanka.ru/fontanka.rss",
            "result.txt"
        );
        writer.build();
        writer.writeToFile();
    }
}
