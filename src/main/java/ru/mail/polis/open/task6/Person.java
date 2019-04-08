package ru.mail.polis.open.task6;

public abstract class Person {
    String name;
    String surname;
    String patronymic;
    byte age;

    abstract void sayHello();

    abstract void sayGoodbye();
}