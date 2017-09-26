package com.jizhilong.examples.lambdas;

/**
 * Hello world!
 *
 */
public class App 
{
    public interface Chooser<T extends Comparable> {
        T choose(T o1, T o2);
    }

    static <T extends Comparable> void printChoosed(T o1, T o2, Chooser<T> chooser) {
        System.out.println(chooser.choose(o1, o2));
    }

    public static void main( String[] args )
    {
        Runnable r = () -> System.out.println( "Hello World!" );
        Integer o1 = 1;
        Integer o2 = 2;
        Chooser<Integer> chooser = (i1, i2) -> (i1 > i2) ? i1 : i2;
        printChoosed(o1, o2, chooser);

        printChoosed(1, 2, (i1, i2) -> (i1 > i2) ? i1 : i2);
        r.run();
    }
}
