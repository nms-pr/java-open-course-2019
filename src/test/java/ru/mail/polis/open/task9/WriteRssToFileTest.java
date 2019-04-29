package ru.mail.polis.open.task9;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WriteRssToFileTest {

    private static File fileTestXml;
    private static MyXml myXml;
    private static String fileOutputName;

    @BeforeAll
    static void createXml() throws MalformedURLException {
        String title = "Titleee";
        String description = "Description";
        String link = "REFS";
        String date = "Wed Apr 24 17:43:19 MSK 2019";

        fileTestXml = new File("Test.xml");
        try (FileOutputStream fos = new FileOutputStream(fileTestXml)) {
            myXml = new MyXml();
            myXml.setTitle(title);
            myXml.setDescription(description);
            myXml.setLink(link);
            myXml.setPubDate(date);
            MyXml.saveCustomChannelXml(fos, myXml);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileOutputName = "testFile";
    }

    @Test
    void testWrite() {
        WriteRssToFile writeRssToFile = new WriteRssToFile(new File("Test1.xml"), fileOutputName);
        StringBuilder sbOutputFile = new StringBuilder();
        try {
            FileReader fileReader = new FileReader(fileOutputName + ".txt");
            Scanner scanner = new Scanner(fileReader);
            while (scanner.hasNextLine()) {
                sbOutputFile.append(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        StringBuilder textForXml = new StringBuilder();
        textForXml.append(myXml.getTitle());
        textForXml.append(myXml.getDescription());
        textForXml.append(myXml.getLink());
        textForXml.append(myXml.getPubDate());

        String s = textForXml.toString();
        String s1 = sbOutputFile.toString();
        assertEquals(s, s1);
    }

    @Test
    void testLenta() throws MalformedURLException {
        WriteRssToFile writeRssToFile = new WriteRssToFile(new URL("https://lenta.ru/rss/news"), "lenta");
    }
}