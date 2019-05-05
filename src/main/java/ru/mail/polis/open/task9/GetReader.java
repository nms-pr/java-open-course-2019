package ru.mail.polis.open.task9;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

public class GetReader {
    URL getUrl;
    SyndFeed syndFeed;
    FileWriter writer;

    public GetReader(URL getUrl, String fileName) throws IOException {
        this.getUrl = getUrl;
        this.writer = new FileWriter(fileName);
    }

    public void read() {
        try {
            SyndFeedInput input = new SyndFeedInput();
            syndFeed = input.build(new XmlReader(getUrl));
            for (SyndEntry feed : syndFeed.getEntries()) {
                writer.write(
                        feed.getTitle() + "\n"
                                + feed.getDescription().getValue() + "\n"
                                + feed.getLink() + "\n"
                                + feed.getPublishedDate() + "\n\n"
                );
            }
            writer.flush();
            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

}
