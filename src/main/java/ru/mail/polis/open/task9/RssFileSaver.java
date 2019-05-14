package ru.mail.polis.open.task9;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class RssFileSaver {

    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

    public static void writeRssToFile(String rssUrl, String outFile) throws FeedException, IOException {
        URL feedSource = new URL(rssUrl);
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = input.build(new XmlReader(feedSource));
        BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));
        for (SyndEntry entry : feed.getEntries()) {
            String description = entry.getDescription().getValue();
            if (description != null) {
                description = description.replaceAll("<[^>]*>", "");
                if (!description.isEmpty()) {
                    ArrayList<String> splDescr = new ArrayList<>(Arrays.asList(description.split("\n")));
                    splDescr.removeIf(o -> o.trim().equals(""));
                    if (splDescr.size() != 0) {
                        StringBuilder builder = new StringBuilder();
                        for (String str: splDescr) {
                            builder.append(str.trim()).append(LINE_SEPARATOR);
                        }
                        description = builder.toString();
                    } else {
                        description = null;
                    }
                } else {
                    description = null;
                }
            }
            ArrayList<String> entryInfo = new ArrayList<>(Arrays.asList(entry.getTitle(),
                    description,
                    entry.getLink(),
                    entry.getPublishedDate().toString()));
            for (String str : entryInfo) {
                if (str != null) {
                    writer.write(str + LINE_SEPARATOR);
                }
            }
        }
        writer.flush();
        writer.close();
    }
}
