package ru.mail.polis.open.task6;

public class Book {

    private String title;
    private String genre;
    private long id;

    public Book(String title, String genre, long id) {
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

    @Override
    public String toString() {
        return title + "\t" + genre + "\t" + id;
    }
}
