package ru.mail.polis.open.task9;

import com.rometools.rome.feed.synd.SyndFeed;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;



public class Task9 {

    private static final String EXPECTED_DATA = "Title: Star City"
            + System.lineSeparator()
            + "Description: How do Americans get ready to work with Russians aboard the"
            + System.lineSeparator()
            + "                International Space Station? They take a crash course in culture, language"
            + System.lineSeparator()
            + "                and protocol at Russia's Star City."
            + System.lineSeparator()
            + "Link: http://liftoff.msfc.nasa.gov/news/2003/news-starcity.asp"
            + System.lineSeparator()
            + "Publishing date: Tue Jun 03 17:39:21 KRAST 2003"
            + System.lineSeparator()
            + "-------------------"
            + System.lineSeparator()
            + "Title: Space Exploration"
            + System.lineSeparator()
            + "Description: Sky watchers in Europe, Asia, and parts of Alaska and Canada"
            + System.lineSeparator()
            + "                will experience a partial eclipse of the Sun on Saturday, May 31st."
            + System.lineSeparator()
            + "Link: http://liftoff.msfc.nasa.gov/"
            + System.lineSeparator()
            + "Publishing date: Fri May 30 19:06:42 KRAST 2003"
            + System.lineSeparator()
            + "-------------------"
            + System.lineSeparator()
            + "Title: The Engine That Does More"
            + System.lineSeparator()
            + "Description: Before man travels to Mars, NASA hopes to design new engines"
            + System.lineSeparator()
            + "                that will let us fly through the Solar System more quickly. The proposed"
            + System.lineSeparator()
            + "                VASIMR engine would do that."
            + System.lineSeparator()
            + "Link: http://liftoff.msfc.nasa.gov/news/2003/news-VASIMR.asp"
            + System.lineSeparator()
            + "Publishing date: Tue May 27 16:37:32 KRAST 2003"
            + System.lineSeparator()
            + "-------------------"
            + System.lineSeparator()
            + "Title: Astronauts' Dirty Laundry"
            + System.lineSeparator()
            + "Description: Compared to earlier spacecraft, the International Space"
            + System.lineSeparator()
            + "                Station has many luxuries, but laundry facilities are not one of them."
            + System.lineSeparator()
            + "                Instead, astronauts have other options."
            + System.lineSeparator()
            + "Link: http://liftoff.msfc.nasa.gov/news/2003/news-laundry.asp"
            + System.lineSeparator()
            + "Publishing date: Tue May 20 16:56:02 KRAST 2003"
            + System.lineSeparator()
            + "-------------------"
            + System.lineSeparator();

    @Test
    void rssDataProviderTest() {

        try {
            SyndFeed result = new RssDataProvider().getNewsFeed("file:testInput.xml");
            new RssDataProvider().writeNewsFeedToFile("testOutput.txt", result);

            if (!compareOutputFileWithExpectedData("testOutput.txt")) {
                Assertions.fail();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private boolean compareOutputFileWithExpectedData(String fileName) throws IOException {
        BufferedReader fileReader = new BufferedReader(new FileReader(new File(fileName)));

        String outputFileString = fileReader.readLine();

        for (String expectedString : EXPECTED_DATA.split(System.lineSeparator())) {

            if (outputFileString == null
                    || !expectedString.equals(outputFileString.replaceAll(System.lineSeparator(), ""))) {
                return false;
            }

            outputFileString = fileReader.readLine();
        }

        return fileReader.readLine() == null;
    }

}
