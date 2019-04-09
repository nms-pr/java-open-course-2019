package ru.mail.polis.open.task6;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class Library {
    private ManagerImpl manager;
    private LibrarianImpl librarian;
    private boolean isOpened = false; // библиотека открыта?
    private HashMap<String, ArrayList<Book>> books = new HashMap<>(); // Книги в библиотеке
    private ArrayList<Book> takenBooks = new ArrayList<>(); // Книги, отданные людям в пользование
    private HashMap<Visitor, ArrayList<Book>> history = new HashMap<>(); // Кто, что и когда брал

    public boolean isOpened() {
        return isOpened;
    }

    public ManagerImpl getManager() {
        return manager;
    }

    public void setManager(ManagerImpl manager) {
        this.manager = manager;
    }

    public LibrarianImpl getLibrarian() {
        return librarian;
    }

    public void setLibrarian(LibrarianImpl librarian) {
        this.librarian = librarian;
    }

    public class LibrarianImpl extends Person implements Librarian {
        public LibrarianImpl(String surname,
                             String name,
                             String patronymic,
                             String address,
                             String number,
                             int age,
                             char sex) {
            super(surname, name, patronymic, address, number, age, sex);
        }

        // Метод takeBook() выдаёт книгу из библиотеки
        @Override
        public Book takeBook(String name, Visitor visitor) {
            ArrayList<Book> bookList;
            if (books.containsKey(name)) {
                bookList = books.get(name); // Получаем список книг с таким названием
            } else {
                throw new IllegalArgumentException("В библиотеке нет книги с таким названием!");
            }

            Book book = bookList.get(0); // Берём одну из книг с таким названием
            bookList.remove(0); // Удаляем взятую книгу

            if (bookList.size() > 0) {
                books.put(name, bookList); // Помещаем обновлённый список книг с таким названием в библиотеку
            } else {
                books.remove(name);
            }

            book.issueTime = new GregorianCalendar(); // Записали для книги время выдачи
            book.returnTime = (Calendar) book.issueTime.clone(); // Клонируем дату выдачи в дату возврата
            book.returnTime.add(Calendar.DATE, 7); // Склонированную дата выдачи увеличиваем на 7 дней
            book.whoTookBook = visitor;

            takenBooks.add(book); // Взятую книгу кладём в список отданных книг
            history.put(visitor, new ArrayList<>() {
                {
                    add(book);
                }
            });

            return book; // Возвращает запрошенную книгу
        }

        // Метод putBook() возвращает книгу в библиотеку
        @Override
        public void putBook(Book book) {
            if (takenBooks.contains(book)) {
                takenBooks.remove(book);

                // Обновляем историю
                ArrayList<Book> historyOfPerson = history.get(book.whoTookBook);
                historyOfPerson.remove(book);
                book.returnTime = new GregorianCalendar(); // Записали фактическую дату возврата
                historyOfPerson.add(book);
                history.put(book.whoTookBook, historyOfPerson);

                book.issueTime = null;
                book.returnTime = null;
                book.whoTookBook = null;

                if (books.containsKey(book.name)) { // Если такая книга уже есть, то добавляем к списку таких же книг
                    books.get(book.name).add(book);
                } else { // Иначе если такой книги ещё не было, то добавляем и создаём ArrayList для книг с таким именем
                    books.put(book.name, new ArrayList<>() {
                        {
                            add(book);
                        }
                    });
                }
            } else {
                throw new IllegalArgumentException("У нас не было такой книги!");
            }
        }

        // Метод getAvailableBooks() возвращаёт список доступных книг
        @Override
        public HashMap<String, ArrayList<Book>> getAvailableBooks() {
            HashMap<String, ArrayList<Book>> map = new HashMap<>(books);
            return map;
        }

        // Метод getHistory() возвращает историю действий: кто, что и когда брал
        @Override
        public HashMap<Visitor, ArrayList<Book>> getHistory() {
            HashMap<Visitor, ArrayList<Book>> map = new HashMap<>(history);
            return map;
        }

        @Override
        public void notifyVisitors() {
            Calendar currentDate = new GregorianCalendar();
            for (Book book : takenBooks) {
                if (currentDate.after(book.returnTime)) {
                    book.whoTookBook.notifyAboutBook(book);
                }
            }
        }
    }

    public class ManagerImpl extends Person implements Manager {
        public ManagerImpl(String surname,
                           String name,
                           String patronymic,
                           String address,
                           String number,
                           int age,
                           char sex) {
            super(surname, name, patronymic, address, number, age, sex);
        }

        // Метод delBook() удаляет все книги с названием name
        @Override
        public void delBook(String name) {
            if (books.containsKey(name)) {
                ArrayList<Book> bookList = books.get(name); // Получаем список книг с таким названием
                books.remove(name);
            }
        }

        // Метод delBook() удаляет конкретно указанную книгу
        @Override
        public void delBook(Book book) {
            if (books.containsKey(book.name)) {
                ArrayList<Book> bookList = books.get(book.name);
                bookList.remove(book);
                if (bookList.size() > 0) {
                    books.put(book.name, bookList);
                } else {
                    books.remove(book.name);
                }
            } else {
                throw new IllegalArgumentException("В библиотеке нет книги с таким названием!");
            }
        }

        // Метод giveBook() добавляет новую книгу в библиотеку
        @Override
        public void addBook(Book book) {
            if (books.containsKey(book.name)) { // Если такая книга уже есть, то добавляем к списку таких же книг
                books.get(book.name).add(book);
            } else { // Иначе если такой книги ещё не было, то добавляем и создаём ArrayList для книг с таким именем
                books.put(book.name, new ArrayList<>() {
                    {
                        add(book);
                    }
                });
            }
        }

        public void openLibrary() {
            isOpened = true;
        }

        public void closeLibrary() {
            isOpened = false;
        }
    }
}
