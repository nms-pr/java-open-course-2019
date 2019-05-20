package ru.mail.polis.open.finall.network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.Charset;

//основной класс соединения
public class Connection {

    private final Socket socket;
    private final Thread thread;

    private final BufferedReader in;
    private final BufferedWriter out;

    private final IConnection eventListener;

    public Connection(IConnection eventListener, String ipAddres, int port) throws IOException {
        this(new Socket(ipAddres, port), eventListener);
    }

    public Connection(Socket socket, IConnection eventListener) throws IOException {
        this.socket = socket;
        this.eventListener = eventListener;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream(), Charset.forName("UTF-8")));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), Charset.forName("UTF-8")));

        //поток который слушает входящие соединения
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    eventListener.connectionReady(Connection.this);
                    //пока не прерван, получаем строку
                    while (!thread.isInterrupted()) {
                        String msg = in.readLine();
                        eventListener.receiveMsg(Connection.this, msg);
                    }
                } catch (IOException e) {
                    eventListener.exception(Connection.this, e);
                }
            }
        });
        thread.start();
    }

    public synchronized void sendString(String value) {
        try {
            out.write(value + "\r\n");
            out.flush();
        } catch (IOException e) {
            eventListener.exception(Connection.this, e);
        }
    }

    public synchronized void disconect() {
        eventListener.disconnect(Connection.this);
        //прерываем
        thread.interrupt();
        try {
            socket.close();
        } catch (IOException e) {
            eventListener.exception(Connection.this, e);
        }
    }

    @Override
    public String toString() {
        return "TcpConnection: " + socket.getInetAddress() + ": " + socket.getPort();
    }
}
