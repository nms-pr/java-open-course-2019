package ru.mail.polis.open.task6;

import java.util.Calendar;

public class Book {
    int id;
    String name;
    int shelfPlace; // место на полке
    int section; // секция
    Calendar issueTime; // дата выдачи книги
    Calendar returnTime; // дата возврата книги
    Visitor whoTookBook; // Кто взял книгу
    Library library; // Из какой библиотеки книга

    public Book(int id, String name, int shelfPlace, int section, Library library) {
        this.id = id;
        this.name = name;
        this.shelfPlace = shelfPlace;
        this.section = section;
        this.library = library;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }

        Book book = (Book) obj;
        if (book == this) {
            return true;
        } else {
            return book.id == id && book.name.equals(name);
        }
    }

    @Override
    public int hashCode() {
        return 31 * id + name.hashCode();
    }
}
