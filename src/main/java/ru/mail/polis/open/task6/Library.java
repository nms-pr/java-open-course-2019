package ru.mail.polis.open.task6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Library {
    private static final byte NUMBER_OF_SHELFS = 20;
    private static boolean status = false; //true - открыта, false - закрыта
    private static byte counter = 0;
    public static LibrarianImpl librarian = new LibrarianImpl("Петр", "Петров", "Петрович", (byte) 39);
    public static ManagerImpl manager = new ManagerImpl("Иван", "Иванов", "Иванович", (byte) 15);
    private static List<Shelf> shelfsInLibrary = new ArrayList<>();
    private static List<CustomerImpl> customers = new ArrayList<>();
    private static List<Book> books = new ArrayList<>();
    public static List<Book> busyBooks = new ArrayList<>();

    static List<Book> getBooks() {
        return books;
    }

    static void setCounter() {
        counter = 0;
    }

    static boolean getStatus() {
        return status;
    }

    static void setStatus(boolean statusOfOpening) {
        status = statusOfOpening;
    }

    static List<Shelf> getShelfsInLibrary() {
        return shelfsInLibrary;
    }

    static List<Book> getBusyBooks() {
        return busyBooks;
    }

    public static List<CustomerImpl> getCustomers() {
        return customers;
    }

    static void work() {
        if (counter == 0) {
            for (int i = 0; i < NUMBER_OF_SHELFS; i++) {
                shelfsInLibrary.add(new Shelf(i + 1));
            }
            customers.add(new CustomerImpl("Илья", "Агапкин", "Федорович", (byte) 19));
            customers.add(new CustomerImpl("Дмитрией", "Ляпов", "Иванович", (byte) 88));
            customers.add(new CustomerImpl("Владимир", "Путин", "Владимирович", (byte) 66));
            customers.add(new CustomerImpl("Владимир", "Жириновский", "Вольфович", (byte) 72));
            customers.add(new CustomerImpl("Константин", "Оганесов", "Ильясович", (byte) 22));
            customers.add(new CustomerImpl("Руслан", "Викторов", "Валерьевич", (byte) 19));
            customers.add(new CustomerImpl("Анастасия", "Кудрявцева", "Дмитриевна", (byte) 33));
            customers.add(new CustomerImpl("Мария", "Потапова", "Михайловна", (byte) 24));
            customers.add(new CustomerImpl("Александра", "Фокина", "Валерьевна", (byte) 22));
            books.add(new Book(1, 1, "War and Peace", "Classic"));
            books.add(new Book(1, 2, "Crime and punishment", "Classic"));
            books.add(new Book(1, 3, "Герой нашего времени", "Классика"));
            books.add(new Book(2, 1, "Алхимик", "Мировые бестселлеры"));
            books.add(new Book(2, 2, "1984", "Мировые бестселлеры"));
            books.add(new Book(2, 3, "The Da Vinci Code", "Мировые бестселлеры"));
            books.add(new Book(3, 1, "Происхождение", "Детективы"));
            books.add(new Book(3, 2, "Один из нас лжёт", "Детективы"));
            books.add(new Book(3, 3, "Чужак", "Детективы"));
            for (Book book : books) {
                Library.getShelfsInLibrary().get(book.shelf).getBooksOnShelf().add(book.shelfPlace, book);
            }
            counter++;
        } else {
            for (Book book : busyBooks) {
                librarian.remindToReturn(book.getUserWhoTakes());
            }
        }
        status = true;
    }

    static boolean placeIsBusy(int shelf, int shelfPlace) {
        boolean check = false;
        shelf--;
        shelfPlace--;
        for (Book book : books) {
            if (book.shelf == shelf && book.shelfPlace == shelfPlace) {
                check = true;
            }
        }
        return check;
    }

    static boolean containCustomer(CustomerImpl customer) {
        boolean check = false;
        for (CustomerImpl customer1 : customers) {
            if (customer == customer1) {
                check = true;
            }
        }
        return check;
    }

    static void newCustomerCome(CustomerImpl customer) {
        boolean check = false;
        if (status == false) {
            throw new IllegalArgumentException("Библиотека сейчас закрыта.");
        }
        for (CustomerImpl customer1 : customers) {
            if (customer == customer1) {
                check = true;
            }
        }
        if (check == false) {
            customers.add(customer);
        }
    }


}