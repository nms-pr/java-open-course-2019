package ru.mail.polis.open.task6;

public abstract class AbstractPerson {
    int age;
    int salary;
    char gender;
    String name;
    String surname;
    String patronymic;

    abstract void salut();
    abstract void farewell();
}
