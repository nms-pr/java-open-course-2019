package ru.mail.polis.open.invertedIndex;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

class DbConnection {

    private static Connection connection;

    public static Connection getConnection() {
        return connection;
    }

    private static DbConnection instance;

    private DbConnection() throws SQLException {
        String url = "jdbc:postgresql://" +
                ConfigFileProvider.getHost() +
                ":" +
                ConfigFileProvider.getPort() +
                "/" +
                ConfigFileProvider.getDatabase();
        Properties props = new Properties();
        props.setProperty("user", ConfigFileProvider.getLogin());
        props.setProperty("password", ConfigFileProvider.getPassword());
        connection = DriverManager.getConnection(url, props);

    }

    public static DbConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DbConnection();
        }
        return instance;
    }
}

