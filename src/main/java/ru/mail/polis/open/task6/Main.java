package ru.mail.polis.open.task6;

public class Main {

    public static void main(String[] args) {
        Library library = Library.getInstance();
        BookShelf bookShelf = BookShelf.getShelf();
        Manager manager = new Manager("Manager");
        Customer customer = new Customer("Customer");
        Librarian librarian = new Librarian("Librarian");
        manager.openLib();
        manager.bringBookToLibrary("Book1");
        manager.bringBookToLibrary("Book2");
        manager.bringBookToLibrary("Book3");
        librarian.addBookToShelf("Genre1", "Book1", 1);
        librarian.addBookToShelf("Genre1", "Book2", 2);
        librarian.addBookToShelf("Genre2", "Book3", 3);
        System.out.println(bookShelf.getBookShelf().size());

    }
}
