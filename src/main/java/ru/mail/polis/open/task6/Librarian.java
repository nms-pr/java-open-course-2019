package ru.mail.polis.open.task6;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class Librarian {

    private String name;
    private List<Customer> clientList = new ArrayList<>();
    private Library library = Library.getInstance();
    private BookShelf bookShelf = BookShelf.getShelf();
    private List<Book> acceptedBooks = new ArrayList<>();

    public Librarian(String name) {
        this.name = name;
    }

    public void acceptBook(String name, Customer customer) {
        acceptedBooks.add(customer.returnBook(name));
        library.getLibrary().addAll(acceptedBooks);
    }

    public Customer getClientByName(String name) {
        for (Customer customer : clientList) {
            if (customer.getName().equals(name)) {
                return customer;
            }
        }
        return null;
    }

    public boolean addBookToShelf(String genre, String bookName, int numberAtShelf) {
        if (library.getLibrary().contains(library.getBookByName(bookName))) {
            if (bookShelf.getBookShelf().containsKey(genre)) {
                library.getBookByName(bookName).setPlaceAtShelf(numberAtShelf);
                bookShelf.getBookShelf().get(genre).add(library.getBookByName(bookName));
                library.removeBook(bookName);
                BookShelf.booksCount++;
                return true;
            } else {
                library.getBookByName(bookName).setPlaceAtShelf(numberAtShelf);
                bookShelf.getBookShelf().put(genre, new ArrayList<>());
                bookShelf.getBookShelf().get(genre).add(library.getBookByName(bookName));
                library.removeBook(bookName);
                BookShelf.booksCount++;
                return true;
            }
        }
        return false;
    }

    public Book giveOutBook(String genre, String bookName, Customer customer) {
        Calendar returnTime;
        clientList.add(customer);
        Book tmpBook = null;
        for (Book book : bookShelf.getBookShelf().get(genre)) {
            if (book.getBookName().equals(bookName)) {
                book.setPlaceAtShelf(0);
                book.setReturned(false);
                book.setTakeBook(new GregorianCalendar());
                book.setReturnBook(new GregorianCalendar());
                returnTime = book.getReturnBook();
                returnTime.add(5, 30);
                book.setCustomerName(customer.getName());
                tmpBook = book;
            }
        }
        bookShelf.getBookShelf().get(genre).remove(library.getBookByName(bookName));
        return tmpBook;
    }

    public void showBooks() {
        bookShelf.showShelf();
    }
}
