package ru.mail.polis.open.task6.implementation.Book;

import ru.mail.polis.open.task6.implementation.people.Customer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class BookInfo {

    private int id;
    private int inStock;
    private Set<Integer> shelfPlaces;
    private List<HistoryEntry> history;

    public BookInfo(int id, int inStock, Set<Integer> shelfPlaces) {

        if (inStock != shelfPlaces.size()) {
            throw new IllegalArgumentException("Quantity of books in stock should correspond to quantity of shelfPlaces");
        }
        this.id = id;
        this.inStock = inStock;
        this.shelfPlaces = shelfPlaces;
        this.history = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public int getInStock() {
        return inStock;
    }

    public Set<Integer> getShelfPlaces() {
        return Set.copyOf(shelfPlaces);
    }

    public void onNewInstanceAdded(int shelfPlace) {

        if (shelfPlaces.contains(shelfPlace)) {
            throw new IllegalArgumentException("Thi book is already present at this place");
        }
        shelfPlaces.add(shelfPlace);
        inStock++;
    }

    public List<HistoryEntry> getHistory() {
        return List.copyOf(history);
    }

    public void addToHistory(Customer customer, Date beginDate, Date endDate) {

        if (beginDate.compareTo(endDate) >= 0) {
            throw new IllegalArgumentException("Begin date cannot be greater than end date");
        }

        history.add(new HistoryEntry(
            customer,
            beginDate,
            endDate
        ));
    }
}
