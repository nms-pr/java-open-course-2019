package ru.mail.polis.open.task6;

import java.util.List;
import java.util.Map;

public class Library {

    private Map<String, List<Book>> library;
    static boolean isOpened;

    public Library(Map<String, List<Book>> library) {
        this.library = library;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public void setOpened(boolean opened) {
        isOpened = opened;
    }

    public Map<String, List<Book>> getLibrary() {
        return library;
    }


}
