import ru.mail.polis.open.HelloWorld2;
import ru.mail.polis.open.task1.FizzBuzz;
import ru.mail.polis.open.task1.MyFizzBuzz;

/**
 * @author mikhail.nechaev
 * Since 25/02/2019
 */
public class HelloWorld {

    public static void main(String[] args) {
        System.out.println("Hello world!");
        FizzBuzz fizzbuzz = new MyFizzBuzz();
        fizzbuzz.print(4,95);
        HelloWorld2.main(null);
    }
}
