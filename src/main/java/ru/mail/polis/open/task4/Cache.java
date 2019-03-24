package ru.mail.polis.open.task4;

public class Cache {

    private class Pair {

        private String input;
        private Expr expression;

        private Pair(String input, Expr expression) {
            this.input = input;
            this.expression = expression;
        }
    }

    private final int sizeBlock;
    private Pair[] cache;
    private int lastElement;

    public Cache(int sizeBlock) {
        this.sizeBlock = sizeBlock;
        cache = new Pair[sizeBlock];
        lastElement = 0;
    }

    public void put(String newInput, Expr newExpression) {
        if (lastElement == sizeBlock - 1) {
            if (cache.length - 1 >= 0) {
                System.arraycopy(cache, 1, cache, 0, cache.length - 1);
            }
        } else {
            lastElement++;
        }
        cache[lastElement] = new Pair(newInput, newExpression);
    }

    private int containsInput(String input) {
        int answer = -1;
        for (int i = 0; i < cache.length; i++) {
            if (cache[i].input.equals(input)) {
                answer = i;
                break;
            }
        }
        return answer;
    }

    public Expr getExpression(String input) {
        int index = containsInput(input);
        if (index != -1) {
            return cache[index].expression;
        } else {
            throw new NullPointerException();
        }
    }
}
