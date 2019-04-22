package ru.mail.polis.open.task6;

public class SomeManager extends People implements Manager {

    SomeManager(String name, int age, char gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public void openLibrary() {
        Library.setLibStatus(true);
        Library.libInit();
    }

    public void closeLibrary() {
        Library.setLibStatus(false);
    }

    void greeting() {
        System.out.println("Здравствуйте, чем могу помочь?");
    }

    void goodbye() {
        System.out.println("До свидания!");
    }
}
