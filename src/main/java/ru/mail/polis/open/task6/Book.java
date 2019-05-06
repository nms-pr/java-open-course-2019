package ru.mail.polis.open.task6;

import java.util.Calendar;

public class Book {
    private int id;
    private String title;
    private int shelfPlace;
    private int section;
    private Calendar dateOfGiving;
    private Calendar dateOfReturn;
    private Customer customerInfo;
    private boolean availability;

    Book(int id, String title, int section, int shelfPlace) {
        this.shelfPlace = shelfPlace;
        this.title = title;
        this.id = id;
        this.section = section;
        this.availability = true;
    }

    public boolean isAvailable() {
        return availability;
    }

    public void setAvailabilityFalse() {
        availability = false;
    }

    public void setAvailabilityTrue() {
        availability = true;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getSection() {
        return section;
    }

    public int getShelfPlace() {
        return shelfPlace;
    }

    public void setDateOfGiving(Calendar currentDate) {
        this.dateOfGiving = currentDate;
    }

    public void setDateOfReturn(Calendar currentDate) {
        this.dateOfReturn = currentDate;
    }

    public Calendar getDateOfGiving() {
        return dateOfGiving;
    }

    public Calendar getDateOfReturn() {
        return dateOfReturn;
    }

    public void setCustomerInfo(Customer customer) {
        this.customerInfo = customer;
    }

    public Customer getCustomerInfo() {
        return customerInfo;
    }
}