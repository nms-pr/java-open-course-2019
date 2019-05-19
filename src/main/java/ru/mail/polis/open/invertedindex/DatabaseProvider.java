package ru.mail.polis.open.invertedindex;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.LinkedHashSet;


public class DatabaseProvider {
    static void createConnection() throws SQLException {
        DbConnection.getInstance();
    }


    static List<String> selecthttp() throws SQLException {
        List<String> https = new ArrayList<>();
        String st = "SELECT url FROM url where NOT status;";
        Statement statement = DbConnection.getConnection().createStatement();
        ResultSet rs = statement.executeQuery(st);
        while (rs.next()) {
            https.add(rs.getString("url"));
        }
        statement.close();
        rs.close();
        return https;
    }

    static Set<String> selectRequest(String request) throws SQLException {
        String lower = request.toLowerCase();
        String newReq = lower.replaceAll("[^a-zA-Z0-9а-яА-Я]", " ");
        String[] words = newReq.split("[ ]+");
        for (int i = 0; i < words.length; i++) {
            if (StopWordsList.getStopWords().contains(words[i])) {                //change
                words[i] = null;
            }
        }
        return ranging(selectFromDb(words));
    }

    private static List<Word> selectFromCache(String[] words) throws SQLException {
        List<Word> listFromCache = new ArrayList<>();
        List<String> listForDb = new ArrayList<>();

        List<Word> listFromDb = selectFromDb(words);
        if (!listFromCache.isEmpty() && !listFromDb.isEmpty()) {
            listFromCache.addAll(listFromDb);
            return listFromCache;
        } else if (!listForDb.isEmpty()) {
            return listFromDb;
        } else {
            return listFromCache;
        }
    }

    private static List<Word> selectFromDb(String[] words) throws SQLException {
        StringBuilder sql = new StringBuilder();
        List<Word> result = new ArrayList<>();
        String url;
        Integer repeat;
        Boolean head;
        Statement statement = DbConnection.getConnection().createStatement();
        ResultSet rs;
        for (String word : words) {
            if (word != null) {
                sql.delete(0, sql.length());
                sql.append("select dataJson from words where word = '").append(word).append("';");
                rs = statement.executeQuery(sql.toString());
                if (!rs.isBeforeFirst()) {
                    return result;
                }
                while (rs.next()) {
                    String str = rs.getArray("dataJson").toString();
                    str = str.replaceAll("\\\\", "");
                    str = str.replaceAll("[\" {}]", "");
                    String[] parts = str.split(",");
                    String[] subParts;
                    for (int i = 0; i < parts.length; i += 3) {
                        //переделать сравнивая ключ
                        subParts = parts[i].split(":");
                        url = subParts[1] + ":" + subParts[2];
                        subParts = parts[i + 2].split(":");
                        repeat = Integer.parseInt(subParts[1]);
                        subParts = parts[i + 1].split(":");
                        head = Boolean.parseBoolean(subParts[1]);
                        result.add(new Word(url, repeat, head));
                    }
                }
            }
        }
        return result;
    }

    static HashSet<String> ranging(List<Word> result) {
        if (result.isEmpty()) {
            return null;
        }
        List<String> answer = new ArrayList<>();
        List<Word> firstPart = new ArrayList<>();
        List<Word> secondPart = new ArrayList<>();
        if (result.isEmpty()) {
            return null;
        }
        for (Word word : result) {
            if (word.isContainedInTitle()) {
                firstPart.add(word);
            } else {
                secondPart.add(word);
            }
        }
        Word[] first = firstPart.toArray(Word[]::new);
        Word[] second = secondPart.toArray(Word[]::new);
        Arrays.sort(first);
        Arrays.sort(second);
        for (int i = 0; i < first.length; i++) {
            answer.add(first[i].getWord());
        }
        for (int i = 0; i < second.length; i++) {
            answer.add(second[i].getWord());
        }
        HashSet<String> answerSet = new LinkedHashSet<>(answer);
        return answerSet;
    }


    static void insertData(String url, List<Word> words) throws SQLException {
        Statement statementSelect = DbConnection.getConnection().createStatement();
        Statement statementInsert = DbConnection.getConnection().createStatement();
        DbConnection.getConnection().setAutoCommit(false);
        ResultSet rs;
        StringBuilder sql = new StringBuilder();
        for (Word word : words) {
            sql.delete(0, sql.length());
            sql.append("select * from words where word = '").append(word.getWord()).append("';");
            rs = statementSelect.executeQuery(sql.toString());
            if (rs.isBeforeFirst()) {
                sql.delete(0, sql.length());
                sql.append("UPDATE words SET dataJson = array_append(dataJson,'{\"url\":\"")
                        .append(url)
                        .append("\",\"repeatNumb\":")
                        .append(word.getNumberOfRepetitions())
                        .append(",\"header\":")
                        .append(word.isContainedInTitle())
                        .append("}') where word = '")
                        .append(word.getWord())
                        .append("';");
                statementInsert.addBatch(sql.toString());
            } else {
                sql.delete(0, sql.length());
                sql.append("INSERT INTO words (word, dataJson) VALUES('")
                        .append(word.getWord())
                        .append("',array['{\"url\":\"")
                        .append(url)
                        .append("\",\"repeatNumb\" : ")
                        .append(word.getNumberOfRepetitions())
                        .append(",\"header\":")
                        .append(word.isContainedInTitle())
                        .append("}'::jsonb]);");
                statementInsert.addBatch(sql.toString());
            }
        }
        statementInsert.executeBatch();
        DbConnection.getConnection().commit();
        sql.delete(0, sql.length());
        sql.append("Select * from url where url = '").append(url).append("';");
        rs = statementSelect.executeQuery(sql.toString());
        if (rs.isBeforeFirst()) {
            sql.delete(0, sql.length());
            sql.append("Update url SET status = TRUE where url = '").append(url).append("';");
            statementInsert.addBatch(sql.toString());
        } else {
            sql.delete(0, sql.length());
            sql.append("Insert into url (url,status) VALUES ('").append(url).append("',TRUE);");
            statementInsert.addBatch(sql.toString());
        }
        statementInsert.executeBatch();
        DbConnection.getConnection().commit();
        statementInsert.close();
        statementSelect.close();
    }

    static boolean inserthttp(String url) throws SQLException {
        Statement statement = DbConnection.getConnection().createStatement();
        DbConnection.getConnection().setAutoCommit(false);
        StringBuilder sql = new StringBuilder();
        sql.append("Insert into url(status,url) values (FALSE,'").append(url).append("');");
        try {
            statement.addBatch(sql.toString());
            statement.executeBatch();
            DbConnection.getConnection().commit();
        } catch (SQLException e) {
            return false;
        }
        statement.close();
        return true;
    }

    static boolean updateIndex() throws SQLException {
        Statement statement = DbConnection.getConnection().createStatement();
        DbConnection.getConnection().setAutoCommit(false);
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE INDEX words_idx ON words USING GIN (to_tsvector('english', word))");
        try {
            statement.executeBatch();
            DbConnection.getConnection().commit();
            statement.close();
        } catch (SQLException e) {
            return false;
        }
        statement.close();
        return true;
    }


}
