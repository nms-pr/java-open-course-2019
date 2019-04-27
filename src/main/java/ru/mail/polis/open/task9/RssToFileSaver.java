package ru.mail.polis.open.task9;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import org.jetbrains.annotations.NotNull;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

public class RssToFileSaver {
    private SyndFeedInput feedBuilder;
    private String lineSeparator;
    private SimpleDateFormat dateFormat;

    public RssToFileSaver(@NotNull String lineSeparator, @NotNull String dateFormatPattern) {
        feedBuilder = new SyndFeedInput();
        this.lineSeparator = lineSeparator;
        dateFormat = new SimpleDateFormat(dateFormatPattern);
    }

    public void saveToFile(@NotNull URL feedSource, @NotNull String filePath)
            throws FeedException, IOException {

        SyndFeed feed = feedBuilder.build(new XmlReader(feedSource));

        try (OutputStream fileOut = new FileOutputStream(new File(filePath))) {
            for (SyndEntry entry : feed.getEntries()) {
                fileOut.write(entry.getTitle().getBytes());
                fileOut.write(lineSeparator.getBytes());

                fileOut.write(entry.getDescription().getValue().getBytes());
                fileOut.write(lineSeparator.getBytes());

                fileOut.write(entry.getLink().getBytes());
                fileOut.write(lineSeparator.getBytes());

                fileOut.write(dateFormat.format(entry.getPublishedDate()).getBytes());
                fileOut.write(lineSeparator.getBytes());
                fileOut.write(lineSeparator.getBytes());
            }
        }
    }
}