package ru.mail.polis.open.finaltask.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable {

    private Server server;

    private PrintWriter outMessage;

    private Scanner inMessage;
    private static final String HOST = "localhost";
    private static final int PORT = 8044;

    private Socket clientSocket = null;

    private static int clientsCounter = 0;

    public ClientHandler(Socket socket, Server server) {
        try {
            clientsCounter++;
            this.server = server;
            this.clientSocket = socket;
            this.outMessage = new PrintWriter(socket.getOutputStream());
            this.inMessage = new Scanner(socket.getInputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                server.sendMessageToAllClients("Новый участник вошел в чат!");
                server.sendMessageToAllClients("Клиентов в чате = " + clientsCounter);
                break;
            }
            while (true) {
                if (inMessage.hasNext()) {
                    String clientMessage = inMessage.nextLine();
                    if (clientMessage.equalsIgnoreCase("##session##end##")) {
                        break;
                    }
                    System.out.println(clientMessage);
                    server.sendMessageToAllClients(clientMessage);
                }
                Thread.sleep(100);
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            this.close();
        }
    }

    public void sendMsg(String msg) {
        try {
            outMessage.println(msg);
            outMessage.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void close() {
        server.removeClient(this);
        clientsCounter--;
        server.sendMessageToAllClients("Клиентов в чате = " + clientsCounter);
    }
}
