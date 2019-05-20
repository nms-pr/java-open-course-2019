package ru.mail.polis.open.finall.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.logging.Logger;

import ru.mail.polis.open.finall.network.Connection;
import ru.mail.polis.open.finall.network.IConnection;

public class Server implements IConnection {

    private Logger logger = Logger.getLogger(Server.class.getName());

    protected ArrayList<Connection> getConnectionsList() {
        return connectionsList;
    }

    private final ArrayList<Connection> connectionsList = new ArrayList<>();

    public Server() {
        logger.info("Server running...");

        try (ServerSocket serverSocket = new ServerSocket(1000)) {
            while (true) {
                try {
                    new Connection(serverSocket.accept(), this);
                } catch (Exception e) {
                    logger.info("Connection exeption: " + e);
                    ;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }

    }

    @Override
    public synchronized void connectionReady(Connection connection) {
        connectionsList.add(connection);
        sendToAllConnections("Client connected: " + connection);

    }

    @Override
    public synchronized void receiveMsg(Connection connection, String value) {
        sendToAllConnections(value);
    }

    @Override
    public synchronized void disconnect(Connection connection) {
        connectionsList.remove(connection);
        connection.disconect();

    }

    @Override
    public synchronized void exception(Connection connection, Exception exception) {
        logger.info("TcpConnection exeption: " + exception);
    }

    private void sendToAllConnections(String value) {
        final int clSize = connectionsList.size();
        logger.info(value);
        for (int i = 0; i < clSize; i++) {
            connectionsList.get(i).sendString(value);
        }
    }

}
