package ru.mail.polis.open.task6.office;

import ru.mail.polis.open.task6.Genres;
import ru.mail.polis.open.task6.Guest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class GuestOrder {

    private List<String> getWantedBookNames;
    private List<Genres> wantedGenres;
    private Guest owner;
    private LocalDateTime orderDate;
    private List<Book> takenBooks;

    GuestOrder(List<String> names, Guest owner) {
        this.getWantedBookNames = names;
        orderDate = LocalDateTime.now();
        this.owner = owner;
    }

    GuestOrder(List<String> names, List<Genres> genres, Guest owner) {
        new GuestOrder(names, owner);
        this.wantedGenres = genres;
    }

    List<String> getGetWantedBookNames() {
        return getWantedBookNames;
    }

    List<Genres> getWantedGenres() {
        return wantedGenres;
    }

    public Guest getOwner() {
        return owner;
    }

    LocalDateTime getOrderDate() {
        return orderDate;
    }

    List<Book> getTakenBooks() {
        return takenBooks;
    }

    void setTakenBooks(List<Book> takenBooks) {
        this.takenBooks = takenBooks;
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
        return getWantedBookNames.equals(that.getWantedBookNames)
            && wantedGenres.equals(that.wantedGenres);
    }

    @Override
    public int hashCode() {
        return 31 * getWantedBookNames.hashCode() + 13 * wantedGenres.hashCode();
    }

}
