package ru.mail.polis.open.task6.office;

import ru.mail.polis.open.task6.Genres;


public class Book {

    private int id;
    private String name;
    private int place;
    private boolean isOnPlace;
    private Genres genre;

    Book(String name, int place, Genres genre) {
        id = this.hashCode();
        this.name = name;
        this.place = place;
        isOnPlace = true;
        this.genre = genre;
    }

    Book(int id, String name, int place, Genres genre) {
        new Book(name, place, genre);
        this.id = id;
    }

    int getId() {
        return id;
    }

    String getName() {
        return name;
    }

    int getPlace() {
        return place;
    }

    boolean isOnPlace() {
        return isOnPlace;
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
        return place == book.place
            && name.equals(book.name)
            && genre == book.genre;
    }

    @Override
    public int hashCode() {
        return (31 * Integer.valueOf(name) + place)
            * Integer.valueOf(String.valueOf(genre));
    }
}
