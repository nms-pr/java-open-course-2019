package ru.mail.polis.open.task6;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class LibraryOneDayTest {
    private static Book book = new Book(1, 2, "Карта дней", "Фантастика");
    private static Book book1 = new Book(5, 6, "Пена", "Фантастика");
    private static Book book2 = new Book(7, 2, "Кукушка", "Сказки");
    private static CustomerImpl customer = new CustomerImpl("Сергей", "Кравченко", "Петрович", (byte) 30);

    @BeforeAll
    static void setPositions() {
        Library.manager.openTheLibrary();
        assertEquals(true, Library.getStatus());
    }

    @Test
    void work() {
        assertEquals(9, Library.getBooks().size());
        Library.librarian.giveBook("Виноваты звёзды", customer); //посетитель решил взять книгу
        assertEquals(true, Library.containCustomer(customer));
        //посетителя не было в картотеке библиотеки, поэтому его туда добавили
        assertEquals(8, Library.getBooks().size());
        //книг стало на одну меньше (тк одну взяли)
        assertEquals(1, Library.getBusyBooks().size());
        //одна книга на руках
        assertThrows(
                IllegalArgumentException.class,
            () -> Library.librarian.giveBook("Виноваты звёзды", Library.getCustomers().get(2)));
        //другой посетитель решил взять ту же книгу — ему не дали, тк её нет
        Library.manager.add(book1);
        //менеджер решил добавить новую книгу
        assertEquals(9, Library.getBooks().size());
        //теперь книг на одну больше, но одна по-прежнему на руках
        Library.librarian.putBook(customer.takenBooks.get(0), customer);
        //посетитель решил вернуть книгу
        assertEquals(10, Library.getBooks().size());
        //теперь книг 10
        assertEquals(0, Library.getBusyBooks().size());
        //ни одна книга не на руках
        assertThrows(
                IllegalArgumentException.class,
            () -> Library.librarian.findBook("Кукушка"));
        //посетитель попросил библиотекаря посмотреть, есть ли у них книга с таким названием (её нет)
        Library.manager.add(book2);
        //а менеджер как раз решил добавить эту книгу
        assertEquals(book2, Library.librarian.findBook("Кукушка"));
        //посетитель снова просит поискать получше, и теперь эта книга есть в библиотеке
        Library.manager.closeTheLibrary();
        assertThrows(
                IllegalArgumentException.class,
            () -> Library.librarian.giveBook("Виноваты звёзды", Library.getCustomers().get(2)));
        //покупатель решил взять книжку, но библиотека уже закрыта

    }

    @AfterAll
    static void clean() {
        Library.setStatus(false);
        Library.setCounter();
        Library.getCustomers().clear();
        Library.getBusyBooks().clear();
        Library.getShelfsInLibrary().clear();
        Library.getBooks().clear();
    }

}