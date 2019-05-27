package ru.mail.polis.open.finall.dao;

public interface UserDao {
    boolean validateUser(String username, String password);
}
