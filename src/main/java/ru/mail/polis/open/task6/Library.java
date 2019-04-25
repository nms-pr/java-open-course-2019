package ru.mail.polis.open.task6;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private static final int SHELFS_NUMBER = 20;
    private static boolean status = false;
    public static SomeLibrarian librarian = new SomeLibrarian("Petr", 45,'M');
    public static SomeManager manager = new SomeManager("Лариса", 55, 'F');
    private static List<BookShelf> bookShelfList = new ArrayList<>(SHELFS_NUMBER);
    private static List<Book> books = new ArrayList<>();
    private static List<Book> busyBooks = new ArrayList<>();

    static List<Book> getAllBooks() {
        return books;
    }

    static List<BookShelf> getAllShelfsInLib() {
        return bookShelfList;
    }

    public static boolean getLibStatus() {
        return status;
    }

    static void setLibStatus(boolean state) {
        status = state;
    }

    static void libInit() {
        for (int i = 0; i < SHELFS_NUMBER; i++) {
            bookShelfList.add(new BookShelf(i + 1));
        }
        status = true;
        books.add(new Book("451 градус по Фаренгейту", "Брэдбери", "Фантастика", 1,1));
        books.add(new Book("Три товарища", "Ремарк", "Роман", 1, 2));
        books.add(new Book("1984","Оруэлл","Антиутопия", 1, 3));
        books.add(new Book("Почтамт","Буковски","Автобиография", 2,1));
        books.add(new Book("Ангелы ада","Стоктонн Томпсон","Гон за журналистикой",2,2));
        books.add(new Book("Солярис","Лем","Фантастический роман",2,3));
        books.add(new Book("Кристина","Кинг","Триллер",3,1));
        books.add(new Book("Вспоминая моих грустных шлюх","Маркес","Роман",3,2));
        books.add(new Book("Майн Кампф","Гитлер","Автобиография",3,3));
        for (Book book : books) {
            Library.getAllShelfsInLib().get(book.getBookShelf()).getBooksFromShelf().add(book.getSpaceNumber(), book);
        }
    }

    static boolean placeIsBusy(int shelf, int spaceNumber) {
        boolean check = false;
        shelf--;
        spaceNumber--;
        for (Book book : getAllBooks()) {
            if ((book.getBookShelf() == shelf) && (book.getSpaceNumber() == spaceNumber)) {
                check = true;
            }
        }
        return check;
    }

    static Book findPlaceForBook(Book book) {
        for (BookShelf shelf : Library.getAllShelfsInLib()) {
            for (int i = 0; i < shelf.getShelfQuantity(); i++) {
                if (shelf.getBooksFromShelf().get(i + 1) == null) {
                    shelf.getBooksFromShelf().add(i + 1, book);
                    book.setSpaceNumber(i + 2);
                    book.setBookShelf(shelf.getBookShelf());
                    return book;
                }
            }
        }
        throw new IllegalArgumentException("В библиотеке нет места");
    }

    static List<Book> getBusyBooksInLib() {
        return busyBooks;
    }
}
