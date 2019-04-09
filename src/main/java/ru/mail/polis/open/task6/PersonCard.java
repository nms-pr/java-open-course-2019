package ru.mail.polis.open.task6;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PersonCard extends Person {

    private final @NotNull List<BookGiven> listCurrentBooks;
    private final @NotNull List<BookGiven> listBookHistory;
    private static final String DELIMITER = ", ";
    private static final String ERROR_MESSAGE = "List is empty";
    private static final String TYPE = "Жанр: ";
    private static final String ID = "ID: ";
    private static final String DATE_GET = "Получена: ";
    private static final String TERM_DATE = "Срок: ";

    public PersonCard(@NotNull Person guest) {
        super(guest.name, guest.surname, guest.birthday);
        this.listCurrentBooks = new ArrayList<>(10);
        this.listBookHistory = new ArrayList<>(50);
    }

    public void takeBooks(BookGiven... books) {
        listCurrentBooks.addAll(Arrays.asList(books));
    }

    public BookGiven returnBook(@NotNull String name, @NotNull String author) {
        return finder(name, author);
    }

    public List<BookGiven> returnBook(int... id) {
        List<BookGiven> listReturn = new ArrayList<>(id.length);
        BookGiven book;
        for (int oneId: id) {
            book = finder(oneId);
            if (book != null) {
                listReturn.add(book);
            }
        }
        return listReturn;
    }

    public String[] viewCurrentBooks() {
        return listToString(listCurrentBooks);
    }

    public String[] viewBookHisory() {
        return listToString(listBookHistory);
    }

    private String[] listToString(List<BookGiven> bookList) {
        if (bookList.size() == 0) {
            return new String[] { ERROR_MESSAGE };
        }
        String[] array = new String[bookList.size()];
        StringBuilder builder;
        BookGiven currentBook;
        for (int index = 0; index < array.length; index++) {
            builder = new StringBuilder();
            currentBook = bookList.get(index);
            builder.append('"').append(currentBook.name).append('"');
            builder.append(DELIMITER);
            builder.append(currentBook.author);
            builder.append(DELIMITER);
            builder.append(TYPE).append(currentBook.section);
            builder.append(DELIMITER);
            builder.append(DATE_GET).append(currentBook.dateIssue);
            builder.append(DELIMITER);
            builder.append(ID).append(currentBook.id);
            builder.append(DELIMITER);
            builder.append(TERM_DATE).append(currentBook.returnDate);
            array[index] = builder.toString();
        }
        return array;
    }

    private BookGiven finder(int id) {
        for (BookGiven book : listCurrentBooks) {
            if (book.id == id) {
                listCurrentBooks.remove(book);
                listBookHistory.add(book);
                return book;
            }
        }
        return null;
    }

    private BookGiven finder(@NotNull String name, @NotNull String author) {
        for (BookGiven book : listCurrentBooks) {
            if (book.name.equals(name)
                && book.author.equals(author)) {
                listCurrentBooks.remove(book);
                listBookHistory.add(book);
                return book;
            }
        }
        return null;
    }
}
