package ru.mail.polis.open.task6;

import java.util.ArrayList;
import java.util.List;

public class SimpleVisitor implements Visitor {
    Librarian librarian;
    String visitorName;
    List<Book> books;

    SimpleVisitor(String visitorName, Librarian librarian) {
        this.librarian = librarian;
        this.visitorName = visitorName;
        books = new ArrayList<Book>();
    }

    @Override
    public void giveBooksBack() throws LibraryException {
        for (Book book : books) {
            librarian.takeBack(book);
        }
        books.clear();
    }

    @Override
    public void takeBookOut(String title) throws LibraryException {
        books.add(librarian.giveOut(title, this));
    }

    @Override
    public String getCredentials() {
        return visitorName;
    }

    @Override
    public void notifyAboutBooks() {
        try {
            giveBooksBack();
        } catch (LibraryException e) {
            System.out.println(e.getMessage());
        }
    }

}
