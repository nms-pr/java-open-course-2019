package ru.mail.polis.open.task6;

import java.util.ArrayList;
import java.util.List;

public final class Library {
    private static final byte NUMBER_OF_SHELFS = 20;
    private static boolean openOrClosed = false;
    private static byte counter = 0;
    public static Librarian librarian = new Librarian("Василий", "Пупкин", 69);
    public static Manager manager = new Manager("Геннадий", "Букин", 43);
    private static List<BookShelf> bookShelfList = new ArrayList<>();
    private static List<Visitor> visitorList = new ArrayList<>();
    private static List<Book> bookList = new ArrayList<>();
    private static List<Book> busyBooks = new ArrayList<>();

    static boolean getOpenOrClosed() {
        return openOrClosed;
    }

    static List<BookShelf> getBookShelfList() {
        return bookShelfList;
    }

    static List<Visitor> getVisitorList() {
        return visitorList;
    }

    static List<Book> getBookList() {
        return bookList;
    }

    static void setCounter() {
        counter = 0;
    }

    static List<Book> getBusyBooks() {
        return busyBooks;
    }

    static void setOpenOrClosed(boolean openOrClosed) {
        Library.openOrClosed = openOrClosed;
    }

    static void baseOfVisitorsAndBooks() {
        if (counter == 0) {
            for (int i = 0; i < NUMBER_OF_SHELFS; i++) {
                bookShelfList.add(new BookShelf(i + 1));
            }
            visitorList.add(new Visitor("Антон", "Сергеев", 22));
            visitorList.add(new Visitor("Евгений", "Сафонов", 33));
            visitorList.add(new Visitor("Павел", "Найденов", 19));
            visitorList.add(new Visitor("Захар", "Собачкин", 47));
            visitorList.add(new Visitor("Игнат", "Кошечкин", 27));
            bookList.add(new Book(17, 4,
                "Преступление и наказание", "Роман"));
            bookList.add(new Book(3, 10,
                "Мастер и Маргарита", "Роман"));
            bookList.add(new Book(1, 8,
                "Красная таблетка", "Психология"));
            for (Book book : bookList) {
                Library.getBookShelfList().get(book.numberBookShelf).getBooksOnShelf().add(book.numberBookShelf, book);
            }
            counter++;
        } else {
            for (Book book : busyBooks) {
                librarian.remindToReturn(book.getWhoseBook());
            }
        }
        openOrClosed = true;
    }

    static boolean placeIsBusy(int bookShelf) {
        boolean check = false;
        bookShelf--;
        if (!openOrClosed) {
            throw new IllegalArgumentException("Библиотека закрыта!");
        }
        for (Book book : bookList) {
            if (book.numberBookShelf == bookShelf) {
                check = true;
            }
        }
        return check;
    }

    static boolean visitor(Visitor visitor) {
        boolean check = false;
        for (Visitor customer : visitorList) {
            if (customer == visitor) {
                check = true;
                break;
            }
        }
        if (!check) {
            visitorList.add(visitor);
        }
        return check;
    }
}
