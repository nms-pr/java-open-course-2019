package ru.mail.polis.open.task6;

import java.util.ArrayList;

import static ru.mail.polis.open.task6.ManagingPerson.store;

public class LibraryClient extends Person {

    private ArrayList<Book> booksTaken = new ArrayList<>();
    private static ArrayList<String> clientDatabase = new ArrayList<>();
    private static int clientOperationsDone = 0;
    private static int busyClients = 0;
    private static int extendCount = 0;
    private static int putsAmount = 0;
    private static int removesAmount = 0;

    private Librarian currentWorkingLibrarian = null;

    public LibraryClient(String name) {
        super(name, clientDatabase);
    }

    public void askLibrarian() {
        Manager.checkIfLibraryIsClosed();
        if (currentWorkingLibrarian != null) {
            System.out.println("You are already talking to a Librarian");
            return;
        }
        busyClients++;
        currentWorkingLibrarian = Librarian.getAFreeLibrarian();
    }

    protected static int getPutsAmount() {
        return putsAmount;
    }

    protected static int getRemovesAmount() {
        return removesAmount;
    }

    protected static int getExtendCount() {
        return extendCount;
    }

    protected static int getBusyClients() {
        return busyClients;
    }

    protected static int getClientOperationsDone() {
        return clientOperationsDone;
    }

    private void check() {
        Manager.checkIfLibraryIsClosed();
        if (currentWorkingLibrarian == null) {
            throw new IllegalCallerException("Ask a Librarian before doing anything else in Library!");
        }
    }

    public void takeBook(Book book) {
        check();
        if (booksTaken.contains(book)) {
            System.out.println("You've already got this book");
            return;
        }
        removesAmount++;
        clientOperationsDone++;
        Book bookTaken = currentWorkingLibrarian.removeOneBookFromCollection(book, store);
        clientOperationsDone++;
        addOneBookToCollection(bookTaken, booksTaken);
        currentWorkingLibrarian.newBookInfo(book, this);
    }

    public void takeBook(int id, String name, ArrayList<String> partitions) {
        Book book = currentWorkingLibrarian.findBookByFullDescription(id, name, partitions);
        if (book != null) {
            takeBook(book);
        }
    }

    public void returnBook(Book book) {
        check();
        putsAmount++;
        clientOperationsDone++;
        Book retBook = removeOneBookFromCollection(book, booksTaken);
        clientOperationsDone++;
        currentWorkingLibrarian.addOneBookToCollection(retBook, store);
        currentWorkingLibrarian.getBookBackFromClient(book, this);
    }

    public void askForExtension(Book book) {
        check();
        extendCount++;
        if (!booksTaken.contains(book)) {
            throw new IllegalArgumentException("You can't extend a book you don't own");
        }
        currentWorkingLibrarian.extendBook(book, this);
    }

    public void sayGoodbie() {
        check();
        busyClients--;
        currentWorkingLibrarian.becomeFree();
        currentWorkingLibrarian = null;
    }

    public <T> ArrayList<Book> askForBooksByParameter(T parameterValue,
                                                                      BookSearchParameter parameterType) {
        return currentWorkingLibrarian.findAllBooksByParameter(parameterValue, parameterType);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LibraryClient)) {
            return false;
        }
        LibraryClient other = (LibraryClient) obj;
        return other.getName().equals(getName());
    }
}
