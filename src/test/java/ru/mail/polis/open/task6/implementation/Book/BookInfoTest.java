package ru.mail.polis.open.task6.implementation.Book;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.mail.polis.open.task6.implementation.people.Customer;
import ru.mail.polis.open.task6.implementation.people.Person;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BookInfoTest {

    private static DateFormat dateFormat;

    @BeforeAll
    static void initDateParser() {

        dateFormat = new SimpleDateFormat("dd/mm/yyyy hh:mm");
    }

    @Test
    void constructor_ThrowsOnIllegalArguments() {

        assertThrows(IllegalArgumentException.class, () -> new BookInfo(1, 3, Set.of(1, 5)));
        assertThrows(IllegalArgumentException.class, () -> new BookInfo(1, 1, Set.of(1, 5)));
        assertThrows(IllegalArgumentException.class, () -> new BookInfo(1, 0, Set.of()));
        assertThrows(IllegalArgumentException.class, () -> new BookInfo(1, -5, Set.of()));
    }

    @Test
    void addToHistory_WorksCorrectly() {

        BookInfo bookInfo = new BookInfo(1, 3, Set.of(1, 3, 5));

        assertEquals(0, bookInfo.getHistory().size());

        try {
            Customer customer = new Customer(new Person("first", "last"), null);
            Date beginDate = dateFormat.parse("01/01/2019 12:00");
            Date endDate = dateFormat.parse("08/01/2019 12:00");

            bookInfo.addToHistory(customer, beginDate, endDate);

            assertEquals(1, bookInfo.getHistory().size());
            assertEquals(
                List.of(new HistoryEntry(customer, beginDate, endDate)),
                bookInfo.getHistory()
            );

        } catch (ParseException e) {
            fail(e.getMessage());
        }
    }


    @Test
    void addToHistory_ThrowsOnEndDateIsLessThanBeginDate() {

        BookInfo bookInfo = new BookInfo(1, 3, Set.of(1, 3, 5));

        try {
            Customer customer = new Customer(new Person("first", "last"), null);
            Date beginDate = dateFormat.parse("08/01/2019 12:00");
            Date endDate = dateFormat.parse("01/01/2019 12:00");

            assertThrows(IllegalArgumentException.class, ()-> bookInfo.addToHistory(customer, beginDate, endDate));

        } catch (ParseException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void onNewInstanceAdded_WorksCorrectly() {

        Set<Integer> initialShelfPlaces = new HashSet<>();
        initialShelfPlaces.add(1);
        initialShelfPlaces.add(3);
        initialShelfPlaces.add(5);
        BookInfo bookInfo = new BookInfo(1, 3, initialShelfPlaces);

        bookInfo.onNewInstanceAdded(2);

        assertEquals(Set.of(1, 3, 5, 2), bookInfo.getShelfPlaces());
        assertEquals(4, bookInfo.getInStock());
        assertEquals(4, bookInfo.getTotal());
    }

    @Test
    void onNewInstanceAdded_ThrowsWhenBookIsAlreadyPresentInPlace() {

        Set<Integer> initialShelfPlaces = new HashSet<>();
        initialShelfPlaces.add(1);
        initialShelfPlaces.add(3);
        initialShelfPlaces.add(5);

        BookInfo bookInfo = new BookInfo(1, 3, initialShelfPlaces);

        assertThrows(IllegalArgumentException.class, () -> bookInfo.onNewInstanceAdded(3));
    }

    @Test
    void onInstanceRemoved_WorksCorrectly() {

        Set<Integer> initialShelfPlaces = new HashSet<>();
        initialShelfPlaces.add(1);
        initialShelfPlaces.add(3);
        initialShelfPlaces.add(5);
        BookInfo bookInfo = new BookInfo(1, 3, initialShelfPlaces);

        bookInfo.onInstanceRemoved(3);

        assertEquals(Set.of(1, 5), bookInfo.getShelfPlaces());
        assertEquals(2, bookInfo.getInStock());
        assertEquals(2, bookInfo.getTotal());
    }

    @Test
    void onInstanceRemoved_ThrowsWhenBookIsAbsentAtPlace() {

        Set<Integer> initialShelfPlaces = new HashSet<>();
        initialShelfPlaces.add(1);
        initialShelfPlaces.add(3);
        initialShelfPlaces.add(5);

        BookInfo bookInfo = new BookInfo(1, 3, initialShelfPlaces);

        assertThrows(IllegalArgumentException.class, () -> bookInfo.onInstanceRemoved(2));
    }

    @Test
    void onInstanceLent_WorksCorrectly() {

        Set<Integer> initialShelfPlaces = new HashSet<>();
        initialShelfPlaces.add(1);
        initialShelfPlaces.add(3);
        initialShelfPlaces.add(5);
        BookInfo bookInfo = new BookInfo(1, 3, initialShelfPlaces);

        bookInfo.onInstanceLent(3);

        assertEquals(Set.of(1, 5), bookInfo.getShelfPlaces());
        assertEquals(2, bookInfo.getInStock());
        assertEquals(3, bookInfo.getTotal());
    }

    @Test
    void onInstanceLent_ThrowsWhenBookIAbsentAtPlace() {

        Set<Integer> initialShelfPlaces = new HashSet<>();
        initialShelfPlaces.add(1);
        initialShelfPlaces.add(3);
        initialShelfPlaces.add(5);

        BookInfo bookInfo = new BookInfo(1, 3, initialShelfPlaces);

        assertThrows(IllegalArgumentException.class, () -> bookInfo.onInstanceLent(2));
    }

    @Test
    void onInstanceReturned_WorksCorrectly() {

        Set<Integer> initialShelfPlaces = new HashSet<>();
        initialShelfPlaces.add(1);
        initialShelfPlaces.add(3);
        initialShelfPlaces.add(5);
        BookInfo bookInfo = new BookInfo(1, 3, initialShelfPlaces);

        bookInfo.onInstanceLent(3);
        bookInfo.onInstanceReturned(3);

        assertEquals(Set.of(1, 3, 5), bookInfo.getShelfPlaces());
        assertEquals(3, bookInfo.getInStock());
        assertEquals(3, bookInfo.getTotal());
    }

    @Test
    void onInstanceReturned_ThrowsWhenBookIsAlreadyPresentInPlace() {

        Set<Integer> initialShelfPlaces = new HashSet<>();
        initialShelfPlaces.add(1);
        initialShelfPlaces.add(3);
        initialShelfPlaces.add(5);

        BookInfo bookInfo = new BookInfo(1, 3, initialShelfPlaces);

        assertThrows(IllegalArgumentException.class, () -> bookInfo.onInstanceReturned(3));
    }

    @Test
    void onInstanceReturned_ThrowsWhenBookWasNotTakenButReturned() {

        Set<Integer> initialShelfPlaces = new HashSet<>();
        initialShelfPlaces.add(1);
        initialShelfPlaces.add(3);
        initialShelfPlaces.add(5);

        BookInfo bookInfo = new BookInfo(1, 3, initialShelfPlaces);

        assertThrows(IllegalArgumentException.class, () -> bookInfo.onInstanceReturned(4));
    }
}
