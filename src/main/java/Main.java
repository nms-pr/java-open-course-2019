import ru.mail.polis.open.task1.FizzBuzz;
import ru.mail.polis.open.task1.FizzBuzzSolution;
import ru.mail.polis.open.task1.StringBulderFizzBuzzSolution;
import ru.mail.polis.open.task1.StringFizzBuzzSolution;

public class Main {

    //границы интервала выводимых чисел
    private static final int from = 1;
    private static final int to = 100;

    public static void main(String[] args) {

        //прогоняем тесты для каждой реализации
        long simplePrintTime = testPrintByTime(new FizzBuzzSolution());
        long stringsPrintTime = testPrintByTime(new StringFizzBuzzSolution());
        long stringBuilderPrintTime = testPrintByTime(new StringBulderFizzBuzzSolution());

        //смотрим что получилось
        System.out.println("Simple time: " + simplePrintTime);
        System.out.println("Strings time: " + stringsPrintTime);
        System.out.println("StringBuilder time: " + stringBuilderPrintTime);
    }

    //метод тестирования по времени
    //возвращает время работы метода print()
    private static long testPrintByTime(FizzBuzz solution){
        long begin = System.currentTimeMillis();
        solution.print(from, to);
        long end = System.currentTimeMillis();
        return end - begin;
    }
}
