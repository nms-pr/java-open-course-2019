package ru.mail.polis.open.task6;

import java.time.LocalDateTime;
import java.time.Month;

public class LibrarianImpl extends Human implements Librarian {
    public LibrarianImpl(String name,
                         String surname,
                         String patronymic,
                         byte age) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.age = age;
    }

    @Override
    public void sayHello() {
        System.out.println("Добро пожаловать в библиотеку!");
    }

    @Override
    public void sayGoodbye() {
        System.out.println("До свидания! Ждем вас снова!");
    }

    @Override
    public Book giveBook(String name, CustomerImpl customer) {
        if (Library.getStatus() == true) {
            if (name == null) {
                throw new IllegalArgumentException("Имя не может быть NULL");
            }
            Book book = findBook(name);

            updateInfo(book, customer);
            return book;
        } else {
            throw new IllegalArgumentException("Простите, библиотека закрыта.");
        }
    }

    @Override
    public Book giveBook(long id, CustomerImpl customer) {
        if (Library.getStatus() == true) {
            if (id == 0 || id < 1000005) {
                throw new IllegalArgumentException("Неверный идентификатор");
            }
            Book book = findBook(id);
            updateInfo(book, customer);
            return book;
        } else {
            throw new IllegalArgumentException("Простите, библиотека закрыта.");
        }
    }

    @Override
    public void putBook(Book book, CustomerImpl customer) {
        if (Library.getStatus() == true) {
            if (book == null) {
                throw new IllegalArgumentException("Книга не может быть NULL");
            }
            book.setTimeOfReceiving(null);
            book.setTimeOfReturn(null);
            book.setUserWhoTakes(null);
            if (Library.placeIsBusy(book.shelf,
                    book.shelfPlace)) {
                Library.getBusyBooks().remove(book);

                book = findPlaceForBook(book);

                Library.getBooks().add(book);
            } else {
                Library.getBusyBooks().remove(book);

                Library.getShelfsInLibrary().get(book.shelf).getBooksOnShelf().add(book.shelfPlace, book);

                Library.getBooks().add(book);
            }
        } else {
            throw new IllegalArgumentException("Простите, библиотека закрыта.");
        }
    }

    Book findBook(String name) {
        Book book = null;
        if (name == null) {
            throw new IllegalArgumentException("Название книги не может быть NULL");
        }
        for (Book book1 : Library.getBooks()) {
            if (book1.name == name) {
                book = book1;
                Library.getShelfsInLibrary().get(book1.shelf).getBooksOnShelf().add(book1.shelfPlace, null);
                break;
            }
        }
        if (book != null) {
            return book;
        } else {
            throw new IllegalArgumentException("Такая книга не найдена");
        }
    }

    Book findBook(long id) {
        Book book = null;
        if (id == 0 || id <= 1000005) {
            throw new IllegalArgumentException("Неверный идентификатор");
        }
        for (Book book1 : Library.getBooks()) {
            if (book1.id == id) {
                book = book1;
                Library.getShelfsInLibrary().get(book1.shelf).getBooksOnShelf().add(book1.shelfPlace, null);
                break;
            }
        }
        if (book != null) {
            return book;
        } else {
            throw new IllegalArgumentException("Такая книга не найдена");
        }
    }

    public void updateInfo(Book book, CustomerImpl customer) {
        book.setTimeOfReceiving(setTimeWhenBookTake());
        book.setTimeOfReturn(setTimeReturnBook());
        book.setUserWhoTakes(customer);
        Library.getBusyBooks().add(book);
        Library.getBooks().remove(book);
        if (Library.containCustomer(customer)) {
            customer.getTakenBooks().add(book);
        } else {
            Library.getCustomers().add(customer);
            customer.getTakenBooks().add(book);

        }
    }

    private LocalDateTime setTimeWhenBookTake() {
        return LocalDateTime.now();
    }

    private LocalDateTime setTimeReturnBook() {
        LocalDateTime time = LocalDateTime.now();
        if (Month.of(12) == Month.DECEMBER) {
            return LocalDateTime.of(time.getYear() + 1, Month.JANUARY, 28, time.getHour(), time.getMinute()
            );
        } else {
            return LocalDateTime.of(time.getYear(),
                    Month.of(time.getMonthValue() + 1), 28,
                    time.getHour(),
                    time.getMinute()
            );
        }
    }

    Book findPlaceForBook(Book book) {
        for (Shelf shelf : Library.getShelfsInLibrary()) {
            for (int i = 0; i < shelf.getQuality(); i++) {
                if (shelf.getBooksOnShelf().get(i + 1) == null) {
                    shelf.getBooksOnShelf().add(i + 1, book);
                    book.setShelfPlace(i + 2);
                    book.setShelf(shelf.getShelf());
                    return book;
                }
            }
        }
        throw new IllegalArgumentException("В библиотеке нет места для этой книги.");
    }

    void usersTakenBooks() {
        for (Book book : Library.getBusyBooks()) {
            System.out.println("Имя " + book.getUserWhoTakes().getName()
                    + " Фамилия " + book.getUserWhoTakes().getSurname()
                    + " Отчество " + book.getUserWhoTakes().getPatronymic()
                    + " взял(а) книгу(и) :");
            book.getUserWhoTakes().getTakenBooks();
        }
    }


    public void remindToReturn(CustomerImpl customer) {
        System.out.println("Уважаемый " + customer.getSurname()
                + " " + customer.getName()
                + " " + customer.getPatronymic() + ",\n"
                + "вы взяли эти книги : \n");

        for (Book book : customer.getTakenBooks()) {
            System.out.println(book.toString());
            System.out.println("Просим вас не забыть их вернуть к : "
                    + book.getTimeOfReturn());
        }
    }

}