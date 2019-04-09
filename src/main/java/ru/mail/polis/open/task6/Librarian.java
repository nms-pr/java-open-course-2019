package ru.mail.polis.open.task6;

import java.util.List;

public interface Librarian {
    BookGiven giveBook(int id);
    BookGiven giveBook(String name, String author);
    List<BookGiven> giveBooks(int ... idArray);
    void takeBooks(Iterable<BookGiven> books);
    String[] getAvailableBooks();
    String sayHello(Person guest);
    PersonCard getPersonCard();
    String sayGoodbye();
}
