package ru.mail.polis.open.task1;

/**
 * @author mikhail.nechaev
 * Since 25/02/2019
 */
public interface FizzBuzz {

    /**
     * Напишите программу, которая выводит на экран числа от 1 до 100.
     * При этом вместо чисел, кратных трем,
     *  программа должна выводить слово «Fizz»,
     *  а вместо чисел, кратных пяти — слово «Buzz».
     * Если число кратно и 3, и 5,
     *  то программа должна выводить слово «FizzBuzz»
     * @param from - с какого числа начинать отсчёт
     * @param to - каким числом заканчивать отсчёт
     */
    void print(int from, int to);
}

class Fb implements FizzBuzz {
    @Override
    public void print(int from, int to){

        for (int count = from; count <= to; count++){
            String str = "";
            if (count % 3 == 0) str+="Fizz";
            if (count % 5 == 0) str+="Buzz";
            if (str == "") System.out.println(count);
            else System.out.println(str);
        }
    }
}
