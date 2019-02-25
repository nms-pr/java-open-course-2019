import ru.mail.polis.open.task1.FizzBuzzSolution;

/**
 * @author mikhail.nechaev
 * Since 25/02/2019
 */
public class Main {

    public static void main(String[] args) {
        //print() test
        long begin = System.currentTimeMillis();
        new FizzBuzzSolution().print(1, 10000);
        long end = System.currentTimeMillis();
        long simplePrintTime = end - begin;

        //printByStrings() test
        begin = System.currentTimeMillis();
        new FizzBuzzSolution().printByStrings(1, 10000);
        end = System.currentTimeMillis();
        long stringsPrintTime = end - begin;

        //printByStringBuilder() test
        begin = System.currentTimeMillis();
        new FizzBuzzSolution().printByStringBuilder(1, 10000);
        end = System.currentTimeMillis();
        long stringBuilderPrintTime = end - begin;
        System.out.println("Simple time: " + simplePrintTime);
        System.out.println("Strings time: " + stringsPrintTime);
        System.out.println("StringBuilder time: " + stringBuilderPrintTime);
    }

}
