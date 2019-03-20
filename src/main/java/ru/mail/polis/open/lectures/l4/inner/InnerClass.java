package ru.mail.polis.open.lectures.l4.inner;

/**
 * @author mikhail.nechaev
 * Since 18/03/2019
 */
public class InnerClass {

    public static void main(String[] args) {
        class X {
            int x0 = 0;
        }

        X x = new X();
        x.x0 = 123;
        System.out.println(x.x0);

        for (int i = 0; i < 2; i++) {
            final int z = i + 10;
            class Y {
                //var value should be final or effective final
                final int y1 = z;
                final int y2;

                public Y(int y2) {
                    this.y2 = y2;
                }
            }

            Y y = new Y(i);
            System.out.print(y.y1 + " " + y.y2);
            System.out.println();
        }
    }
}
