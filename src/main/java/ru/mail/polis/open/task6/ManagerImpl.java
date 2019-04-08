package ru.mail.polis.open.task6;

public class ManagerImpl extends Person implements Manager {
    public ManagerImpl(String name,
                       String surname,
                       String patronymic,
                       byte age) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.age = age;
    }

    @Override
    void sayHello() {
        System.out.println("Здравствуйте. Рады видеть вас в нашей библиотеке");
    }

    @Override
    void sayGoodbye() {
        System.out.println("До свидания!");
    }

    @Override
    public void add(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Книга не может быть NULL");
        }
        if (Library.placeIsBusy(book.getShelf(), book.getShelfPlace())) {
            book = Library.librarian.findPlaceForBook(book);
            Library.getBooks().add(book);
        } else {
            Library.getShelfsInLibrary().get(book.shelf).getBooksOnShelf().add(book.shelfPlace, book);
            Library.getBooks().add(book);
        }
    }

    @Override
    public void remove(Book book) {
        boolean check = false;
        if (book == null) {
            throw new IllegalArgumentException("Книга не может быть NULL");
        }
        for (Book book1 : Library.getBooks()) {
            if (book1 == book) {
                check = true;
            }
        }
        if (check == true) {
            Library.getShelfsInLibrary().get(book.shelf).getBooksOnShelf().remove(book);
            Library.getBooks().remove(book);
        } else {
            throw new IllegalArgumentException("Такой книги нет в библиотеке.");
        }
    }

    @Override
    public void openTheLibrary() {
        Library.setStatus(true);
        Library.work();

    }

    @Override
    public void closeTheLibrary() {
        Library.setStatus(false);

    }

}
