package ru.mail.polis.open.task9;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.jetbrains.annotations.NotNull;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class NewsReader {

    private URL feedUrl;
    private String fileName;
    private DateFormat dateFormat;

    public NewsReader(@NotNull URL feedUrl, @NotNull String fileName) throws FileNotFoundException {
        this.feedUrl = feedUrl;
        if (fileName.isEmpty()) {
            throw new FileNotFoundException();
        }
        this.fileName = fileName;
        dateFormat = new SimpleDateFormat();
    }

    public void read() throws IOException, FeedException {
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed syndFeed = input.build(new XmlReader(feedUrl));
        Path file = Path.of(fileName);
        if (!file.toFile().exists()) {
            Files.createFile(file);
        }
        FileOutputStream outputStream = new FileOutputStream(file.toFile());
        for (SyndEntry entry : syndFeed.getEntries()) {
            outputStream.write(entry.getTitle().getBytes());
            outputStream.write(System.lineSeparator().getBytes());

            outputStream.write(entry.getDescription().getValue().getBytes());
            outputStream.write(System.lineSeparator().getBytes());

            outputStream.write(entry.getLink().getBytes());
            outputStream.write(System.lineSeparator().getBytes());

            outputStream.write(dateFormat.format(entry.getPublishedDate()).getBytes());
            outputStream.write(System.lineSeparator().getBytes());
        }
        outputStream.close();
    }
}


