package ru.mail.polis.open.task6;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Visitor extends Person implements IVisitor {

    public List<Book> takenBooks;

    Visitor(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        takenBooks = new ArrayList<>();
    }

    @Override
    void sayHello() {
        System.out.println("Добрейшего денечка. Есть чо?");
    }

    @Override
    void sayGoodbye() {
        System.out.println("От души. Всего доброго");
    }

    @Override
    public void takeBook(long id) {
        takenBooks.add(Library.librarian.giveBook(id, this));
    }

    @Override
    public void takeBookByTitle(String[] bookTitle) {
        if (bookTitle.length == 0) {
            throw new IllegalArgumentException("Необходимо название книги");
        }
        for (String book : bookTitle) {
            takenBooks.add(Library.librarian.giveBook(book, this));
        }
    }

    @Override
    public void takeBookByGenre(String... bookGenre) {
        if (bookGenre.length == 0) {
            throw new IllegalArgumentException("Необходим жанр книги");
        }
        for (String book : bookGenre) {
            takenBooks.add(Library.librarian.giveBook(book, this));
        }
    }

    @Override
    public void returnBook(Book... book) {
        for (Book currentBook : book) {
            int number = takenBooks.indexOf(currentBook);
            Library.librarian.putBook(takenBooks.remove(number), this);

        }
    }

    void showTakenBook() {
        for (Book book : takenBooks) {
            System.out.println(book.toString());
        }
    }

    int getAge() {
        return age;
    }

    String getName() {
        return name;
    }

    String getSurname() {
        return surname;
    }

    List<Book> getTakenBooks() {
        return takenBooks;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Visitor)) {
            return false;
        }
        Visitor visitor = (Visitor) object;
        return Objects.equals(takenBooks, visitor.takenBooks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(takenBooks);
    }
}
