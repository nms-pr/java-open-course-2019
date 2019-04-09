package ru.mail.polis.open.task6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Library {

    private boolean isOpened;
    private final List<BookCard> depository;
    private final List<PersonCard> personCardList;
    private static final String CLOSED_TEXT = "Библиотека закрыта";
    private static final String OPENED_TEXT = "Библиотека открыта";

    public Library(int librarySize, int guestsSize) {
        isOpened = true;
        depository = new ArrayList<>(librarySize);
        personCardList = new ArrayList<>(guestsSize);
    }

    public Librarian getLibrarian() {
        if (isOpened) {
            return new LibraryStaff();
        } else {
            throw new ClosedLibraryExeption(CLOSED_TEXT);
        }
    }

    public Manager getManager() {
        return new LibraryStaff();
    }

    public String getStatus() {
        if (isOpened) {
            return OPENED_TEXT;
        } else {
            return CLOSED_TEXT;
        }
    }

    private class LibraryStaff implements Librarian, Manager {

        private Person guest;
        private static final String GREETINGS = "Здравствуйте, ";
        private static final String BUSY = "Я здесь одна!";
        private static final String BYE = "До свидания!";
        private static final String DELIMITER = ", ";
        private static final String IN_STOCK = "В наличии: ";
        private static final String TYPE = "Жанр: ";
        private static final String ID = "ID: ";


        @Override
        public void addNewBooks(BookCard... bookCards) {
            depository.addAll(Arrays.asList(bookCards));
        }

        @Override
        public void openLibrary() {
            isOpened = true;
        }

        @Override
        public void closeLibrary() {
            isOpened = false;
        }

        @Override
        public void deleteBooks(int... idArray) {
            for (int oneId : idArray) {
                depository.remove(findById(oneId));
            }
        }

        @Override
        public List<BookCard> getDepository() {
            return depository;
        }

        @Override
        public BookGiven giveBook(int id) {

            BookCard card = findById(id);
            if (card == null) {
                throw new IllegalArgumentException("There is not this book");
            } else {
                return card.getBook(guest);
            }
        }

        @Override
        public BookGiven giveBook(String name, String author) {
            for (BookCard card : depository) {
                if (card.author.equals(author)
                    && card.name.equals(name)) {
                    return card.getBook(guest);
                }
            }
            return null;
        }

        @Override
        public List<BookGiven> giveBooks(int... idArray) {
            List<BookGiven> giveList = new ArrayList<>(idArray.length);
            BookCard current;
            BookGiven given;
            for (int id : idArray) {
                current = findById(id);
                if (current != null) {
                    given = current.getBook(guest);
                    if (given != null) {
                        giveList.add(current.getBook(guest));
                    }
                }
            }
            return giveList;
        }

        @Override
        public String[] getAvailableBooks() {
            return listBooksToString(depository);
        }

        @Override
        public String sayHello(Person guest) {
            if (this.guest != null) {
                return BUSY;
            } else if (isOpened) {
                this.guest = guest;
                return GREETINGS + guest.name;
            } else {
                throw new ClosedLibraryExeption(CLOSED_TEXT);
            }
        }

        @Override
        public PersonCard getPersonCard() {
            for (PersonCard card : personCardList) {
                if (guest.equals(card)) {
                    return card;
                }
            }
            PersonCard newGuest = new PersonCard(guest);
            personCardList.add(newGuest);
            return newGuest;
        }

        @Override
        public String sayGoodbye() {
            this.guest = null;
            return BYE;
        }

        private String[] listBooksToString(List<BookCard> booksList) {
            String[] arrayBooks = new String[booksList.size()];
            StringBuilder builder;
            BookCard book;
            for (int index = 0; index < arrayBooks.length; index++) {
                builder = new StringBuilder();
                book = booksList.get(index);
                builder.append(book.author);
                builder.append(DELIMITER);
                builder.append('"').append(book.name).append('"');
                builder.append(DELIMITER);
                builder.append(TYPE).append(book.section);
                builder.append(DELIMITER);
                builder.append(ID).append(book.id);
                builder.append(DELIMITER);
                builder.append(IN_STOCK).append(book.getBooksCounter());
                arrayBooks[index] = builder.toString();
            }
            return arrayBooks;
        }

        public void takeBooks(Iterable<BookGiven> books) {
            BookCard currentBook = null;
            for (BookGiven book : books) {
                for (BookCard card : depository) {
                    if (book.equals(card)) {
                        currentBook = card;
                        break;
                    }
                }
                if (currentBook != null) {
                    currentBook.returnBooks(1);
                    currentBook = null;
                }
            }
        }

        private BookCard findById(int id) {
            for (BookCard card : depository) {
                if (card.id == id) {
                    return card;
                }
            }
            return null;
        }
    }
}
