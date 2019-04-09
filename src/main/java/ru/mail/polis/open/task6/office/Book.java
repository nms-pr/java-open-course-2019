package ru.mail.polis.open.task6.office;

import ru.mail.polis.open.task6.Genres;

import java.util.Random;


public class Book {

    private int id;
    private String name;
    private Genres genre;

    Book(String name, Genres genre) {
        id = new Random().nextInt();
        this.name = name;
        this.genre = genre;
    }

    Book(int id, String name, Genres genre) {
        this(name, genre);
        this.id = id;
    }

    int getId() {
        return id;
    }

    String getName() {
        return name;
    }

    Genres getGenre() {
        return genre;
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
        return name.equals(book.name)
            && genre == book.genre;
    }

    @Override
    public int hashCode() {
        return 31 * Integer.valueOf(name)
            * genre.hashCode();
    }
}
