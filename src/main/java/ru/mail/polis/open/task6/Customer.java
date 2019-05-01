package ru.mail.polis.open.task6;

import java.util.ArrayList;
import java.util.List;

class Customer {

    private String name;
    private boolean hasABooks;
    private List<Book> bookList;

    public Customer(String name) {
        this.name = name;
        bookList = new ArrayList<>();
    }


    public String getName() {
        return name;
    }

    public boolean isHasABooks() {
        return hasABooks;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void takeBook(String Genre, String bookName, Librarian librarian, Customer customer) {
        bookList.add(librarian.giveOutBook(Genre, bookName, customer));
        hasABooks = true;
    }

    public Book returnBook(String bookName) {
        Book tmpBook = null;
        for (Book book : bookList) {
            if (book.getBookName().equals(bookName)) {
                tmpBook = book;
            }
        }
        bookList.remove(tmpBook);
        return tmpBook;
    }

    public void askForBooks(Librarian librarian) {
        librarian.showBooks();
    }

    public String hasDebts() {
        if (bookList.size() == 0) {
            hasABooks = false;
            return "Has Debts";
        }
        return "Has no Debts";
    }


}
