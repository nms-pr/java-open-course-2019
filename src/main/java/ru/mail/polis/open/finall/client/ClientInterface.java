package ru.mail.polis.open.finall.client;

import ru.mail.polis.open.finall.network.Connection;

public interface ClientInterface {
    void getText();

    void printMsg(String msg);

    void addConnection(Connection connection);
}
