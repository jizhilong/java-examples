package com.jizhilong.examples.anonymousClass;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args ) {
      System.out.println(new Object() {
        @Override
        public String toString() {
          return "hello, world!";
        }
      });
    }
}
