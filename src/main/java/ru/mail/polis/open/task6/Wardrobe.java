package ru.mail.polis.open.task6;

import java.util.HashMap;
import java.util.Map;

public class Wardrobe {
    private final int wardrobeNumber;
    private Map<Integer, Shelf> shelfInWardrobe;
    private final int shelfQuantity;

    public Wardrobe(int wardrobeNumber) {
        shelfQuantity = 10;
        this.wardrobeNumber = wardrobeNumber;
        this.shelfInWardrobe = new HashMap<>();
        for (int i = 0; i < shelfQuantity; i++) {
            shelfInWardrobe.put(i, new Shelf(i));
        }

    }

    public int getWardrobeNumber() {
        return wardrobeNumber;
    }

    public Map<Integer, Shelf> getShelfInWardrobe() {
        return shelfInWardrobe;
    }
}
