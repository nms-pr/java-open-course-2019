package ru.mail.polis.open.task6;

import java.util.ArrayList;
import java.util.Arrays;

class UtilTest {
    static Book winnie = new Book(1,
                              "Winnie the Pooh",
                              new ArrayList<>(Arrays.asList("Fairytale", "Children", "Alan Milne")));
    static Book narnia1 = new Book(2,
                               "Chronicles of Narnia",
                               new ArrayList<>(Arrays.asList("Fairytale",
                                       "Family", "Children", "Mythology", "Part 1")));
    static Book myths = new Book(3,
                             "Myths of Ancient Greece",
                             new ArrayList<>(Arrays.asList("Mythology", "Education")));
    static Book physics = new Book(4,
                               "Quantum Physics for stupid people",
                               new ArrayList<>(Arrays.asList("Science", "Education")));
    static Book narnia2 = new Book(5,
                               "Chronicles of Narnia",
                               new ArrayList<>(Arrays.asList("Fairytale",
                                       "Family", "Children", "Mythology", "Part 2")));
    static Book winnie2 = new Book(1,
                               "Winnie the Pooh",
                               new ArrayList<>(Arrays.asList("Fairytale", "Children", "Alan Milne")));
}
