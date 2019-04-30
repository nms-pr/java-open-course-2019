package ru.mail.polis.open.task6;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Librarian extends ManagingPerson {

    private static HashMap<LibraryClient, ArrayList<Book>> booksByClients = new HashMap<>();
    private static HashMap<String, Person> librarianDatabase = new HashMap<>();

    private boolean isBusy = false;

    public Librarian(String name) {
        super(name);
    }

    @Override
    protected HashMap<String, Person> getDatabase() {
        return librarianDatabase;
    }

    protected Book findBookByFullDescription(int id, String name, ArrayList<String> partitions) {
        Book book = new Book(id, name, partitions);
        if (store.containsKey(book)) {
            return book;
        }
        System.out.println("No such book found! Sorry!");
        return null;
    }

    private <T> boolean condition(T parameterValue, BookSearchParameter parameterType, Book book) {
        switch (parameterType) {
            case BY_NAME: return book.name.equals(parameterValue);
            case BY_PARTITION: return book.partitions.contains(parameterValue);
            case BY_PARTITIONS: return book.partitions.containsAll((Collection<String>) parameterValue);
            default: return false;
        }
    }

    protected void signClient(LibraryClient client) {
        booksByClients.put(client, new ArrayList<>());
    }

    protected  <T> ArrayList<Book> findAllBooksByParameter(
            T parameterValue,
            BookSearchParameter parameterType
    ) {
        if ((parameterType.equals(BookSearchParameter.BY_NAME)
                || parameterType.equals(BookSearchParameter.BY_PARTITION)) && !(parameterValue instanceof String)
                || parameterType.equals(BookSearchParameter.BY_PARTITIONS) && !(parameterValue instanceof ArrayList)) {
            throw  new IllegalArgumentException("The type of parameter is specified incorrectly");
        }
        ArrayList<Book> res = new ArrayList<>();
        for (Book book: store.keySet()) {
            if (condition(parameterValue, parameterType, book) && !res.contains(book)) {
                res.add(book);
            }
        }
        return res;
    }

    protected static Librarian getAFreeLibrarian() {
        for (String librarianName: librarianDatabase.keySet()) {
            Librarian librarian = (Librarian)librarianDatabase.get(librarianName);
            if (!librarian.isBusy) {
                librarian.isBusy = true;
                return librarian;
            }
        }
        System.out.println("All librarians are busy right now!");
        return null;
    }

    protected void connectBookToAClient(LibraryClient client, Book book) {
        if (!booksByClients.containsKey(client)) {
            System.out.println("You must sign up in the Library first!");
            return;
        }
        booksByClients.get(client).add(book);
        book.setCurrentUser(client);
        book.setTakeAndReturnTime();
    }

    public String remindClientAboutBookReturnTime(LibraryClient client, Book book) {
        if (!booksByClients.get(client).contains(book)) {
            throw new IllegalArgumentException("This book is not owned by this client currently");
        }
        return client.getName()
                + " must return this book: "
                + LINE_SEPARATOR
                + book.toString()
                + LINE_SEPARATOR
                + "until " + book.getReturnTime().getTime();
    }

    public HashMap<LibraryClient, ArrayList<Book>> getBooksByClients() {
        return booksByClients;
    }

    protected void extendBook(Book book) {
        book.extend();
    }

    protected void markBookAsUnused(Book book) {
        book.markAsUnused();
    }

    protected void becomeFree() {
        isBusy = false;
    }
}
