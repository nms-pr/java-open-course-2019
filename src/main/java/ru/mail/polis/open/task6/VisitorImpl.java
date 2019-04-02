package ru.mail.polis.open.task6;

import java.util.ArrayList;
import java.util.List;

public class VisitorImpl extends AbstractPerson implements Visitor {

    private List<Book> takenBooks;

    public VisitorImpl(
        String surname,
        String name,
        String patronymic,
        char gender,
        int age,
        int salary
    ) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.gender = gender;
        this.salary = salary;
        this.age = age;
        this.salary = salary;
        takenBooks = new ArrayList<>();
    }

    @Override
    public void takeBook(String name, String author) {
        takenBooks.add(
            Library
            .librarian
            .giveBook(
                name,
                author
            )
        );
    }

    @Override
    public void takeBook(String[] names, String[] authors) {
        if (names.length != authors.length) {
            throw new IllegalArgumentException("Incorrect request");
        }
        List<Book> books = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            books.add(Library.librarian.giveBook(names[i], authors[i]));
        }
    }

    @Override
    public void takeBook(long ID) {
         takenBooks.add(
             Library
            .librarian
            .giveBook(ID)
         );
    }

    @Override
    public void takeBook(long[] IDs) {
        List<Book> books = new ArrayList<>();
        for (int i = 0; i < IDs.length; i++) {
            books.add(Library.librarian.giveBook(IDs[i]));
        }
    }

    @Override
    public void giveAway(Book book) {
        int number = takenBooks.indexOf(book);
        Library
            .librarian
            .putBook(
                takenBooks
                    .remove(number)
            );
    }

    @Override
    public void giveAway(List<Book> books) {
        int number;
        for (Book book : books) {
            number = takenBooks.indexOf(book);
            Library
                .librarian
                .putBook(
                    takenBooks
                        .remove(number)
                );
        }
    }

    @Override
    void salut() {
        System.out.println("Здравствуйте! Что интересного можете предложить почитать?");
    }

    @Override
    void farewell() {
        System.out.println("Спасибо, до свидания!");
    }

    public List<Book> getTakenBooks() {
        return takenBooks;
    }
}
