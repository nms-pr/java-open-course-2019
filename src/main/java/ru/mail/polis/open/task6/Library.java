package ru.mail.polis.open.task6;

import java.util.HashMap;
import java.util.Map;

public final class Library {
    static ManagerImpl manage = new ManagerImpl(
        "Petrov",
        "Artem",
        "Igorevich",
        'M',
        18,
        50000
    );
    private static final int QUANTITY_WARDROBE = 45;
    private static Map<Integer, Wardrobe> libraryWardrobe = new HashMap<>();
    private static boolean isOpened = false;

    private Library() {}

    private static void initWardrobe() {
        for (int i = 0; i < QUANTITY_WARDROBE; i++) {
            libraryWardrobe.put(i, new Wardrobe(i));
        }
    }

    private static void initBasicBooks() {

    }

    public static boolean isOpened() {
        return isOpened;
    }

    public static void setOpened(boolean opened) {
        isOpened = opened;
    }

    public static Map<Integer, Wardrobe> getLibraryWardrobe() {
        return libraryWardrobe;
    }
}
