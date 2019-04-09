package ru.mail.polis.open.task6;

import org.jetbrains.annotations.NotNull;

public class BookCard extends BookInfo {

    private int booksCounter;
    private int totalBooksAmount;
    private static int idCounter = 1;

    public BookCard(@NotNull String name, @NotNull String author, @NotNull Section section, int totalBooksAmount) {
        super(name, author, section, idCounter++);
        this.totalBooksAmount = totalBooksAmount;
        booksCounter = totalBooksAmount;
    }

    public BookGiven getBook(Person reader) {
        if (booksCounter > 0) {
            booksCounter--;
            int term = 0;
            switch (this.section) {
                case Other:
                    term = 7;
                    break;
                case Poetry:
                case Fantastic:
                    term = 21;
                    break;
                case Classical:
                    term = 30;
                    break;
                case Adventures:
                    term = 28;
                    break;
                case Scientific:
                case Educational:
                    term = 5;
                    break;
                default:
                    term = 3;
                    break;
            }
            return new BookGiven(this, reader, term);
        }
        return null;
    }

    public void returnBooks(int booksAmount) {
        if (booksAmount < 1) {
            throw new IllegalArgumentException("Incorrect amount of books");
        }
        booksCounter += booksAmount;
    }

    public void addBooks(int booksAmount) {
        returnBooks(booksAmount);
        totalBooksAmount += booksAmount;
    }

    public int getTotalBooksAmount() {
        return totalBooksAmount;
    }

    public int getGivenBooksAmount() {
        return totalBooksAmount - booksCounter;
    }

    public int getBooksCounter() {
        return booksCounter;
    }
}
