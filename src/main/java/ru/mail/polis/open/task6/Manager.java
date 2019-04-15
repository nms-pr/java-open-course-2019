package ru.mail.polis.open.task6;

public class Manager extends Person implements IManager {

    public Manager(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    @Override
    void sayHello() {
        System.out.println("Доброго времени суток!");
    }

    @Override
    void sayGoodbye() {
        System.out.println("Всего доброго!");
    }

    @Override
    public void addBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Книга не должна быть null");
        }
        if (Library.placeIsBusy(book.numberBookShelf)) {
            book = Library.librarian.findPlaceForBook(book);
            Library.getBookList().add(book);
        } else {
            Library.getBookShelfList().get(book.numberBookShelf).getBooksOnShelf().add(book.numberBookShelf, book);
            Library.getBookList().add(book);
        }
    }

    @Override
    public void deleteBook(Book book) {
        boolean check = false;
        if (book == null) {
            throw new IllegalArgumentException("Книга не может быть null");
        }
        for (Book currentBook : Library.getBookList()) {
            if (currentBook == book) {
                check = true;
            }
        }
        if (check) {
            Library.getBookShelfList().get(book.numberBookShelf).getBooksOnShelf().remove(book);
            Library.getBookList().remove(book);
        } else {
            throw new IllegalArgumentException("Такой книги  нет в библлиотеке");
        }
    }

    @Override
    public void openTheLibrary() {
        Library.setOpenOrClosed(true);
        Library.baseOfVisitorsAndBooks();
    }

    @Override
    public void closeTheLibrary() {
        Library.setOpenOrClosed(false);
    }
}
