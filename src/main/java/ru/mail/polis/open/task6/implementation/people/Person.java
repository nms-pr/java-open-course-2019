package ru.mail.polis.open.task6.implementation.people;

public class Person {

    private String firstName;
    private String lastName;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    protected Person() {}

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
