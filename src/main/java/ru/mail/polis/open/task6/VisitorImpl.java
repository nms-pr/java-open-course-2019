package ru.mail.polis.open.task6;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        takenBooks = new ArrayList<>();
    }

    @Override
    public void takeBook(String name, String author) {
        takenBooks.add(
            Library
            .librarian
            .giveBook(
                name,
                author,
                this
            )
        );
    }

    @Override
    public void takeBook(String[] names, String[] authors) {
        if (names.length != authors.length
            || names.length == 0) {
            throw new IllegalArgumentException("Incorrect request");
        }
        for (int i = 0; i < names.length; i++) {
            takenBooks.add(
                Library.librarian.giveBook(
                    names[i],
                    authors[i],
                    this
                )
            );
        }
    }

    @Override
    public void takeBook(long ID) {
         takenBooks.add(
             Library
            .librarian
            .giveBook(ID, this)
         );
    }

    @Override
    public void takeBook(long[] IDs) {
        if (IDs.length == 0) {
            throw new IllegalArgumentException("Incorrect request");
        }
        for (int i = 0; i < IDs.length; i++) {
            takenBooks.add(Library.librarian.giveBook(IDs[i], this));
        }
    }

    @Override
    public void giveAway(Book book) {
        int number = takenBooks.indexOf(book);
        Library
            .librarian
            .putBook(
                takenBooks
                    .remove(number),
                this
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
                        .remove(number),
                    this
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

    @Override
    public int hashCode() {
        return Objects.hash(
            age,
            salary,
            gender,
            name,
            surname,
            patronymic,
            takenBooks
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VisitorImpl visitor = (VisitorImpl) o;
        return age == visitor.getAge()
            && salary == visitor.getSalary()
            && gender == visitor.getGender()
            && Objects.equals(name, visitor.getName())
            && Objects.equals(surname, visitor.getSurname())
            && Objects.equals(patronymic, visitor.getPatronymic())
            && Objects.equals(takenBooks, visitor.getTakenBooks());
    }

    public void infoAboutTakenBook() {
        for (Book book : takenBooks) {
            book.toString();
        }
    }

    public int getAge() {
        return age;
    }

    public int getSalary() {
        return salary;
    }

    public char getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public List<Book> getTakenBooks() {
        return takenBooks;
    }
}
