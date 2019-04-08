package ru.mail.polis.open.task6;

import java.util.Date;

public class
Book {
    private final int id;
    private final String name;
    private int shelfSpace;
    private final int section;
    private long incomingDate;
    private long dateOfGivingOut;
    private long dateOfReturn;
    private Visitor recipient;

    Book(String name, int section) {
        this.name = name;
        this.section = section;
        this.id = name.hashCode();
        this.incomingDate = 0;
        this.dateOfGivingOut = 0;
        this.dateOfReturn = 0;
    }

    public void setShelfSpace(int shelfSpace) {
        this.shelfSpace = shelfSpace;
    }

    void setAsIncoming() {
        recipient = null;
        this.dateOfReturn = (this.incomingDate == 0) ? this.dateOfReturn : new Date().getTime();
        this.incomingDate = (incomingDate == 0) ? new Date().getTime() : incomingDate;
    }

    void setAsGivenOut(Visitor recipient) {
        this.recipient = recipient;
        this.dateOfGivingOut = new Date().getTime();
    }

    int getId() {
        return id;
    }

    int getSection() {
        return section;
    }

    String getName() {
        return name;
    }

    int getShelfSpace() {
        return shelfSpace;
    }

    Visitor getRecipient() {
        return recipient;
    }

    public long getDateOfGivingOut() {
        return dateOfGivingOut;
    }

    public long getIncomingDate() {
        return incomingDate;
    }

    public long getDateOfReturn() {
        return dateOfReturn;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Book book = (Book) obj;
        return (this.name.equals(book.getName())
                && this.id == book.getId()
                && this.getShelfSpace() == book.getShelfSpace()
                && this.section == book.getSection());
    }
}
