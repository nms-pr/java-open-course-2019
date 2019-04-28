package ru.mail.polis.open.task6;

import java.util.Arrays;

public class Jopa {
    private static Manager pete = new Manager("pete");
    private static Manager bob = new Manager("bob");
    private static Librarian pete2 = new Librarian("pete");
    private static Librarian pete4 = new Librarian("pete");
    private static LibraryClient pete3 = new LibraryClient("pete");

    public static void main(String[] args) {
        System.out.println(pete.getName() + " " + pete2.getName() + " " + pete3.getName());
        System.out.println(Arrays.toString(pete.getDatabase().toArray()));
        for (String name: pete2.getDatabase().keySet()) {
            System.out.println(pete2.getDatabase().get(name).getName());
        }
    }
}
