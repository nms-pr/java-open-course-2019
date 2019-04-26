package ru.mail.polis.open.task6;

import java.util.ArrayList;
import java.util.Date;

public class Manager extends Person implements ManagerActions {


    @Override
    public void openLib() {
        if (Library.isOpened) {
            System.out.println("Библиотека уже открыта");
        } else {
            Library.isOpened = true;
        }

    }

    @Override
    public void closeLib() {
        if (!Library.isOpened) {
            System.out.println("Библиотека уже закрыта");
        } else {
            Library.isOpened = false;
        }
    }

    @Override
    public void bringBook(Book book) {

    }

    @Override
    public void removeBook(Book book) {

    }
}
