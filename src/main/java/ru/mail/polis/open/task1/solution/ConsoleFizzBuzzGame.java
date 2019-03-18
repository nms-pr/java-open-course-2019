package ru.mail.polis.open.task1.solution;

/**
 * @author mikhail.nechaev
 * Since 04/03/2019
 */
public class ConsoleFizzBuzzGame extends AbstractFizzBuzzGame {

    public ConsoleFizzBuzzGame() {
        super();
    }

    public ConsoleFizzBuzzGame(String wordDelimiter) {
        super(wordDelimiter);
    }

    @Override
    void print(String word) {
        System.out.print(word);
    }

    @Override
    void flush() {
        System.out.flush();
    }
}
