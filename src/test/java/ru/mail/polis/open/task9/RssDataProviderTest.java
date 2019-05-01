package ru.mail.polis.open.task9;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class RssDataProviderTest {

    @Test
    void rssDataProviderTest() {

        List<String[]> expectedData = createExpectedData("testInput.xml");

        try {
            new RssDataProvider().writeNewsFeedToFile(
                    "testOutput.txt", new RssDataProvider().getNewsFeed("file:testInput.xml")
            );
        } catch (IOException | FeedException e) {
            e.printStackTrace();
        }

        List<String[]> outputFileData = readOutputFile("testOutput.txt");

        assertEquals(outputFileData.size(), expectedData.size());

        for (int i = 0; i < outputFileData.size(); i++) {
            assertEquals(
                    outputFileData.get(i)[0].replaceAll(" ", "").replaceAll("\n", ""),
                    expectedData.get(i)[0].replaceAll(" ", "").replaceAll("\n", ""));

            assertEquals(
                    outputFileData.get(i)[1].replaceAll(" ", "").replaceAll("\n", ""),
                    expectedData.get(i)[1].replaceAll(" ", "").replaceAll("\n", ""));

            assertEquals(outputFileData.get(i)[2].replaceAll(" ", "").replaceAll("\n", ""),
                    expectedData.get(i)[2].replaceAll(" ", "").replaceAll("\n", ""));

            assertEquals(outputFileData.get(i)[3].replaceAll(" ", "").replaceAll("\n", ""),
                    expectedData.get(i)[3].replaceAll(" ", "").replaceAll("\n", ""));
        }

    }

    private List<String[]> createExpectedData(String fileName) {
        List<String[]> expectedData = new ArrayList<>();

        try {
            SyndFeed feed = new SyndFeedInput().build(new XmlReader(new File(fileName)));
            for (SyndEntry entry : feed.getEntries()) {
                String[] entryData = new String[4];
                entryData[0] = "Title:" + entry.getTitle();
                entryData[1] = "Description:" + entry.getDescription().getValue();
                entryData[2] = "Link:" + entry.getLink();
                entryData[3] = "Publishing date:" + entry.getPublishedDate().toString();
                expectedData.add(entryData);
            }
        } catch (IOException | FeedException e) {
            e.printStackTrace();
        }

        return expectedData;
    }

    private List<String[]> readOutputFile(String fileName) {
        ArrayList<String[]> file = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)))) {

            String nextLine = reader.readLine();
            while (nextLine != null) {

                StringBuilder title = new StringBuilder();
                StringBuilder description = new StringBuilder();
                StringBuilder link = new StringBuilder();
                StringBuilder publishingDate = new StringBuilder();
                String[] section = new String[4];

                while (nextLine != null && !nextLine.split(":")[0].equals("Description")) {
                    title.append(nextLine);
                    nextLine = reader.readLine();
                }
                section[0] = title.toString();

                while (nextLine != null && !nextLine.split(":")[0].equals("Link")) {
                    description.append(nextLine);
                    nextLine = reader.readLine();
                }
                section[1] = description.toString();

                while (nextLine != null && !nextLine.split(":")[0].equals("Publishing date")) {
                    link.append(nextLine);
                    nextLine = reader.readLine();
                }
                section[2] = link.toString();

                while (nextLine != null && !nextLine.equals("-------------------")) {
                    publishingDate.append(nextLine);
                    nextLine = reader.readLine();
                }
                section[3] = publishingDate.toString();

                file.add(section);
                nextLine = reader.readLine();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }

}