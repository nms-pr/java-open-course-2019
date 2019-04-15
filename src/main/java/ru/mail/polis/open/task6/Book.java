package ru.mail.polis.open.task6;

import java.time.LocalDateTime;
import java.util.Objects;

public class Book {
    public final long id;
    public int numberBookShelf;
    public final String bookTitle;
    public final String bookGenre;
    private LocalDateTime bookReceiptTime;
    private LocalDateTime bookReturnTime;
    private Visitor whoseBook;

    public Book(int numberBookShelf, int numberOfCopies, String bookTitle, String bookGenre) {
        this.id = numberBookShelf * numberOfCopies * bookGenre.length() * bookTitle.length() + 22000;
        this.numberBookShelf = numberBookShelf - 1;
        this.bookTitle = bookTitle;
        this.bookGenre = bookGenre;
        this.bookReceiptTime = LocalDateTime.now();
        this.bookReturnTime = LocalDateTime.now();
        this.whoseBook = null;
    }

    public long getId() {
        return id;
    }

    public int getNumberBookShelf() {
        return numberBookShelf + 1;
    }

    public void setNumberBookShelf(int numberBookShelf) {
        this.numberBookShelf = numberBookShelf - 1;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getBookGenre() {
        return bookGenre;
    }

    public LocalDateTime getBookReceiptTime() {
        return bookReceiptTime;
    }

    public void setBookReceiptTime(LocalDateTime bookReceiptTime) {
        this.bookReceiptTime = bookReceiptTime;
    }

    public LocalDateTime getBookReturnTime() {
        return bookReturnTime;
    }

    public void setBookReturnTime(LocalDateTime bookReturnTime) {
        this.bookReturnTime = bookReturnTime;
    }

    public Visitor getWhoseBook() {
        return whoseBook;
    }

    public void setWhoseBook(Visitor whoseBook) {
        this.whoseBook = whoseBook;
    }

    public String toString() {
        return "Книга {Идентификатор : " + id + ", название книги : " + bookTitle
            + ", жанр книги : " + bookGenre + " }" + "\n";
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Book)) {
            return false;
        }
        Book book = (Book) object;
        return id == book.id
            && numberBookShelf == book.numberBookShelf
            && bookTitle.equals(book.bookTitle)
            && bookGenre.equals(book.bookGenre)
            && bookReceiptTime.equals(book.bookReceiptTime)
            && bookReturnTime.equals(book.bookReturnTime)
            && Objects.equals(whoseBook, book.whoseBook);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numberBookShelf, bookTitle, bookGenre, bookReceiptTime, bookReturnTime, whoseBook);
    }
}
