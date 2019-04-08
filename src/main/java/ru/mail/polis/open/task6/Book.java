package ru.mail.polis.open.task6;

import java.util.Objects;

public class Book {

    private String title;
    private String genre;
    private int id;

    Book(String title, String genre, int id) {
        this.title = title;
        this.genre = genre;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, genre, id);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        Book book = (Book) obj;
        if (this == book) {
            return true;
        } else {
            return this.title.equals(book.getTitle()) && this.genre.equals(book.getGenre())
                    && this.id == book.getId();
        }
    }
}
