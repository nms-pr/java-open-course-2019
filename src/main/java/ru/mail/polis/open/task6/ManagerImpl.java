package ru.mail.polis.open.task6;

import org.jetbrains.annotations.NotNull;

public class ManagerImpl extends AbstractPerson implements Manager {

    public ManagerImpl(
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
    public Book add(Book book) {
        if (book == null) {
            throw new NullPointerException("Book cannot be a NULL");
        }
        if (isBusyPlace(
            book.getWardrobeNumber(),
            book.getShelfNumber(),
            book.getShelfSpace()
        )
        ) {
            book = searchPlaceForBook(book);
            return book;
        }
        Library
            .getLibraryWardrobe()
            .get(book.getWardrobeNumber())
            .getShelfInWardrobe()
            .get(book.getShelfNumber())
            .getBookShelf()
            .put(
                book.getShelfSpace(),
                book
            );
        return book;
    }

    @Override
    public void remove(Book book) {
        if (book == null) {
            throw new NullPointerException("Book cannot be a NULL");
        }
        if (Library
            .getLibraryWardrobe()
            .get(book.getWardrobeNumber())
            .getShelfInWardrobe()
            .get(book.getShelfNumber())
            .getBookShelf()
            .containsKey(book.getShelfSpace())
        ) {
            Library
                .getLibraryWardrobe()
                .get(book.getWardrobeNumber())
                .getShelfInWardrobe()
                .get(book.getShelfNumber())
                .getBookShelf()
                .remove(book.getShelfSpace());
            return;
        }
        throw new NoSuchBookException("Library haven't this book");
    }

    @Override
    public void openLibrary() {
        Library.setOpened(true);
    }

    @Override
    public void closeLibrary() {
        Library.setOpened(false);
    }

    @Override
    void salut() {
        System.out.println("Привтствую. Как Вам у нас в библиотеке?");
    }

    @Override
    void farewell() {
        System.out.println("Всего хорошего!");
    }

//    Book searchSameBooks(Book book) {
//        for (Wardrobe wardrobe : Library.getLibraryWardrobe().values()) {
//            for (Shelf shelf : wardrobe.getShelfInWardrobe().values()) {
//                for (Book bookOnShelf : shelf.getBookShelf().values()) {
//                    if (bookOnShelf.equals(book)) {
//                        book.setQuantity(
//                            book.getQuantity() + bookOnShelf.getQuantity()
//                        );
//                        return book;
//                    }
//                }
//            }
//        }
//        return book;
//    }

    boolean isBusyPlace (
        int wardrobeNumber,
        int shelfNumber,
        int placeNumber
    ) {
        return Library
            .getLibraryWardrobe()
            .get(wardrobeNumber)
            .getShelfInWardrobe()
            .get(shelfNumber)
            .getBookShelf()
            .containsKey(placeNumber);
    }

    Book searchPlaceForBook(Book book) {
        for (Wardrobe wardrobe : Library.getLibraryWardrobe().values()) {
            for (Shelf shelf : wardrobe.getShelfInWardrobe().values()) {
                int space = 0;
                while (shelf.getBookShelf().containsKey(++space))
                {}
                if (space <= shelf.getCapacity()) {
                    shelf.getBookShelf().put(space, book);
                    book.setShelfSpace(space);
                    book.setShelfNumber(shelf.getShelfNumber());
                    book.setWardrobeNumber(wardrobe.getWardrobeNumber());
                    return book;
                }
            }
        }
        throw new NoSpaceForBookException("Haven't space in Library for this book");
    }
}
