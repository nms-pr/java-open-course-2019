package ru.mail.polis.open.task6;

import java.time.LocalDateTime;
import java.time.Month;

public class LibrarianImpl extends AbstractPerson implements Librarian {

    LibrarianImpl(
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
    }

    @Override
    public Book giveBook(String name, String author, VisitorImpl visitor) {
        if (isInBlackList(visitor)) {
            throw new PersonIsInBlackListException("You are in black list. We cannot give you book");
        }
        Book book = searchSuchBooks(name, author);
        updateInfoAfterTakenBook(book, visitor);
        Library.getBusyBooks().add(book);
        Library.getBooks().remove(book);
        return book;
    }

    @Override
    public Book giveBook(long id, VisitorImpl visitor) {
        if (isInBlackList(visitor)) {
            throw new PersonIsInBlackListException("You are in black list. We cannot give you book");
        }
        Book book = searchSuchBooks(id);
        updateInfoAfterTakenBook(book, visitor);
        Library.getBusyBooks().add(book);
        Library.getBooks().remove(book);
        return book;
    }

    @Override
    public void putBook(Book book, VisitorImpl visitor) {
        if (book == null) {
            throw new NullPointerException("Book cannot be a NULL");
        }
        if (LocalDateTime
            .now()
            .isAfter(book.getTimeOfReturnTheBook())
        ) {
            Library.getBlackListOfVisitors().add(visitor);
        }
        book.setTimeOfReceiptTheBook(null);
        book.setTimeOfReturnTheBook(null);
        book.setUser(null);
        if (Library.isBusyPlace(
            book.getBookcaseNumber(),
            book.getShelfNumber(),
            book.getShelfSpace()
        )
        ) {
            Library
                .getBusyBooks()
                .remove(book);

            book = searchPlaceForBook(book);

            Library
                .getBooks()
                .add(book);
            return;
        }
        Library
            .getBusyBooks()
            .remove(book);

        Library
            .getLibraryBookcase()
            .get(book.getBookcaseNumber())
            .getShelfInBookcase()
            .get(book.getShelfNumber())
            .getBookShelf()
            .put(
                book.getShelfSpace(),
                book
            );

        Library
            .getBooks()
            .add(book);
    }

    @Override
    void salut() {
        System.out.println("Здравствуйте! В наличии книги на любой вкус и цвет!");
    }

    @Override
    void farewell() {
        System.out.println("До свидания! Приходите ещё и не"
            + " забывайте вовремя возвращать книги!");
    }

    Book searchSuchBooks(String name, String author) {
        for (Bookcase wardrobe : Library.getLibraryBookcase().values()) {
            for (Shelf shelf : wardrobe.getShelfInBookcase().values()) {
                for (Book book : shelf.getBookShelf().values()) {
                    if (book != null
                        && book.getName().equals(name)
                        && book.getAuthor().equals(author)) {

                        shelf.getBookShelf().put(book.getShelfNumber(), null);
                        return book;
                    }
                }
            }
        }
        throw new NoSuchBookException("Such book not found");
    }

    Book searchSuchBooks(long id) {
        for (Bookcase wardrobe : Library.getLibraryBookcase().values()) {
            for (Shelf shelf : wardrobe.getShelfInBookcase().values()) {
                for (Book book : shelf.getBookShelf().values()) {
                    if (book != null && book.getId() == id) {
                        shelf.getBookShelf().put(book.getShelfNumber(), null);
                        return book;
                    }
                }
            }
        }
        throw new NoSuchBookException("Such book not found");
    }

    void updateInfoAfterTakenBook(Book book, VisitorImpl visitor) {
        book.setTimeOfReceiptTheBook(setupTimeTakenBook());
        book.setTimeOfReturnTheBook(setupTimeGiveAwayBook());
        book.setUser(visitor);
        Library.getBusyBooks().add(book);
    }

    private LocalDateTime setupTimeTakenBook() {
        return LocalDateTime.now();
    }

    private LocalDateTime setupTimeGiveAwayBook() {
        LocalDateTime ldt = LocalDateTime.now();
        if (Month.of(12) == Month.DECEMBER) {
            return LocalDateTime.of(
                ldt.getYear() + 1,
                Month.JANUARY,
                28,
                ldt.getHour(),
                ldt.getMinute()
            );
        } else {
            return LocalDateTime.of(
                ldt.getYear(),
                Month.of(ldt.getMonthValue()),
                28,
                ldt.getHour(),
                ldt.getMinute()
            );
        }
    }

    Book searchPlaceForBook(Book book) {
        for (Bookcase bookcase : Library.getLibraryBookcase().values()) {
            for (Shelf shelf : bookcase.getShelfInBookcase().values()) {
                for (int i = 0; i < shelf.getCapacity(); i++) {
                    if (shelf.getBookShelf().get(i + 1) == null) {
                        shelf.getBookShelf().put(i + 1, book);
                        book.setShelfSpace(i + 1);
                        book.setShelfNumber(shelf.getShelfNumber());
                        book.setBookcaseNumber(bookcase.getBookcaseNumber());
                        return book;
                    }
                }
            }
        }
        throw new NoSpaceForBookException("Haven't space in Library for this book");
    }

    void infoAboutUserSpecificBook() {
        for (Book book : Library.getBusyBooks()) {
            System.out.println("Name " + book.getUser().getName()
                + " Surname " + book.getUser().getSurname()
                + " Patronymic " + book.getUser().getPatronymic()
                + " taken a book :");
            book.getUser().infoAboutTakenBook();
        }
    }

    private boolean isInBlackList(VisitorImpl visitor) {
        return Library
            .getBlackListOfVisitors()
            .contains(visitor);
    }

    void remindToVisitor(VisitorImpl visitor) {
        System.out.println("Dear " + visitor.getSurname()
            + " " + visitor.getName()
            + " " + visitor.getPatronymic() + ",\n"
            + "you taken following books : \n");

        for (Book book : visitor.getTakenBooks()) {
            System.out.println(book.toString());
            System.out.println("you should return this book : "
                + book.getTimeOfReturnTheBook());
        }
    }
}
