package ru.mail.polis.open.finall.client;

import ru.mail.polis.open.finall.network.Connection;
import ru.mail.polis.open.finall.network.IConnection;

import java.io.IOException;
import java.util.logging.Logger;

public class Client implements IConnection {

    private static final String IP_ADDR = "localhost";
    private static final int PORT = 1000;

    private Connection connection;
    private ClientInterface clientGui;

    private Logger logger = Logger.getLogger(Client.class.getName());

    public Client() {
        clientGui = new ClientGui();
        try {
            connection = new Connection(Client.this, IP_ADDR, PORT);
            clientGui.addConnection(connection);
        } catch (IOException e1) {
            logger.warning(e1.getMessage());
        }
    }

    @Override
    public void connectionReady(Connection connection) {
        clientGui.printMsg("Connection ready...");
    }

    @Override
    public void receiveMsg(Connection connection, String value) {
        clientGui.printMsg(value);
    }

    @Override
    public void disconnect(Connection connection) {
        clientGui.printMsg("Disconnect");
    }

    @Override
    public void exception(Connection connection, Exception exception) {
        clientGui.printMsg("Connection exception: " + exception);
    }
}

