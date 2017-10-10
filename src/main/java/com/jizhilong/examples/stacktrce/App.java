package com.jizhilong.examples.stacktrce;

/**
 * @author jizhilong, {@literal <zlji@leyantech.com>}
 * @date 10/10/2017
 */
public class App {
  private static void inner() {
    System.out.printf("calling this method from %s\n", Thread.currentThread().getStackTrace()[1]);
  }

  public static void main(String []args) {
    inner();
  }
}
