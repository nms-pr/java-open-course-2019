package ru.mail.polis.open.task6;

/**
 * Есть библиотекарь (добавляет и выдаёт книги)
 */
public interface Librarian {

    boolean addBook(Book book);
    Book issueBook(Book book);

}
