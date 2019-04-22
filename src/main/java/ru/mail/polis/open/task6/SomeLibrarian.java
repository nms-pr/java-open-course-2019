package ru.mail.polis.open.task6;

import java.time.LocalDateTime;

public class SomeLibrarian extends People implements Librarian {

    public SomeLibrarian(String name, int age, char gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public void greeting() {
        System.out.println("Здравствуйте! Чем могу помочь?");
    }

    public void goodbye() {
        System.out.println("До свидания!");
    }

    public Book giveOutBook(String name, SomeVisitor visitor) {
        if (name == null) {
            throw new IllegalArgumentException("Название книги не может быть null");
        }
        Book book = findBook(name);
        if (book!= null) {
            book.setUser(visitor);
            book.setTakenDate(LocalDateTime.now());
            return book;
        } else {
            return null;
        }
    }

    public void takeBookIn(Book book, SomeVisitor visitor){
        if (book == null) {
            throw new IllegalArgumentException("Название не может быть null");
        }
        if(Library.placeIsBusy(book.getBookShelf(), book.getSpaceNumber())) {
            Book internalBook = Library.findPlaceForBook(book);
            internalBook.setUser(null);
            internalBook.setTakenDate(null);
            internalBook.setReturnDate(LocalDateTime.now());
        } else {
            book.setUser(null);
            book.setTakenDate(null);
            book.setReturnDate(LocalDateTime.now());
            Library.getAllShelfsInLib().get(book.getBookShelf()).getBooksFromShelf().add(book);
        }
    }

    public Book takeBookForProlong(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Нет книги");
        }
        book.setTakenDate(LocalDateTime.now());
        return book;
    }

    Book findBook(String name) {
        Book book1 = null;
        if (name == null) {
            throw new IllegalArgumentException("Название книги не может быть null");
        }
        for (Book book : Library.getAllBooks()) {
            if (book.getName().equals(name)) {
                book1 = book;
                break;
            }
        }
        if (book1 == null) {
            return null;
        } else {
            return book1;
        }
    }
}
