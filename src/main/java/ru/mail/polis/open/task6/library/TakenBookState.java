package ru.mail.polis.open.task6.library;

import java.util.Date;

public class TakenBookState {
    private String bookName;
    private String customerName;
    private Date date;

    public TakenBookState(String bookName, String customerName, Date date) {
        this.bookName = bookName;
        this.customerName = customerName;
        this.date = date;
    }
}
