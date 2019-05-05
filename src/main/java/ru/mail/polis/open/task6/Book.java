package ru.mail.polis.open.task6;

import java.time.LocalDateTime;
import java.util.Objects;

public class Book {
    public final long id;
    public int shelf; // номер полки
    public int shelfPlace; //место на полке (номер)
    public final String name;
    public final String section;
    private LocalDateTime timeOfReceiving;
    private LocalDateTime timeOfReturn;
    private CustomerImpl userWhoTakes;

    public Book(int shelf, int shelfPlace, String name, String section) {
        this.shelf = shelf - 1;
        this.id = name.length() * section.length() * (shelf + 1) + 1000005;
        this.shelfPlace = shelfPlace - 1;
        this.name = name;
        this.section = section;
        this.timeOfReceiving = LocalDateTime.now();
        this.timeOfReturn = LocalDateTime.now();
        this.userWhoTakes = null;
    }


    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSection() {
        return section;
    }

    public int getShelf() {
        return shelf + 1;
    }

    public int getShelfPlace() {
        return shelfPlace + 1;
    }

    public LocalDateTime getTimeOfReceiving() {
        return timeOfReceiving;
    }

    public LocalDateTime getTimeOfReturn() {
        return timeOfReturn;
    }

    public CustomerImpl getUserWhoTakes() {
        return userWhoTakes;
    }

    public void setUserWhoTakes(CustomerImpl userWhoTakes) {
        this.userWhoTakes = userWhoTakes;
    }

    public String toString() {
        return "Книга {"
                + " идентификационный номер = " + id + ", название = " + name + ", секция = " + section + " }" + "\n";
    }

    public void setTimeOfReceiving(LocalDateTime timeOfReceiving) {
        this.timeOfReceiving = timeOfReceiving;
    }

    public void setTimeOfReturn(LocalDateTime timeOfReturn) {
        this.timeOfReturn = timeOfReturn;
    }

    public void setShelfPlace(int shelfPlace) {
        this.shelfPlace = shelfPlace - 1;
    }

    public void setShelf(int shelf) {
        this.shelf = shelf - 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Book)) {
            return false;
        }
        Book book = (Book) o;
        return getId() == book.getId()
                && getShelf() == book.getShelf()
                && getShelfPlace() == book.getShelfPlace()
                && getName().equals(book.getName())
                && getSection().equals(book.getSection())
                && Objects.equals(getTimeOfReceiving(), book.getTimeOfReceiving())
                && Objects.equals(getTimeOfReturn(), book.getTimeOfReturn())
                && Objects.equals(getUserWhoTakes(), book.getUserWhoTakes());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),
                getShelf(),
                getShelfPlace(),
                getName(),
                getSection(),
                getTimeOfReceiving(),
                getTimeOfReturn(),
                getUserWhoTakes());
    }
}