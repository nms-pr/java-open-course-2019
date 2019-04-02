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
        int bookcaseNumber,
        int shelfNumber,
        int shelfSpace,
        int pages,
        String name,
        String author,
        String section
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
    }

    public long getID() {
        return ID;
    }

    public int getBookcaseNumber() {
        return bookcaseNumber;
    }

    public int getShelfNumber() {
        return shelfNumber;
    }

    public int getShelfSpace() {
        return shelfSpace;
    }

    public int getPages() {
        return pages;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getSection() {
        return section;
    }

    public LocalDateTime getTimeOfReceiptTheBook() {
        return timeOfReceiptTheBook;
    }

    public LocalDateTime getTimeOfReturnTheBook() {
        return timeOfReturnTheBook;
    }

    public void setTimeOfReceiptTheBook(LocalDateTime timeOfReceiptTheBook) {
        this.timeOfReceiptTheBook = timeOfReceiptTheBook;
    }

    public void setTimeOfReturnTheBook(LocalDateTime timeOfReturnTheBook) {
        this.timeOfReturnTheBook = timeOfReturnTheBook;
    }

    public VisitorImpl getUser() {
        return user;
    }

    public void setUser(VisitorImpl user) {
        this.user = user;
    }

    public void setBookcaseNumber(int wardrobeNumber) {
        this.bookcaseNumber = wardrobeNumber;
    }

    public void setShelfNumber(int shelfNumber) {
        this.shelfNumber = shelfNumber;
    }

    public void setShelfSpace(int shelfSpace) {
        this.shelfSpace = shelfSpace;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return getID() == book.getID()
            && getPages() == book.getPages()
            && Objects.equals(getName(), book.getName())
            && Objects.equals(getAuthor(), book.getAuthor())
            && Objects.equals(getSection(), book.getSection()
        );
    }

    @Override
    public int hashCode() {
        return
            Objects.hash(
                getID(),
                getPages(),
                getName(),
                getAuthor(),
                getSection()
            );
    }
}
