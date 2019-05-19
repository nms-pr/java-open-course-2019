package ru.mail.polis.open.invertedindex;

public class Word implements Comparable<Word> {

    private String word;
    private int numberOfRepetitions;
    private boolean containedInTitle;

    Word(String word) {
        this.word = word;
        this.numberOfRepetitions = 1;
        this.containedInTitle = false;
    }

    Word(String word, int number, Boolean repeat) {
        this.word = word;
        this.numberOfRepetitions = number;
        this.containedInTitle = repeat;
    }

    public void setContainsInTitle(boolean containedInTitle) {
        this.containedInTitle = containedInTitle;
    }

    public void incrementNumberOfRepetitions() {
        this.numberOfRepetitions++;
    }

    public int getNumberOfRepetitions() {
        return numberOfRepetitions;
    }

    public String getWord() {
        return word;
    }

    public boolean isContainedInTitle() {
        return containedInTitle;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Word word = (Word) obj;
        return word.getWord().equals(this.word);
    }

    public int compareTo(Word other) {
        if (this.getNumberOfRepetitions() > other.getNumberOfRepetitions()) {
            return 1;
        } else if (this.getNumberOfRepetitions() == other.getNumberOfRepetitions()) {
            return 0;
        }
        return -1;
    }
}
