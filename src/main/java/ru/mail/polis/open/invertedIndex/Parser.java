package ru.mail.polis.open.invertedIndex;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;

public class Parser implements Callable<ArrayList<Word>> {

    private String currentPageLink;
    private int currentDepth;
    private int maxAllowedDepth;
    private CopyOnWriteArrayList<String> visitedPages;
    private Document document;
    private ArrayList<Word> foundWords;


    Parser(String link, int depth, int maxAllowedDepth, CopyOnWriteArrayList<String> visitedPages) {
        this.currentPageLink = link;
        this.currentDepth = depth;
        this.maxAllowedDepth = maxAllowedDepth;
        this.visitedPages = visitedPages;
        this.foundWords = new ArrayList<>();
    }


    @Override
    public ArrayList<Word> call() {
        try {
            if (currentDepth > 3 ) System.out.println("Depth:" + currentDepth+ " Max:" + maxAllowedDepth );
            foundWords.add(new Word(currentPageLink));
            getPage();
            visitedPages.add(currentPageLink);
            ParsersManager.addTasks(
                    createNewTasks(
                            getNotVisitedLinksFromCurrentPage()
                    )
            );

            processPageText();
            processHeaders();

            return foundWords;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }


    private void processHeaders() {

        Elements headers = document.select("h1");
        headers.addAll(document.select("h2"));
        headers.addAll(document.select("h3"));
        headers.addAll(document.select("h4"));
        headers.addAll(document.select("h5"));
        headers.addAll(document.select("h6"));

        for (Element element : headers) {

            String[] words = element.ownText()
                    .toLowerCase()
                    .replaceAll("[^a-zа-я]", " ")
                    .split(" ");

            for (String word : words) {
                if (!word.equals("") && !StopWordsList.getStopWords().contains(word)) {
                    Word newWord = new Word(word);
                    if (foundWords.contains(newWord)) {
                        foundWords.get(foundWords.indexOf(newWord)).setContainsInTitle(true);
                        foundWords.get(foundWords.indexOf(newWord)).incrementNumberOfRepetitions();
                    } else {
                        newWord.setContainsInTitle(true);
                        foundWords.add(newWord);
                    }
                }
            }

        }
    }


    private void processPageText() {
        Elements elements = document.body().getAllElements();

        for (Element element: elements) {

            String[] words = element.ownText()
                    .toLowerCase()
                    .replaceAll("[^a-zа-я]", " ")
                    .split(" ");

            for (String word : words) {
                if (!word.equals("") && !StopWordsList.getStopWords().contains(word)) {
                    Word newWord = new Word(word);
                    if (!foundWords.contains(newWord)) {
                        foundWords.add(newWord);
                    } else {
                        foundWords.get(foundWords.indexOf(newWord)).incrementNumberOfRepetitions();
                    }
                }
            }

        }
    }


    /**
     * @param links ссылки, на не посещённые страницы сайта
     * @return список "заданий" по обраотке страниц сайта
     */
    private synchronized ArrayList<Parser> createNewTasks(ArrayList<String> links) {
        ArrayList<Parser> result = new ArrayList<>();

        if (links != null && currentDepth < maxAllowedDepth) {
            for (String link : links) {
                Parser newTask = new Parser(link, currentDepth+1, maxAllowedDepth, visitedPages);

                if (!result.contains(newTask)) {
                    result.add(newTask);
                }
            }
        }

        return result;
    }


    /**
     * load HTML document by link
     */
    private void getPage() throws IOException {
        document = Jsoup.connect(currentPageLink).get();
    }


    /**
     * @return не посещённые ссылки, ведущие на этот же сайт
     */
    private ArrayList<String> getNotVisitedLinksFromCurrentPage() {
        ArrayList<String> result = new ArrayList<>();

        Elements links = document.select("a[href]");

        for (Element link: links) {
            if (linkIsRelated(link.attr("abs:href"))
                    && !visitedPages.contains(link.attr("abs:href"))
            ) {
                result.add(link.attr("abs:href"));
            }
        }

        return result;
    }


    /**
     * @return если ссылка ведёт на этот же сайт {@code true}, иначе {@code false}
     */
    private boolean linkIsRelated(String link) {
        return (link.split("[/]+").length > 1 &&
                currentPageLink.split("[/]+")[1].equals(link.split("[/]+")[1]));
    }


    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Parser parser = (Parser) obj;
        return parser.currentPageLink.equals(this.currentPageLink);
    }
}
