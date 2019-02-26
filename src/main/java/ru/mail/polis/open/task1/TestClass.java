import ru.mail.polis.open.task1.FizzBuzz;

class TestClass implements FizzBuzz {
    public void print(int from, int to) {

        for (int i = from; i < to; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                System.out.println("FizzBuzz");
            } else if (i % 3 == 0) {
                System.out.println("Fizz");
            } else if (i % 5 == 0) {
                System.out.println("Buzz");
            }else
                System.out.println(i);
        }
    }

    public static void main(String[] args) {
        FizzBuzz fizzBuzz = new TestClass();
        fizzBuzz.print(1, 100);
    }
}