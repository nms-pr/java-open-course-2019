package ru.mail.polis.open.invertedindex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ConfigFileProvider {

    private static String host;
    private static String database;
    private static String port;
    private static String login;
    private static String password;
    private static int depthOfSearch;
    private static int numberOfStreams;

    public static void readFile(String fileName) throws IOException {

        File file = new File(fileName);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            parseLine(line);
        }
        bufferedReader.close();
        fileReader.close();
    }

    private static void parseLine(String line) throws IllegalArgumentException {

        String[] lineParts = line.split(":");

        if (lineParts.length != 2) {
            throw new IllegalArgumentException("Wrong line: " + "\"" + line + "\"");
        }

        lineParts[0] = lineParts[0].trim().toLowerCase();
        lineParts[1] = lineParts[1].trim();

        storeParameter(lineParts);
    }

    private static void storeParameter(String[] lineParts) {

        host = lineParts[0].equals("host") ? lineParts[1] : host;
        port = lineParts[0].equals("port") ? lineParts[1] : port;
        database = lineParts[0].equals("database") ? lineParts[1] : database;
        login = lineParts[0].equals("login") ? lineParts[1] : login;
        password = lineParts[0].equals("password") ? lineParts[1] : password;

        depthOfSearch = lineParts[0].equals("depthofsearch") ? Integer.parseInt(lineParts[1]) : depthOfSearch;
        numberOfStreams = lineParts[0].equals("numberofstreams") ? Integer.parseInt(lineParts[1]) : numberOfStreams;
    }

    public static String getDatabase() {
        return database;
    }

    public static String getHost() {
        return host;
    }

    public static int getDepthOfSearch() {
        return depthOfSearch;
    }

    public static int getNumberOfStreams() {
        return numberOfStreams;
    }

    public static String getLogin() {
        return login;
    }

    public static String getPassword() {
        return password;
    }

    public static String getPort() {
        return port;
    }

}
