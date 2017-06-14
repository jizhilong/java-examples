package com.jizhilong;

import java.util.Iterator;

/**
 * Hello world!
 *
 */
public class App
{
    interface Greeter {
        String greet();
    }

    private static void callGreeter(Greeter greeter) {
        System.out.println(greeter.greet());
    }

    public static void main( String[] args )
    {
        callGreeter(new Greeter() {
            public String greet() {
                return "Hello, world!";
            }
        });
    }
}
