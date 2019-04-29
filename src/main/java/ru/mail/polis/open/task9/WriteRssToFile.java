package ru.mail.polis.open.task9;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Logger;

public class WriteRssToFile {
    private static final Logger LOGGER = Logger.getLogger(WriteRssToFile.class.getName());

    public WriteRssToFile(URL feedSource, String fileName) {
//        this.feedSource = feedSource;
//        this.fileName = fileName;

        SyndFeed feed = readAnExternalFeed(feedSource);
        File file = createFile(fileName);
        writeFeedToFile(feed, file);
    }


    private SyndFeed readAnExternalFeed(URL feedSource) {
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = null;
        try {
            feed = input.build(new XmlReader(feedSource));
        } catch (FeedException e) {
            LOGGER.warning(e.getMessage());
        } catch (IOException e) {
            LOGGER.warning(e.getMessage());
        }
        return feed;
    }

    private File createFile(String fileName) {
        File file = new File(fileName + ".txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    private boolean writeFeedToFile(SyndFeed feed, File file) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);

            for (SyndEntry entry : feed.getEntries()) {
                fileWriter.append((char) Character.LINE_SEPARATOR);
                fileWriter.append(entry.getTitle());
                fileWriter.append((char) Character.LINE_SEPARATOR);
                fileWriter.append(entry.getDescription().getValue());
//                fileWriter.append((char) Character.LINE_SEPARATOR);
                fileWriter.append(entry.getLink());
                fileWriter.append((char) Character.LINE_SEPARATOR);
                fileWriter.append(entry.getPublishedDate().toString());
                fileWriter.append((char) Character.LINE_SEPARATOR);
            }
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

}
