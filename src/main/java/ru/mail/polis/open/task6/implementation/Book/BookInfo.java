package ru.mail.polis.open.task6.implementation.Book;

import ru.mail.polis.open.task6.implementation.people.Customer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class BookInfo {

    private int id;

    private int total;

    private int inStock;
    private Set<Integer> shelfPlaces;
    private List<HistoryEntry> history;
    public BookInfo(int id, int total, Set<Integer> shelfPlaces) {

        if (total <= 0) {
            throw new IllegalArgumentException("There must be at least one instance of book");
        }

        if (total != shelfPlaces.size()) {
            throw new IllegalArgumentException("Quantity of books in stock should correspond to quantity of shelfPlaces");
        }

        this.id = id;
        this.total = total;
        this.inStock = total;
        this.shelfPlaces = new TreeSet<>(shelfPlaces);
        this.history = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public int getInStock() {
        return inStock;
    }

    public int getTotal() {
        return total;
    }

    public Set<Integer> getShelfPlaces() {
        return Set.copyOf(shelfPlaces);
    }

    public void onNewInstanceAdded(int shelfPlace) {

        if (shelfPlaces.contains(shelfPlace)) {
            throw new IllegalArgumentException("This book is already present at this place");
        }
        shelfPlaces.add(shelfPlace);
        inStock++;
        total++;
    }

    public void onInstanceRemoved(int shelfPlace) {

        if(!shelfPlaces.contains(shelfPlace)) {
            throw new IllegalArgumentException("No such book present at this place");
        }

        shelfPlaces.remove(shelfPlace);
        total--;
        inStock--;
    }

    public void onInstanceLent(int shelfPlace) {

        if (!shelfPlaces.contains(shelfPlace)) {
            throw new IllegalArgumentException("No such book present at this place");
        }
        shelfPlaces.remove(shelfPlace);
        inStock--;
    }


    public void onInstanceReturned(int shelfPlace) {
        if (shelfPlaces.contains(shelfPlace)) {
            throw new IllegalArgumentException("This book is already present at this place");
        }

        if (inStock + 1 > total) {
            throw new IllegalArgumentException("This book was not lent yet, but already returned");
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
