package ru.mail.polis.open.task1;

class FizzBuzzClass implements FizzBuzz {
    private static final String FIZZ = "Fizz";
    private static final String BUZZ = "Buzz";
    public static final int FROM = 1;
    public static final int TO = 100;

    public void print(int from, int to) {
        StringBuilder stringOutput = new StringBuilder();
        for (int i = from; i <= to; i++) {
            if (i % 3 != 0 && i % 5 != 0) {
                stringOutput.append(i);
            } else {
                if (i % 3 == 0) {
                    stringOutput.append(FIZZ);
                }
                if (i % 5 == 0) {
                    stringOutput.append(BUZZ);
                }
            }
            stringOutput.append(" ");
        }
        System.out.print(stringOutput);
    }
    
    public static void main(String[] args) {
        FizzBuzzClass fizzBuzzVar = new FizzBuzzClass();
        fizzBuzzVar.print(FROM, TO);
    }
}
