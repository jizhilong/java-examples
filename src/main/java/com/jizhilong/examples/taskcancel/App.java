package com.jizhilong.examples.taskcancel;

import java.util.concurrent.*;

/**
 * @author jizhilong, {@literal <zlji@leyantech.com>}
 * @date 13/10/2017
 */
public class App {
  public static void main(String[] args) {
    ExecutorService executorService = Executors.newFixedThreadPool(4);

    for (int i = 0; i < 16; i++) {
      executorService.execute(() -> {
        System.out.printf("interrupting %s\n", Thread.currentThread().getId());
        Thread.currentThread().interrupt();
      });
    }

    Future<Integer> future = executorService.submit(() -> {
      int a = 1;
      while (a > 0 && !Thread.currentThread().isInterrupted()) {
        a = 1+1;
      }
      System.out.println("leaving");
      return 1;
    });

    System.out.println("getting future");

    try {
      future.get(5, TimeUnit.SECONDS);
    } catch (InterruptedException | ExecutionException | TimeoutException e) {
      e.printStackTrace();
      boolean canceled = future.cancel(true);
      System.out.printf("canceled: %s\n", canceled);
    }
  }
}
