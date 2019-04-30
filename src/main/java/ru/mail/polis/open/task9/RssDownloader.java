package ru.mail.polis.open.task9;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class RssDownloader {
    private URL address;
    private String filename;
    private DateFormat dateFormat;

    RssDownloader(URL address, String filename) throws FileNotFoundException {
        this.address = address;
        if (filename.isEmpty()) {
            throw new FileNotFoundException();
        }
        this.filename = filename;
        dateFormat = new SimpleDateFormat();
    }

    public void download() throws IOException, FeedException {
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = input.build(new XmlReader(address));
        Path file = Path.of(filename);
        if (!file.toFile().exists()) {
            Files.createFile(file);
        }
        FileOutputStream output = new FileOutputStream(file.toFile());
        for (SyndEntry feedEntry : feed.getEntries()) {
            output.write(feedEntry.getTitle().getBytes());
            output.write(System.lineSeparator().getBytes());
            output.write(feedEntry.getDescription().getValue().getBytes());
            output.write(System.lineSeparator().getBytes());
            output.write(feedEntry.getLink().getBytes());
            output.write(System.lineSeparator().getBytes());
            output.write(dateFormat.format(feedEntry.getPublishedDate()).getBytes());
            output.write(System.lineSeparator().getBytes());
        }
        output.close();
    }
}
