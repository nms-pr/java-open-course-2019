package ru.mail.polis.open.task6;

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
            return book;
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
        return book;
    }

    @Override
    public void remove(Book book) {
        if (book == null) {
            throw new NullPointerException("Book cannot be a NULL");
        }
        if (Library
            .getLibraryBookcase()
            .get(book.getBookcaseNumber())
            .getShelfInBookcase()
            .get(book.getShelfNumber())
            .getBookShelf()
            .get(book.getShelfSpace()) != null
            && Library
            .getLibraryBookcase()
            .get(book.getBookcaseNumber())
            .getShelfInBookcase()
            .get(book.getShelfNumber())
            .getBookShelf()
            .get(book.getShelfSpace())
            .equals(book)
        ) {
            Library
                .getLibraryBookcase()
                .get(book.getBookcaseNumber())
                .getShelfInBookcase()
                .get(book.getShelfNumber())
                .getBookShelf()
                .remove(book.getShelfSpace());

            Library
                .showAvailableBooks()
                .remove(book);
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
}
