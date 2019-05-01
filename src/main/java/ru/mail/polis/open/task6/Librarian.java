package ru.mail.polis.open.task6;

import java.util.*;

public class Librarian extends ManagingPerson {
    private class BookInfo {
        private static final int DAYS_TO_USE = 15;

        private final Book book;
        private final LibraryClient currentOwner;
        private final GregorianCalendar takeTime;
        private final GregorianCalendar returnTime;

        private BookInfo(LibraryClient currentOwner, Book book) {
            this.book = book;
            this.currentOwner = currentOwner;
            this.takeTime = (GregorianCalendar) Calendar.getInstance();
            this.returnTime = takeTime;
            extend();
        }

        private void extend() {
            returnTime.add(Calendar.DAY_OF_MONTH, DAYS_TO_USE);
        }

        @Override
        public String toString() {
            return book.toString() + currentOwner.getName();
        }
    }

    private static HashMap<String, BookInfo> bookInfos = new HashMap<>();
    private static HashMap<String, Person> librarianDatabase = new HashMap<>();

    private boolean isBusy = false;
    private static int busyLibrarians = 0;
    private static int extendCount = 0;
    private static int getBackCount = 0;
    private static int newBookInfos = 0;

    public Librarian(String name) {
        super(name, librarianDatabase);
    }

    protected Book findBookByFullDescription(int id, String name, ArrayList<String> partitions) {
        Book book = new Book(id, name, partitions);
        if (store.containsKey(book)) {
            return book;
        }
        System.out.println("No such book found! Sorry!");
        return null;
    }

    private <T> boolean condition(T parameterValue, BookSearchParameter parameterType, Book book) {
        switch (parameterType) {
            case BY_ID: return book.getId() == (int) parameterValue;
            case BY_NAME: return book.getName().equals(parameterValue);
            case BY_PARTITION: return book.getPartitions().contains(parameterValue);
            case BY_PARTITIONS: return book.getPartitions().containsAll((Collection<String>) parameterValue);
            default: return false;
        }
    }

    protected  <T> ArrayList<Book> findAllBooksByParameter(
            T parameterValue,
            BookSearchParameter parameterType
    ) {
        if ((parameterType.equals(BookSearchParameter.BY_NAME)
                || parameterType.equals(BookSearchParameter.BY_PARTITION)) && !(parameterValue instanceof String)
                || parameterType.equals(BookSearchParameter.BY_PARTITIONS) && !(parameterValue instanceof ArrayList)
                || parameterType.equals(BookSearchParameter.BY_ID) && !(parameterValue instanceof Integer)) {
            throw  new IllegalArgumentException("The type of parameter is specified incorrectly");
        }
        ArrayList<Book> res = new ArrayList<>();
        for (Book book: store.keySet()) {
            if (condition(parameterValue, parameterType, book) && !res.contains(book)) {
                res.add(book);
            }
        }
        return res;
    }

    protected static Librarian getAFreeLibrarian() {
        busyLibrarians++;
        if (LibraryClient.getBusyClients() != busyLibrarians) {
            throw new IllegalCallerException("This Librarian is just pretending that he's working!");
        }
        for (String librarianName: librarianDatabase.keySet()) {
            Librarian librarian = (Librarian)librarianDatabase.get(librarianName);
            if (!librarian.isBusy) {
                librarian.isBusy = true;
                return librarian;
            }
        }
        System.out.println("All librarians are busy right now!");
        return null;
    }

    public String remindClientAboutBookReturnTime(LibraryClient client, Book book) {
        String key = book.toString() + client.getName();
        if (!bookInfos.containsKey(key)) {
            throw new IllegalCallerException("This book is not owned by this client currently");
        }
        return client.getName()
                + " must return this book: "
                + LINE_SEPARATOR
                + book.toString()
                + LINE_SEPARATOR
                + "until "
                + bookInfos.get(key).returnTime.getTime();
    }

    protected void becomeFree() {
        busyLibrarians--;
        if (busyLibrarians != LibraryClient.getBusyClients()) {
            throw new IllegalCallerException("Go work you lazy man!");
        }
        isBusy = false;
    }

    protected void extendBook(Book book, LibraryClient client) {
        extendCount++;
        String key = book.toString() + client.getName();
        if (extendCount != LibraryClient.getExtendCount() || !bookInfos.containsKey(key)) {
            throw new IllegalCallerException("You're extending an unowned book!");
        }
        bookInfos.get(key).extend();
    }

    public String getBookInfos() {
        StringBuilder res = new StringBuilder();
        for (String key: bookInfos.keySet()) {
            BookInfo info = bookInfos.get(key);
            res
                    .append(info.book.toString())
                    .append(LINE_SEPARATOR)
                    .append("Currently used by ")
                    .append(info.currentOwner.getName())
                    .append(LINE_SEPARATOR)
                    .append("Will be used by them until ")
                    .append(info.returnTime.getTime());
        }
        return res.toString();
    }

    protected void getBookBackFromClient(Book book, LibraryClient client) {
        getBackCount++;
        String key = book.toString() + client.getName();
        if (!bookInfos.containsKey(key) || getBackCount != LibraryClient.getPutsAmount()) {
            throw new IllegalCallerException();
        }
        bookInfos.get(key);
    }

    protected void newBookInfo(Book book, LibraryClient client) {
        newBookInfos++;
        if (newBookInfos != LibraryClient.getRemovesAmount()) {
            throw new IllegalCallerException();
        }
        bookInfos.put(book.toString() + client.getName(), new BookInfo(client, book));
    }
}
