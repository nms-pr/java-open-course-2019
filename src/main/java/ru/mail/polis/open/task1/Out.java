package ru.mail.polis.open.task1;


        public class Out  implements FizzBuzz {
            @Override
            public void print(int from, int to) {
                for (int i = from; i < to; i++) {

                    if ((i % 5 == 0) && (i % 3 == 0)) {
                        System.out.println("fizzbuzz");
                    } else {
                        if (i % 5 == 0) {
                            System.out.println("buzz");
                        } else {
                            if
                            (i % 3 == 0) {
                                System.out.println("fizz");
                            } else

                                System.out.println(i);
                        }
                    }

                }
            }

            public static void main(String args[]) {
                Out demo = new Out();
                demo.print(0, 101);

            }
        }

