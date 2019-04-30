package ru.mail.polis.open.task9;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RssTest {
    @Test
    void test() {
        String input = "http://feeds.bbci.co.uk/news/world/rss.xml";
        String output = "output.xml";
        String forCompare = "forCompare.xml";
        Rss.romeParse(input,output);
        assertTrue(MyComparator.compare(input,output,forCompare));
    }

}
