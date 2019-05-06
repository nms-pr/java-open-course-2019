package ru.mail.polis.open.task6;

import java.util.ArrayList;
import java.util.Calendar;
import java.lang.IllegalArgumentException;

public class Library {
    private int maxCountOfPlaces;
    private ArrayList<Book> allBooks = new ArrayList<>();
    private boolean openClosed;
    private Manager currentManager;
    private Librarian currentLibrarian;
    private Boolean[] shelfPlacesStatus = new Boolean[maxCountOfPlaces];
    private ArrayList<Customer> customerList = new ArrayList<>();

    Library(Manager manager, Librarian librarian, int maxCountOfPlaces) {
        this.currentManager = manager;
        this.currentLibrarian = librarian;
        this.maxCountOfPlaces = maxCountOfPlaces;
    }

    public void changeManager(Manager manager) {
        this.currentManager = manager;
    }

    public Manager currentManager() {
        return currentManager;
    }

    public void changeLibrarian(Librarian librarian) {
        this.currentLibrarian = librarian;
    }

    public Librarian currentLibrarian() {
        return currentLibrarian;
    }

    public boolean isOpen() {
        return openClosed;
    }

    public void closeLibrary() {
        openClosed = false;
    }

    public void openLibrary() {
        openClosed = true;
    }

    public void addBook(int id, String title, int section, int shelfPlace) {
        if (shelfPlace < maxCountOfPlaces && !shelfPlacesStatus[shelfPlace]) {
            allBooks.add(new Book(id, title, section, shelfPlace));
            shelfPlacesStatus[shelfPlace] = true;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void removeBook(Book currentBook) {
        if (currentBook.isAvailable()) {
            shelfPlacesStatus[currentBook.getShelfPlace()] = false;
            allBooks.remove(currentBook);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void giveBook(Book book, Customer customer) {
        if (book.isAvailable()) {
            book.setDateOfGiving(Calendar.getInstance());
            Calendar date = Calendar.getInstance();
            date.add(Calendar.MONTH, 1);
            book.setDateOfReturn(date);
            book.setAvailabilityFalse();
            book.setCustomerInfo(customer);
            if (!customerList.contains(customer)) {
                customerList.add(customer);
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void returnBook(Library library, Book book, Customer customer) {
        book.setDateOfGiving(null);
        book.setDateOfReturn(null);
        book.setAvailabilityTrue();
        book.setCustomerInfo(null);
        if (customer.whatInStock(library).isEmpty()) {
            customerList.remove(customer);
        }
    }

    public ArrayList<Book> getAllBooks() {
        return allBooks;
    }

    public ArrayList<Book> getListOfBooksOnHands() {
        ArrayList<Book> listOfBooksOnHands = new ArrayList<>();
        for (Book book : getAllBooks()) {
            if (!book.isAvailable()) {
                listOfBooksOnHands.add(book);
            }
        }
        return listOfBooksOnHands;
    }
}
