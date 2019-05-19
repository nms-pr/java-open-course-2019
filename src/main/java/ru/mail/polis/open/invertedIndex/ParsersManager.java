package ru.mail.polis.open.invertedIndex;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ParsersManager {

    private static ConcurrentLinkedDeque<Future<ArrayList<Word>>> futures = new ConcurrentLinkedDeque<>();;
    private static ExecutorService service;

    static void parse(int maxNumberOfThreads, int maxDepthOfSearch) throws SQLException {
        service = Executors.newFixedThreadPool(maxNumberOfThreads);

        ArrayList<String> links = (ArrayList<String>) DatabaseProvider.selectHTTP();

        for (String link : links) {
            futures.add(service.submit(new Parser(link, 1, maxDepthOfSearch, new CopyOnWriteArrayList<>())));
        }

        while (!futures.isEmpty()) {
            try {
                List<Word> futureResult = futures.getFirst().get();

                if (!futureResult.isEmpty()) {
                    String link = futureResult.get(0).getWord();
                    futureResult.remove(0);

                    if (!futureResult.isEmpty()) {
                        DatabaseProvider.insertData(link, futureResult);
                    }
                }

            } catch (Exception ignore) {
                ignore.printStackTrace();
            } finally {
                futures.pollFirst();
                System.out.println(futures.size());
            }
        }



    }

    public static synchronized void addTasks(ArrayList<Parser> tasks) {

        for (Parser task: tasks) {
            futures.add(service.submit(task));
        }
    }

    public static ConcurrentLinkedDeque<Future<ArrayList<Word>>> getFutures() {
        return futures;
    }
}
