package ru.mail.polis.open.task6;

import java.time.LocalDateTime;
import java.time.Month;

public class Librarian extends Person implements ILibrarian {

    public Librarian(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    @Override
    public void sayHello() {
        System.out.println("Здравствуйте! Какую книгу хотите взять почитать?");
    }

    @Override
    public void sayGoodbye() {
        System.out.println("Всего доброго! Приходите еще!");
    }

    @Override
    public Book giveBook(String bookTitle, Visitor visitor) {
        if (Library.getOpenOrClosed() == true) {
            if (name == null) {
                throw new IllegalArgumentException("Имя не может быть null");
            }
            Book book = findBook(bookTitle);
            recordingInReadingMagazine(book, visitor);
            return book;
        } else {
            throw new IllegalArgumentException("Библиотека закрыта.");
        }
    }

    @Override
    public Book giveBook(long id, Visitor visitor) {
        if (Library.getOpenOrClosed() == true) {
            if (id == 0 || id < 20) {
                throw new IllegalArgumentException("Неверный идентификатор");
            }
            Book book = findBook(id);
            recordingInReadingMagazine(book, visitor);
            return book;
        } else {
            throw new IllegalArgumentException("Библиотека закрыта");
        }
    }

    @Override
    public void putBook(Book book, Visitor visitor) {
        if (Library.getOpenOrClosed() == true) {
            if (book == null) {
                throw new IllegalArgumentException("Книга не может быть null");
            }
            book.setBookReceiptTime(null);
            book.setBookReturnTime(null);
            book.setWhoseBook(null);
            if (Library.placeIsBusy(book.numberBookShelf)) {
                Library.getBusyBooks().remove(book);
                book = findPlaceForBook(book);
                Library.getBookList().add(book);
            } else {
                Library.getBusyBooks().remove(book);
                Library.getBookShelfList().get(book.numberBookShelf).getBooksOnShelf().add(book.numberBookShelf, book);
                Library.getBookList().add(book);
            }
        } else {
            throw new IllegalArgumentException("Библиотека закрыта");
        }
    }

    Book findBook(String bookTitle) {
        Book book = null;
        if (bookTitle == null) {
            throw new IllegalArgumentException("Название книги не может быть null");
        }
        for (Book currentBook : Library.getBookList()) {
            if (currentBook.bookTitle == bookTitle) {
                book = currentBook;
                Library.getBookShelfList().get(book.numberBookShelf).getBooksOnShelf().add(book.numberBookShelf,
                    null);
                break;
            }
        }
        if (book != null) {
            return book;
        } else {
            throw new IllegalArgumentException("Книга не найдена");
        }
    }

    Book findBook(long id) {
        Book book = null;
        if (id == 0 || id < 20) {
            throw new IllegalArgumentException("Неверный идентификатор");
        }
        for (Book currentBook : Library.getBookList()) {
            if (currentBook.id == id) {
                book = currentBook;
                Library.getBookShelfList().get(book.numberBookShelf).getBooksOnShelf().add(book.numberBookShelf,
                    null);
                break;
            }
        }
        if (book != null) {
            return book;
        } else {
            throw new IllegalArgumentException("Книга не найдена");
        }
    }

    public void recordingInReadingMagazine(Book book, Visitor visitor) {
        book.setBookReceiptTime(setTimeBookTake());
        book.setBookReturnTime(setTimeBookReturn());
        book.setWhoseBook(visitor);
        Library.getBusyBooks().add(book);
        Library.getBookList().remove(book);
        if (Library.visitor(visitor)) {
            visitor.takenBooks.add(book);
        } else {
            Library.getVisitorList().add(visitor);
            visitor.takenBooks.add(book);
        }
    }

    private LocalDateTime setTimeBookTake() {
        return LocalDateTime.now();
    }

    private LocalDateTime setTimeBookReturn() {
        LocalDateTime time = LocalDateTime.now();
        if (Month.of(12) == Month.DECEMBER) {
            return LocalDateTime.of(time.getYear() + 1, Month.JANUARY, 28, time.getHour(),
                time.getMinute());
        } else {
            return LocalDateTime.of(time.getYear(), Month.of(time.getMonthValue() + 1), 28,
                time.getHour(), time.getMinute());
        }
    }

    Book findPlaceForBook(Book book) {
        for (BookShelf bookShelf : Library.getBookShelfList()) {
            for (int i = 0; i < bookShelf.getPlacesOnTheShelf(); i++) {
                if (bookShelf.getBooksOnShelf().get(i + 1) == null) {
                    bookShelf.getBooksOnShelf().add(i + 1, book);
                    book.setNumberBookShelf(i + 2);
                    book.setNumberBookShelf(bookShelf.getNumberBookShelf());
                    return book;
                }
            }
        }
        throw new IllegalArgumentException("В библиотеке нет места для этой книг");
    }

    public void remindToReturn(Visitor visitor) {
        System.out.println("Уважаемый " + visitor.getName() + " " + visitor.getSurname() + ", вы взяли эти книги: \n");
        for (Book book : visitor.getTakenBooks()) {
            System.out.println(book.toString());
            System.out.println("Не забудьте вернуть к : " + book.getBookReturnTime());
        }
    }
}
