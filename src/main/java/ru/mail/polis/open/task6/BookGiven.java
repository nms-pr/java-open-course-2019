package ru.mail.polis.open.task6;

import java.time.LocalDate;

public class BookGiven extends BookInfo {

    public final Person reader;
    public final LocalDate dateIssue;
    public final LocalDate returnDate;

    public BookGiven(BookInfo info, Person reader, int termInDays) {
        super(info.name, info.author, info.section, info.id);
        this.reader = reader;
        this.dateIssue = LocalDate.now();
        returnDate = dateIssue.plusDays(termInDays);
    }
}
