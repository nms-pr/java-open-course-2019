package ru.mail.polis.open.task9;

import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.XmlReader;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class RssToFileTest {

    // Just checking how everything works
    @Test
    void transfer() {

        try (OutputStream outputStream = new FileOutputStream("test-result.txt");
             OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream)
        ) {
            RssToFile.transfer(new URL("https://news.rambler.ru/rss/tech/"), outputStreamWriter);
        } catch (IOException | FeedException e) {
            e.printStackTrace();
        }
    }

    @Test
    void transfer_FromStreamToStream_WorksCorrectly() {

        try (InputStream inputStream = new FileInputStream("test-rss.xml");
             XmlReader inputStreamReader = new XmlReader(inputStream, true, "UTF_8");
             OutputStream outputStream = new FileOutputStream("test-result.txt");
             OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8)
        ) {
            assertDoesNotThrow(() -> RssToFile.transfer(inputStreamReader, outputStreamWriter));
        } catch (IOException e) {
            fail(e);
        }

        try {
            assertEquals(
                Files.readAllLines(Paths.get("expected.txt"), StandardCharsets.UTF_8).toString(),
                Files.readAllLines(Paths.get("test-result.txt"), StandardCharsets.UTF_8).toString()
            );
        } catch (IOException e) {
            fail(e);
        }
    }
}