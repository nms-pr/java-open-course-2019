package main.java.ru.mail.polis.open.task1;

public class CFizzBuzz implements FizzBuzz {
    @Override
    public void print(int from, int to) {
        StringBuilder s = new StringBuilder();

        for(Integer i = from; i <= to; i++) {

            boolean textIs = false;

            if(i%3 == 0) {
                s.append("Fizz");
                textIs = true;
            }

            if(i%5 == 0) {
                s.append("Buzz");
                textIs = true;
            }

            if(!textIs) s.append(i.toString());

            s.append(System.lineSeparator());
        }
        System.out.println(s.toString());
    }
}
