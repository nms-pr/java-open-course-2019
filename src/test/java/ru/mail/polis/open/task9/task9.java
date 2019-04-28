package ru.mail.polis.open.task9;

import com.rometools.rome.feed.synd.SyndFeed;
import org.junit.jupiter.api.Test;
import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class task9 {

    @Test
    void RssDataProviderTest() {

        try {
            SyndFeed result = new RssDataProvider().getNewsFeed("file:testInput.xml");
            new RssDataProvider().writeNewsFeedToFile("testOutput.txt", result);

            if (!compareFiles("testOutput.txt", "expectedOutput.txt")) {
                fail();
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

            if (!firstFileLine.equals(secondFileLine)) {
                return false;
            }

            firstFileLine = firstFile.readLine();
            secondFileLine = secondFile.readLine();
        }

        return true;
    }

}
