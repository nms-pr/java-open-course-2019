package ru.mail.polis.open.task6;

import java.util.ArrayList;

public class LibraryClient extends Person {

    private ArrayList<Manager.Book> booksTaken = new ArrayList<>();
    private static ArrayList<String> clientDatabase = new ArrayList<>();

    private Librarian currentWorkingLibrarian = null;

    public LibraryClient(String name) {
        super(name);
    }

    @Override
    protected ArrayList<String> getDatabase() {
        return clientDatabase;
    }

    @Override
    protected ArrayList<Manager.Book> getCollection() {
        return booksTaken;
    }

    public void askLibrarian() {
        Manager.checkIfLibraryIsClosed();
        if (currentWorkingLibrarian != null) {
            System.out.println("You are already talking to a Librarian");
            return;
        }
        currentWorkingLibrarian = Librarian.getAFreeLibrarian();
    }

    private void check() {
        Manager.checkIfLibraryIsClosed();
        if (currentWorkingLibrarian == null) {
            System.out.println("Ask a Librarian before doing anything else in Library!");
        }
    }

    public void takeBook(Manager.Book book) {
        check();
        if (booksTaken.contains(book)) {
            System.out.println("You've already got this book");
            return;
        }
        addOneBookToCollection(currentWorkingLibrarian.removeOneBookFromCollection(book));
        currentWorkingLibrarian.connectBookToAClient(this, book);
    }

    public void takeBook(int id, String name, ArrayList<String> partitions) {
        Manager.Book book = currentWorkingLibrarian.findBookByFullDescription(id, name, partitions);
        if (book != null) {
            takeBook(book);
        }
    }

    public void signInLibrary() {
        check();
        currentWorkingLibrarian.signClient(this);
    }

    public void returnBook(Manager.Book book) {
        check();
        currentWorkingLibrarian.addOneBookToCollection(removeOneBookFromCollection(book));
        currentWorkingLibrarian.markBookAsUnused(book);
    }

    public void askForExtension(Manager.Book book) {
        check();
        if (!booksTaken.contains(book)) {
            throw new IllegalArgumentException("You can't extend a book you don't own");
        }
        currentWorkingLibrarian.extendBook(book);
    }

    public void sayGoodbie() {
        check();
        currentWorkingLibrarian.becomeFree();
        currentWorkingLibrarian = null;
    }

    public <T> ArrayList<Manager.Book> askForBooksByParameter(T parameterValue,
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
