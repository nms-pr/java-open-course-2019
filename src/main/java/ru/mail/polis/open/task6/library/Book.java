package ru.mail.polis.open.task6.library;

import ru.mail.polis.open.task6.Customer;

import java.util.Date;
import java.util.Objects;


public class Book {
    private int id;
    private String name;
    private String section;
    private int shelfPlace;
    private Date dateOfIssue;
    private Customer owner;

    public Book(int id, String name, String section, int shelfPlace) {
        this.id = id;
        this.name = name;
        this.section = section;
        this.shelfPlace = shelfPlace;
        this.dateOfIssue = null;
        this.owner = null;
    }

    public void setDateOfIssue(Date dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public void setOuner(Customer ouner) {
        this.owner = ouner;
    }

    public Date getDateOfIssue() {
        return dateOfIssue;
    }

    public String getName() {
        return name;
    }

    public Object getowner() {
        return owner;
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
        return id == book.id
                && shelfPlace == book.shelfPlace
                && Objects.equals(name, book.name)
                && Objects.equals(section, book.section);
    }

}
