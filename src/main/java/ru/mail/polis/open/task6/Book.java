package ru.mail.polis.open.task6;

import java.time.LocalDateTime;
import java.util.Objects;

public class Book {

    private final int id;
    private final String name;
    private final String author;

    private String section;
    private int bookShelf;
    private int spaceNumber;

    private LocalDateTime takenDate;
    private LocalDateTime returnDate;
    private Visitor user;

    Book(String name, String author, String section,
         int bookShelf, int spaceNumber) {
        this.id = section.length()  * spaceNumber + 1;
        this.name = name;
        this.author = author;
        this.section = section;
        this.bookShelf = bookShelf;
        this.spaceNumber = spaceNumber;
        this.takenDate = null;
        this.returnDate = null;
    }

    int getId() {
        return id;
    }

    String getName() {
        return name;
    }

    String getAuthor() {
        return author;
    }

    String getSection() {
        return section;
    }

    public int getBookShelf() {
        return bookShelf - 1;
    }

    public int getSpaceNumber() {
        return spaceNumber - 1;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public LocalDateTime getTakenDate() {
        return takenDate;
    }

    public Visitor getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "Book: { Номер: " + id + ", Название: " + name
               + ", Автор: " + author + ", Секция: " + section;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Book)) {
            return false;
        }
        Book book = (Book) o;
        return getId() == book.getId()
                && getName().equals(book.getName())
                && getAuthor().equals(book.getAuthor())
                && getSection().equals(book.getSection())
                && getBookShelf() == book.getBookShelf()
                && getSpaceNumber() == book.getSpaceNumber()
                && Objects.equals(getReturnDate(), book.getReturnDate())
                && Objects.equals(getTakenDate(), book.getTakenDate())
                && Objects.equals(getUser(), book.getUser());
    }

    public void setBookShelf(int bookShelf) {
        this.bookShelf = bookShelf;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public void setSpaceNumber(int spaceNumber) {
        this.spaceNumber = spaceNumber;
    }

    public void setTakenDate(LocalDateTime takenDate) {
        this.takenDate = takenDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    public void setUser(Visitor user) {
        this.user = user;
    }
}
