package ru.mail.polis.open.task6;

public class Book {

    private String title;
    private String genre;
    private int id;

    public Book(String title, String genre, int id) {
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
    public String toString() {
        return title + "\t" + genre + "\t" + id;
    }

    @Override
    public int hashCode() {
        return title.hashCode() + genre.hashCode() + id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != Book.class) {
            return false;
        }
        Book book = (Book) obj;
        if (this == book) {
            return true;
        } else if (this.title.equals(book.getTitle()) && this.genre.equals(book.getGenre())
                && this.id == book.getId()) {
            return true;
        }
        return false;
    }
}
