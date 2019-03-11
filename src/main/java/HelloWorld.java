import ru.mail.polis.open.HelloWorld2;
import ru.mail.polis.open.task1.solution.ConsoleFizzBuzzGame;

/**
 * @author mikhail.nechaev
 * Since 25/02/2019
 */
public class HelloWorld {

    public static void main(String[] args) {
        System.out.println("Hello world!");
        HelloWorld2.main(null);

        ConsoleFizzBuzzGame game1 = new ConsoleFizzBuzzGame();
        game1.print(1,20);
    }
}
