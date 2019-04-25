package ru.mail.polis.open.task6;

public class SomeManager extends People implements Manager {

    SomeManager(String name, int age, char gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public void openLibrary() {
        Library.setLibStatus(true);
        Library.libInit();
    }

    public void addBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Книга не может быть null");
        }
        if (Library.placeIsBusy(book.getBookShelf(), book.getSpaceNumber())) {
            book = Library.findPlaceForBook(book);
            Library.getAllBooks().add(book);
        } else {
            Library.getAllShelfsInLib().get(book.getBookShelf()).getBooksFromShelf().add(book.getSpaceNumber(), book);
            Library.getAllBooks().add(book);
        }
    }

    public void closeLibrary() {
        Library.setLibStatus(false);
    }

    void greeting() {
        System.out.println("Здравствуйте, чем могу помочь?");
    }

    void goodbye() {
        System.out.println("До свидания!");
    }
}
