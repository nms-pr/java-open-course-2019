package ru.mail.polis.open.task6;

import java.util.ArrayList;

public class VisitorImpl extends Person implements Visitor {
    ArrayList<Book> takenBooks = new ArrayList<>();

    public VisitorImpl(String surname,
                       String name,
                       String patronymic,
                       String address,
                       String number,
                       int age,
                       char sex) {
        super(surname, name, patronymic, address, number, age, sex);
    }

    // Метод takeBook() берёт книгу в библиотеке по названию
    @Override
    public void takeBook(String name, Library library) {
        Book book = library.getLibrarian().takeBook(name, this);
        if (book != null) {
            takenBooks.add(book);
        }
    }

    // Метод returnBook() возвращает книгу в библиотеку
    @Override
    public void returnBook(Book book) {
        book.library.getLibrarian().putBook(book);
        takenBooks.remove(book);
    }

    @Override
    public void notifyAboutBook(Book book) {
        returnBook(book);
    }
}
