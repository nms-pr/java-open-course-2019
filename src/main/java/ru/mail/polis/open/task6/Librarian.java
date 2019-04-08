package ru.mail.polis.open.task6;

import java.util.ArrayList;

class Librarian {

    static ArrayList<Book> giveBooks(int[] id, int[] section, Visitor visitor) {
        ArrayList<Book> result = null;
        try {
            result = Library.giveOutBooks(id, section, visitor);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

    static ArrayList<Book> giveBooks(String[] name, int[] section, Visitor visitor) {
        ArrayList<Book> result = null;
        try {
            result = Library.giveOutBooks(name, section, visitor);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

    static void receiveBook(Book book, Visitor visitor) {
        try {
            Library.receiveBook(book, visitor);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    static void remindVisitorsToReturnBooks() {
        for (Book book : Library.getTakenBooks()) {
            System.out.println("Dear"
                    + book.getRecipient().getFirstName()
                    + book.getRecipient().getSecondName()
                    + ", you must return the following book: "
                    + book.getName());
        }
    }

}
