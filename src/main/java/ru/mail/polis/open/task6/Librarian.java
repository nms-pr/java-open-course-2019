package ru.mail.polis.open.task6;

import java.util.ArrayList;

public class Librarian extends Person {
    Librarian(String fullName, String sex, int age) {
        super(fullName, sex, age);
    }

    public void giveBook(Library library, Customer customer, Book book) {
        customer.getBook(library, book);
    }

    public void returnBook(Library library, Customer customer, Book book) {
        customer.returnBook(library, book);
    }

    public ArrayList<Book> getListOfBooksOnHands(Library library) {
        return library.getListOfBooksOnHands();
    }

    public ArrayList<Book> getListOfBooksOnHandsCurrentCustomer(Customer customer) {
        return customer.whatOnHands();
    }

    public void remindReturnBook(Customer customer, Book book) {
        System.out.println(customer.getFullName()
                + ", не забудь вернуть книгу "
                + book.getTitle() + " "
                + book.getDateOfReturn());
    }
}
