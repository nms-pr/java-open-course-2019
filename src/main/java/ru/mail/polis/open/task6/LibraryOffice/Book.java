package ru.mail.polis.open.task6.LibraryOffice;

import ru.mail.polis.open.task6.Genres;

import java.util.Date;

class Book {

    private int id;
    private String name;
    private int place;
    private Date lastTimePicked;
    private boolean isOnPlace;
    private Genres genre;

    Book(String name, int place, Genres genre) {
        id = this.hashCode();
        this.name = name;
        this.place = place;
        lastTimePicked = new Date();
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

    Date getLastTimePicked() {
        return lastTimePicked;
    }

    boolean isOnPlace() {
        return isOnPlace;
    }

    Genres getGenre() {
        return genre;
    }

}
