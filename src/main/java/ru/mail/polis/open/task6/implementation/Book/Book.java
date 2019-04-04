package ru.mail.polis.open.task6.implementation.Book;

import java.util.Objects;

public class Book {
    private final String name;

    private final String author;
    private final Category category;
    public Book(String name, String author, Category category) {
        this.name = name;
        this.author = author;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(name, book.name) &&
            Objects.equals(author, book.author) &&
            category == book.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, author, category);
    }

    @Override
    public String toString() {
        return "Book{" +
            "name='" + name + '\'' +
            ", author='" + author + '\'' +
            ", category=" + category +
            '}';
    }
}
