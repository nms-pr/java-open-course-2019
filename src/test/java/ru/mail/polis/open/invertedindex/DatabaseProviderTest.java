package ru.mail.polis.open.invertedindex;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class DatabaseProviderTest {
    private static Word one = new Word("one", 7, true);
    private static Word two = new Word("two", 7, false);
    private static Word three = new Word("three", 3, true);
    private static Word four = new Word("four", 5, false);
    private static Word five = new Word("five", 1, true);

    @BeforeAll
    @Test
    static void connection() {
        try {
            ConfigFileProvider.readFile("InvertIndexConfiguration");
            try {
                assertEquals(null, DbConnection.getConnection());
                DatabaseProvider.createConnection();
                assertNotEquals(null, DbConnection.getConnection());
            } catch (SQLException e) {
                System.out.println("Fail. Problems with connection");
            }
        } catch (IOException e) {
            System.out.println("Problems with configuration file");
        }
    }

    @Test
    void selectHttp() {
        try {
            String url = "http://Test";
            DatabaseProvider.createConnection();
            assertFalse(DatabaseProvider.selecthttp().contains(url));
            assertTrue(DatabaseProvider.inserthttp(url));
            assertTrue(DatabaseProvider.selecthttp().contains(url));
            assertFalse(DatabaseProvider.inserthttp(url));
            rollbackInsetHttp(url);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Fail.  Problems with connection(Http)");
        }
    }

    @Test
    void insertData() {
        try {
            HashSet<String> res = new LinkedHashSet<>();
            String url = "http://data";
            res.add(url);
            assertFalse(DatabaseProvider.selecthttp().contains(url));
            assertFalse(fullListOfUrl().contains(url));
            assertEquals(null, DatabaseProvider.selectRequest("one"));
            DatabaseProvider.insertData(url, new ArrayList<>(Arrays.asList(one, two)));
            assertFalse(DatabaseProvider.selecthttp().contains((url)));
            assertTrue(fullListOfUrl().contains(url));
            assertEquals(res, DatabaseProvider.selectRequest("one"));
            rollbackInsetWord(one.getWord());
            rollbackInsetWord(two.getWord());
            rollbackInsetHttp(url);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Fail. Problems with connection(Data)");
        }
    }

    @Test
    void ranging() {
        assertEquals(new HashSet<>(Arrays.asList(one.getWord(), three.getWord(), five.getWord(),
                two.getWord(), four.getWord())),
                DatabaseProvider.ranging(new ArrayList<>(Arrays.asList(one, two, three, four, five))));

    }

    @Test
    void updateIndex() {
        try {
            assertTrue(DatabaseProvider.updateIndex());
            dropIndex();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void rollbackInsetHttp(String url) throws SQLException {
        Statement statement = DbConnection.getConnection().createStatement();
        DbConnection.getConnection().setAutoCommit(true);
        StringBuilder sql = new StringBuilder();
        sql.append("Delete from url where url = '").append(url).append("';");
        statement.executeUpdate(sql.toString());
        statement.close();
    }

    private static void rollbackInsetWord(String word) throws SQLException {
        Statement statement = DbConnection.getConnection().createStatement();
        DbConnection.getConnection().setAutoCommit(true);
        StringBuilder sql = new StringBuilder();
        sql.append("Delete from words where word = '").append(word).append("';");
        statement.executeUpdate(sql.toString());
        statement.close();
    }

    private static List<String> fullListOfUrl() throws SQLException {
        List<String> https = new ArrayList<>();
        String st = "SELECT url FROM url;";
        Statement statement = DbConnection.getConnection().createStatement();
        ResultSet rs = statement.executeQuery(st);
        while (rs.next()) {
            https.add(rs.getString("url"));
        }
        statement.close();
        rs.close();
        return https;
    }

    private static boolean dropIndex() throws SQLException {
        Statement statement = DbConnection.getConnection().createStatement();
        DbConnection.getConnection().setAutoCommit(true);
        StringBuilder sql = new StringBuilder();
        sql.append("DROP INDEX words_idx ");
        try {
            statement.executeUpdate(sql.toString());
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        statement.close();
        return true;
    }

}