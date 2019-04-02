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
        this.salary = salary;
    }

    @Override
    public Book giveBook(String name, String author, VisitorImpl visitor) {
        Book book = searchSuchBooks(name, author);
        updateInfoAfterTakenBook(book, visitor);
        return book;
    }

    @Override
    public Book giveBook(long ID, VisitorImpl visitor) {
        Book book = searchSuchBooks(ID);
        updateInfoAfterTakenBook(book, visitor);
        return book;
    }

    @Override
    public void putBook(Book book) {
        if (book == null) {
            throw new NullPointerException("Book cannot be a NULL");
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
            book = Library
                .librarian
                .searchPlaceForBook(book);
            Library
                .showAvailableBooks()
                .add(book);
            return;
        }
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
            .showAvailableBooks()
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
                    if (book
                        .getName()
                        .equals(name)
                    && book
                    .getAuthor()
                    .equals(author)) {
                        return book;
                    }
                }
            }
        }
        throw new NoSuchBookException("Such book not found");
    }

    Book searchSuchBooks(long ID) {
        for (Bookcase wardrobe : Library.getLibraryBookcase().values()) {
            for (Shelf shelf : wardrobe.getShelfInBookcase().values()) {
                for (Book book : shelf.getBookShelf().values()) {
                    if (book.getID() == ID) {
                        return book;
                    }
                }
            }
        }
        throw new NoSuchBookException("Such book not found");
    }

    LocalDateTime setupTimeTakenBook() {
        return LocalDateTime.now();
    }

    LocalDateTime setupTimeGiveAwayBook() {
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
                int space = 0;
                while (shelf.getBookShelf().containsKey(++space))
                {}
                if (space <= shelf.getCapacity()) {
                    shelf.getBookShelf().put(space, book);
                    book.setShelfSpace(space);
                    book.setShelfNumber(shelf.getShelfNumber());
                    book.setBookcaseNumber(bookcase.getBookcaseNumber());
                    return book;
                }
            }
        }
        throw new NoSpaceForBookException("Haven't space in Library for this book");
    }

    void updateInfoAfterTakenBook(Book book, VisitorImpl visitor){
        book.setTimeOfReceiptTheBook(setupTimeTakenBook());
        book.setTimeOfReturnTheBook(setupTimeGiveAwayBook());
        book.setUser(visitor);
        Library.busyBooks.add(book);
    }
}