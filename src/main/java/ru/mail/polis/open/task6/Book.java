package ru.mail.polis.open.task6;

import java.time.LocalDateTime;
import java.util.Objects;

public class Book {
    private final long ID;
    private final int pages;
    private final String name;
    private final String author;
    private final String section;

    private int bookcaseNumber;
    private int shelfNumber;
    private int shelfSpace;
    private LocalDateTime timeOfReceiptTheBook;
    private LocalDateTime timeOfReturnTheBook;
    private VisitorImpl user;

    public Book(
        String name,
        String author,
        String section,
        int pages,
        int bookcaseNumber,
        int shelfNumber,
        int shelfSpace
    ) {
        this.ID = pages
            * name.length()
            * author.length()
            * section.length()
            * 10 + 5;
        this.bookcaseNumber = bookcaseNumber;
        this.shelfNumber = shelfNumber;
        this.shelfSpace = shelfSpace;
        this.pages = pages;
        this.name = name;
        this.author = author;
        this.section = section;
        this.user = null;
        this.timeOfReceiptTheBook = LocalDateTime.now();
        this.timeOfReturnTheBook = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Book book = (Book) o;
        return ID == book.getID()
            && pages == book.getPages()
            && Objects.equals(name, book.getName())
            && Objects.equals(author, book.getAuthor())
            && Objects.equals(section, book.getSection());
    }

    @Override
    public int hashCode() {
        return
            Objects.hash(
                ID,
                pages,
                name,
                author,
                section
            );
    }

    @Override
    public String toString() {
        return "Book{"
            + "ID=" + ID
            + ", pages=" + pages
            + ", name='" + name + '\''
            + ", author='" + author + '\''
            + ", section='" + section + '\''
            + '}';
    }

    long getID() {
        return ID;
    }

    int getBookcaseNumber() {
        return bookcaseNumber;
    }

    int getShelfNumber() {
        return shelfNumber;
    }

    int getShelfSpace() {
        return shelfSpace;
    }

    private int getPages() {
        return pages;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    private String getSection() {
        return section;
    }

    public LocalDateTime getTimeOfReceiptTheBook() {
        return timeOfReceiptTheBook;
    }

    LocalDateTime getTimeOfReturnTheBook() {
        return timeOfReturnTheBook;
    }

    void setTimeOfReceiptTheBook(LocalDateTime timeOfReceiptTheBook) {
        this.timeOfReceiptTheBook = timeOfReceiptTheBook;
    }

    void setTimeOfReturnTheBook(LocalDateTime timeOfReturnTheBook) {
        this.timeOfReturnTheBook = timeOfReturnTheBook;
    }

    VisitorImpl getUser() {
        return user;
    }

    void setUser(VisitorImpl user) {
        this.user = user;
    }

    void setBookcaseNumber(int wardrobeNumber) {
        this.bookcaseNumber = wardrobeNumber;
    }

    void setShelfNumber(int shelfNumber) {
        this.shelfNumber = shelfNumber;
    }

    void setShelfSpace(int shelfSpace) {
        this.shelfSpace = shelfSpace;
    }
}
