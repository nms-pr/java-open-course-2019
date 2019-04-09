package ru.mail.polis.open.task6;

/**
 * Есть менеджер
 * - приносит/убирает новые/старые книги
 * - открывает/закрывает библиотеку
 */
public interface Manager {

    boolean bringBook(Book book);
    boolean removeBook(Book book);

    void openInstitution();
    void closeInstitution();

}
