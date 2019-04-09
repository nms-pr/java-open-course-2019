package ru.mail.polis.open.task6.office;

import ru.mail.polis.open.task6.Genres;
import ru.mail.polis.open.task6.Guest;

import java.time.LocalDateTime;
import java.util.List;

public class GuestOrder {

    private List<String> wantedNames;
    private List<Genres> wantedGenres;
    private Guest owner;
    private LocalDateTime orderDate;
    private List<Book> takenBooks;

    GuestOrder(List<String> names, Guest owner) {
        this.wantedNames = names;
        orderDate = LocalDateTime.now();
        this.owner = owner;
    }

    GuestOrder(List<String> names, List<Genres> genres, Guest owner) {
        this(names, owner);
        this.wantedGenres = genres;
    }

    List<String> getGetWantedBookNames() {
        return wantedNames;
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

    public List<Book> getTakenBooks() {
        return takenBooks;
    }

    void setTakenBooks(List<Book> takenBooks) {
        owner.setTakenBooks(takenBooks);
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
        return wantedNames.equals(that.wantedNames)
            && wantedGenres.equals(that.wantedGenres);
    }

    @Override
    public int hashCode() {
        return 31 * wantedNames.hashCode() + 13 * wantedGenres.hashCode();
    }

}
