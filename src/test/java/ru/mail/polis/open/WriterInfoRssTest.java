package ru.mail.polis.open;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.mail.polis.open.task9.NotDoneBuildException;
import ru.mail.polis.open.task9.WriterInfoRss;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WriterInfoRssTest {

    private static WriterInfoRss writer;
    private static String link;
    private static String file;

    @BeforeAll
    static void initFields() {
        link = "https://news-rossii.ru/feed/";
        file = "result.txt";
        writer = new WriterInfoRss(
            link,
            file
        );
    }

    @Test
    void testThrowExceptionDuaIncorrectArguments() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new WriterInfoRss(
                null,
                file
            )
        );

        assertThrows(
            NullPointerException.class,
            () -> new WriterInfoRss(
                link,
                null
            )
        );
    }

    @Test
    void testWorkingMethods() {
        assertThrows(
            NotDoneBuildException.class,
            () -> writer.writeToFile()
        );

        writer.build();

        assertDoesNotThrow(() -> writer.writeToFile());

        int sizeFeed = 0;
        List<String[]> listInfo = new ArrayList<>();

        try {
            SyndFeed feed = new SyndFeedInput().build(new XmlReader(new File("test.xml")));
            for (SyndEntry entry : feed.getEntries()) {
                String[] info = new String[4];
                info[0] = entry.getTitle();
                info[1] = entry.getDescription().getValue();
                info[2] = entry.getPublishedDate().toString();
                info[3] = entry.getLink();
                sizeFeed++;

                listInfo.add(info);
            }
        } catch (FeedException | IOException e) {
            e.printStackTrace();
        }

        assertEquals(sizeFeed, listInfo.size());

        writer.build();
        writer.writeToFile();

        try (BufferedReader reader = new BufferedReader(new FileReader(new File(file)))) {
            for (String[] info : listInfo) {
                /*
                делаю replaceAll по "\n" и " ", так как при чтении из *.xml файла считываются пробелы отступов
                и переносы строк
                 */
                assertEquals(
                    info[0].replaceAll(" ", "").replaceAll("\n", ""),
                    reader.readLine().replaceAll(" ", "")

                );
                assertEquals(
                    info[1].replaceAll(" ", "").replaceAll("\n", ""),
                    reader.readLine().replaceAll(" ", "")
                );
                assertEquals(
                    info[2].replaceAll(" ", "").replaceAll("\n", ""),
                    reader.readLine().replaceAll(" ", "")
                );
                assertEquals(
                    info[3].replaceAll(" ", "").replaceAll("\n", ""),
                    reader.readLine().replaceAll(" ", "")
                );
                reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
