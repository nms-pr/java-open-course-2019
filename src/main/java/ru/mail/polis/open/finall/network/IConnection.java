package ru.mail.polis.open.finall.network;

public interface IConnection {

    //готовое событие
    void connectionReady(Connection connection);

    //принимаем строчку
    void receiveMsg(Connection connection, String value);

    //соединение порвалось
    void disconnect(Connection connection);

    //где - то случилось исключение
    void exception(Connection connection, Exception exception);


}
