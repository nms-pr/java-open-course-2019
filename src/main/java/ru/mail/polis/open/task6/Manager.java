package ru.mail.polis.open.task6;

import java.util.List;

public interface Manager {

    void addNewBooks(BookCard... bookCards);

    void openLibrary();

    void closeLibrary();

    void deleteBooks(int... idArray);

    List<BookCard> getDepository();

}
