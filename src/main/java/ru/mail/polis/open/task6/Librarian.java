package ru.mail.polis.open.task6;

import java.util.List;

public interface Librarian {

    public Book giveOutBook(String name, SomeVisitor visitor);

    public void takeBookIn(Book book, SomeVisitor visitor);
}
