package ru.mail.polis.open.task6;

import java.util.ArrayList;
import java.util.HashMap;

public interface Librarian {
    Book takeBook(String name, Visitor visitor);

    void putBook(Book book);

    HashMap<String, ArrayList<Book>> getAvailableBooks();

    HashMap<Visitor, ArrayList<Book>> getHistory();

    void notifyVisitors();
}
