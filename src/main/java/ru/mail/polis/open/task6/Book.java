package ru.mail.polis.open.task6;

import java.util.Objects;

public class Book {
    private final int ID;
    private final int pages;
    private final String name;
    private final String author;
    private final String section;

    private int wardrobeNumber;
    private int shelfNumber;
    private int shelfSpace;
//    private int quantity;
    private int timeOfReceiptTheBook;
    private int timeOfReturnTheBook;
    private String userName;

    public Book(
        int wardrobeNumber,
        int shelfNumber,
        int shelfSpace,
        int pages,
        int quantity,
        String name,
        String author,
        String section
    ) {
        this.ID = (int)(Math.random() * 100000);
        this.wardrobeNumber = wardrobeNumber;
        this.shelfNumber = shelfNumber;
        this.shelfSpace = shelfSpace;
        this.pages = pages;
//        this.quantity = quantity;
        this.timeOfReceiptTheBook = 0;
        this.timeOfReturnTheBook = 0;
        this.name = name;
        this.author = author;
        this.section = section;
        this.userName = null;
    }

    public int getID() {
        return ID;
    }

    public int getWardrobeNumber() {
        return wardrobeNumber;
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

    public int getTimeOfReceiptTheBook() {
        return timeOfReceiptTheBook;
    }

    public int getTimeOfReturnTheBook() {
        return timeOfReturnTheBook;
    }

    public String getUserName() {
        return userName;
    }

//    public int getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(int quantity) {
//        this.quantity = quantity;
//    }

    public void setWardrobeNumber(int wardrobeNumber) {
        this.wardrobeNumber = wardrobeNumber;
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
