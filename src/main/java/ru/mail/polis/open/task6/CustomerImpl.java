package ru.mail.polis.open.task6;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CustomerImpl extends Human implements Customer {
    public List<Book> takenBooks;

    CustomerImpl(String name,
                 String surname,
                 String patronymic,
                 byte age) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.age = age;
        takenBooks = new ArrayList<>();
    }

    @Override
    void sayHello() {
        System.out.println("Здравствуйте! Дайте что-нибудь почитать!");
    }

    @Override
    void sayGoodbye() {
        System.out.println("Спасибо! До свидания!");
    }

    @Override
    public void takeBook(String name) {
        if (name.length() == 0) {
            throw new IllegalArgumentException("Your request cannot be empty");
        }
        takenBooks.add(
                Library.librarian.giveBook(name, this));
    }

    @Override
    public void takeBook(long id) {
        takenBooks.add(
                Library.librarian.giveBook(id, this));
    }

    @Override
    public void takeBook(String[] names) {
        if (names.length == 0) {
            throw new IllegalArgumentException("Your request cannot be empty");
        }
        for (var name : names) {
            takenBooks.add(Library.librarian.giveBook(name, this));
        }

    }

    @Override
    public void takeBook(long[] ids) {
        if (ids.length == 0) {
            throw new IllegalArgumentException("Your request cannot be empty");
        }
        for (var id : ids) {
            takenBooks.add(Library.librarian.giveBook(id, this));
        }

    }

    @Override
    public void returnBook(Book book) {
        int number = takenBooks.indexOf(book);
        Library.librarian.putBook(takenBooks.remove(number), this);
    }

    @Override
    public void returnBook(Book[] books) {
        for (var book : books) {
            int number = takenBooks.indexOf(book);
            Library.librarian.putBook(takenBooks.remove(number), this);
        }
        int size = books.length;
        for (int i = size - 1; i >= 0; i--) {
            takenBooks.remove(books[i]);
        }
    }

    private int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    String getSurname() {
        return surname;
    }

    String getPatronymic() {
        return patronymic;
    }

    List<Book> getTakenBooks() {
        return takenBooks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CustomerImpl)) {
            return false;
        }
        CustomerImpl customer = (CustomerImpl) o;
        return Objects.equals(getTakenBooks(), customer.getTakenBooks());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTakenBooks());
    }
}