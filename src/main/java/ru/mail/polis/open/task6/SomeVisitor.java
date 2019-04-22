package ru.mail.polis.open.task6;

import java.util.ArrayList;
import java.util.List;

public class SomeVisitor extends People implements Visitor {

    private final int LIST_OF_BOOKS_CAPACITY = 5; // todo: Проверять емкость
    private List<Book> listOfBookForVisitor;

    SomeVisitor(String name, int age, char gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        listOfBookForVisitor = new ArrayList<>();
        for (int i = 0; i < LIST_OF_BOOKS_CAPACITY; i++) {
            listOfBookForVisitor.add(null);
        }
    }

    public void returnBook(Book book) {
       Library.librarian.takeBookIn(book, this);
       listOfBookForVisitor.remove(book);
    }

    public void getBook(String bookName) {
        if (bookName.length() == 0) {
            throw new IllegalArgumentException("Название книги не может быть пустым!");
        }
        Book book = Library.librarian.giveOutBook(bookName, this);
        if (book == null) {
            System.out.println("Запрашиваемая книга не найдена");
        } else {
            listOfBookForVisitor.add(book);
        }
    }

//  В методе ниже подразумеваем, что книга всегда существует во владении у пользователя,
//  поэтому не проверяем название книги на ее существование у пользователя

    public void prolongBook(Book book) {
        Library.librarian.takeBookForProlong(book);
    }

    void greeting() {
        System.out.println("Здравствуйте! Есть че?");
    }

    void goodbye() {
        System.out.println("Спасибо! До свидания.");
    }
}
