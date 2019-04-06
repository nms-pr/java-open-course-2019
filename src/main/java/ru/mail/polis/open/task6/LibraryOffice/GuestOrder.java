package ru.mail.polis.open.task6.LibraryOffice;

import ru.mail.polis.open.task6.Genres;

import java.util.ArrayList;
import java.util.List;

public class GuestOrder {

    private List<String> wantedBooks;
    private List<Genres> wantedGenres;
    private int amount = 1;

    GuestOrder(List<String> names) {
        this.wantedBooks = names;
    }

    GuestOrder(List<String> names, List<Genres> genres) {
        new GuestOrder(names);
        this.wantedGenres = genres;
    }

    GuestOrder(String name) {
        this.wantedBooks = new ArrayList<>();
        wantedBooks.add(name);
    }

    GuestOrder(Genres genre) {
        assignGenre(genre);
    }

    GuestOrder(String name, Genres genre) {
        new GuestOrder(name);
        assignGenre(genre);
    }


    public List<String> getWantedBooks() {
        return wantedBooks;
    }

    public List<Genres> getWantedGenres() {
        return wantedGenres;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    private void assignGenre(Genres genre) {
        this.wantedGenres = new ArrayList<>();
        wantedGenres.add(genre);
    }
}
