package ru.mail.polis.open.task6.office;

import ru.mail.polis.open.task6.Genres;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GuestOrder {

    private List<String> getWantedBookNames;
    private List<Genres> wantedGenres;
    private int amount = 1;

    GuestOrder(List<String> names) {
        this.getWantedBookNames = names;
    }

    GuestOrder(List<String> names, List<Genres> genres) {
        new GuestOrder(names);
        this.wantedGenres = genres;
    }

    GuestOrder(String name) {
        this.getWantedBookNames = new ArrayList<>();
        getWantedBookNames.add(name);
    }

    GuestOrder(Genres genre) {
        assignGenre(genre);
    }

    GuestOrder(String name, Genres genre) {
        new GuestOrder(name);
        assignGenre(genre);
    }


    public List<String> getGetWantedBookNames() {
        return getWantedBookNames;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GuestOrder that = (GuestOrder) o;
        return amount == that.amount
            && Objects.equals(getWantedBookNames, that.getWantedBookNames)
            && Objects.equals(wantedGenres, that.wantedGenres);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getWantedBookNames, wantedGenres, amount);
    }
}
