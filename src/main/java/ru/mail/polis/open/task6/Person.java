package ru.mail.polis.open.task6;

public abstract class Person {
    private String fullName;
    private String sex;
    private int age;

    Person(String fullName, String sex, int age) {
        this.fullName = fullName;
        this.sex = sex;
        this.age = age;
    }

    public String getFullName() {
        return this.fullName;
    }

    public String getSex() {
        return this.sex;
    }

    public int getAge() {
        return this.age;
    }
}
