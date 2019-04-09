package ru.mail.polis.open.task6;

public abstract class Person {
    String surname;
    String name;
    String patronymic;
    String address; // Адрес проживания
    String number; // Номер телефона
    int age;
    char sex; // Пол

    public Person(String surname, String name, String patronymic, String address, String number, int age, char sex) {
        if (sex != 'М' && sex != 'м' && sex != 'Ж' && sex != 'Ж') {
            throw new IllegalArgumentException("Неверный пол");
        }
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.address = address;
        this.number = number;
        this.age = age;
        this.sex = sex;
    }
}
