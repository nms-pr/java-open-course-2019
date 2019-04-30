package ru.mail.polis.open.task9;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class RecordInfoTest {

    private static RecordInfo record;
    private static String link;
    private static String file;

    @BeforeAll
    static void setup() throws MalformedURLException {
        link = "https://lenta.ru/rss/top7";
        file = "TestRecordInfo";
        record = new RecordInfo(link, file);
    }

    @Test
    void illegalArgumentExceptionTest() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new RecordInfo(
                    null,
                    file
            )
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> new RecordInfo(
                    link,
                    null
            )
        );
    }

    @Test
    void testRecording() throws IOException {
        record.recordingToTheFile();
        BufferedReader br = new BufferedReader(new FileReader(new File(file)));
        assertNotEquals(null, br.readLine());
        List<String[]> dataList = new ArrayList<>();
        try {
            SyndFeed feed = new SyndFeedInput().build(new XmlReader(new File(file)));
            for (SyndEntry entry : feed.getEntries()) {
                String[] data = new String[4];
                data[0] = entry.getTitle();
                data[1] = entry.getDescription().getValue();
                data[2] = entry.getPublishedDate().toString();
                data[3] = entry.getLink();
                dataList.add(data);
            }
        } catch (IOException | FeedException e) {
            e.getStackTrace();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(new File(file)))) {
            for (String[] data : dataList) {
                assertEquals(
                        data[0].replaceAll(" ", "").replaceAll("\n", ""),
                        reader.readLine().replaceAll(" ", "")

                );
                assertEquals(
                        data[1].replaceAll(" ", "").replaceAll("\n", ""),
                        reader.readLine().replaceAll(" ", "")
                );
                assertEquals(
                        data[2].replaceAll(" ", "").replaceAll("\n", ""),
                        reader.readLine().replaceAll(" ", "")
                );
                assertEquals(
                        data[3].replaceAll(" ", "").replaceAll("\n", ""),
                        reader.readLine().replaceAll(" ", "")
                );
                reader.readLine();
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
}