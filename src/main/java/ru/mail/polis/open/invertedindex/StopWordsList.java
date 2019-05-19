package ru.mail.polis.open.invertedindex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StopWordsList {

    private static List<String> stopWords = new ArrayList<String>(Arrays.asList("и", "а", "но", "я", "мы", "он",
            "к", "от", "по", "над", ""));


    public static List<String> getStopWords() {
        return stopWords;
    }
}
