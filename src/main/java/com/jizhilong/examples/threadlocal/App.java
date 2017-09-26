package com.jizhilong.examples.threadlocal;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author jizhilong, {@literal <zlji@leyantech.com>}
 * @date 26/09/2017
 */
public class App {
  private static ThreadLocal<String> threadName = ThreadLocal.withInitial(() -> Thread.currentThread().getName());
  private static ScheduledExecutorService executor = Executors.newScheduledThreadPool(4);

  private static void printThreadName() {
    System.out.println(threadName.get());
  }

  public static void main(String[] args) {
    executor.schedule(App::printThreadName, 1, TimeUnit.SECONDS);
    executor.schedule(App::printThreadName, 2, TimeUnit.SECONDS);
    executor.schedule(App::printThreadName, 3, TimeUnit.SECONDS);
    executor.schedule(App::printThreadName, 4, TimeUnit.SECONDS);
  }
}
