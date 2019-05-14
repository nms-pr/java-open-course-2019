package ru.mail.polis.open.task9;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RssTest {

    @Test
    void testFileNotFound() {
        assertThrows(FileNotFoundException.class, () -> RssFileSaver.writeRssToFile(
                "https://mi3ch.livejournal.com/data/rss",
                        "aFolder\\aFile.txt"));
    }

    @Test
    void testNonExistentUrl() {
        assertThrows(UnknownHostException.class, () -> RssFileSaver.writeRssToFile(
                "https://un.re.al.UR.l.comm/data/rss",
                        "outtext\\out.txt"));
    }

    @Test
    void testMalformedUrl() {
        assertThrows(MalformedURLException.class, () -> RssFileSaver.writeRssToFile(
                "This_Just_Can't_Be_An_Inet_Adress",
                        "outtext\\out.txt"));
    }

    private void assertFileContent(String expectedContent, String fileName) throws IOException {
        List<String> actualContentList = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        StringBuilder actualContent = new StringBuilder();
        for (String line: actualContentList) {
            actualContent.append(line).append(RssFileSaver.LINE_SEPARATOR);
        }
        assertEquals(expectedContent, actualContent.toString());
    }

}
