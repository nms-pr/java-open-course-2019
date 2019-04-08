package ru.mail.polis.open.task6;

/**
 * Есть менеджер
 * - приносит/убирает новые/старые книги
 * - открывает/закрывает библиотеку
 */
public interface Manager {

    Book bringBook();
    void removeBook(Book book);

    boolean openInstitution();
    boolean closeInstitution();

}
