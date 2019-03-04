package ru.mail.polis.open.task1;

public class Main {

        public static void main(String[] args){
            FizzBuzzImpl obj = new FizzBuzzImpl();
             obj.from = 1;

            // obj.print(1, 100);
            for (int i = 1;i < 100; i++){

                obj.check = false;

                if (i % 3 == 0){
                    obj.print(obj.from, i);
                    obj.from = i+1;
                    System.out.print("Fizz");
                    obj.check = true;
                }
                if (i % 5 == 0){
                    if (obj.check != true){
                        obj.print(obj.from, i);
                        obj.from = i+1;
                        obj.check = true;
                    }
                    System.out.print("Buzz");
                }
                if (obj.check == true){
                    System.out.println();
                }
            }
            obj.print(obj.from , 101);
        }
}
