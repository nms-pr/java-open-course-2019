package ru.mail.polis.open.task9;

import com.rometools.rome.feed.synd.SyndFeed;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;



public class Task9 {

    @Test
    void rssDataProviderTest() {

        try {
            SyndFeed result = new RssDataProvider().getNewsFeed("file:testInput.xml");
            new RssDataProvider().writeNewsFeedToFile("testOutput.txt", result);

            if (!compareFiles("testOutput.txt", "expectedOutput.txt")) {
                Assertions.fail();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    boolean compareFiles(String firstFileName, String secondFileName) throws IOException {

        BufferedReader firstFile = new BufferedReader(new FileReader(new File(firstFileName)));
        BufferedReader secondFile = new BufferedReader(new FileReader(new File(secondFileName)));

        String firstFileLine = firstFile.readLine();
        String secondFileLine = secondFile.readLine();

        while (firstFileLine != null && secondFileLine != null) {

            if (!firstFileLine
                    .replaceAll("\n", "")
                    .replaceAll("\t", "")
                    .replaceAll(" ", "")
                    .equals(secondFileLine
                            .replaceAll("\n", "")
                            .replaceAll("\t", "")
                            .replaceAll(" ", ""))) {
                return false;
            }

            firstFileLine = firstFile.readLine();
            secondFileLine = secondFile.readLine();
        }

        return true;
    }

}
