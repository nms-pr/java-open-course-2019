package ru.mail.polis.open.task6;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Library library = new Library(new HashMap<>());
        library.getLibrary().put("BookType1", new ArrayList<>());
        library.getLibrary().get("BookType1").add(new Book(1, "Book1", new GregorianCalendar()));
        Calendar calendar = new GregorianCalendar();
        System.out.println(calendar.getTime());
        calendar.add(5, 30);
        System.out.println(calendar.getTime());
        System.out.println();
        System.out.println(library.getLibrary().get("BookType1"));
    }
}
