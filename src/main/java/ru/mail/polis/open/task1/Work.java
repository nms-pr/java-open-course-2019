package ru.mail.polis.open.task1;

public class Work implements FizzBuzz {

    @Override // Аннотации нужны для безопасности при удалении родителя?
    //
    public void print(int from, int to) {
        for(int i = from;i <= to;i++){

            //Решение через строки, так как
            //1)строки универсальны для изменения
            //2)решение короткое и читаемое
            String s ="";
            if (i%3==0) s = "Fizz";
            if (i%5==0) s+= "Bazz";
            if (s =="") s = s.valueOf(i);
            System.out.println(s);

        }
    }
}
