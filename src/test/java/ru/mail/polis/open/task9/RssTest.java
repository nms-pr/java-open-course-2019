package ru.mail.polis.open.task9;

import org.junit.jupiter.api.Test;

public class RssTest {
    @Test
    void test(){
        String[] input = new String[2];
        input[0]="file:file.xml";
        input[1]="output.xml";
        Rss.main(input);
    }
}
