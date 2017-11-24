package com.jizhilong.examples.logdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jizhilong, {@literal <zlji@leyantech.com>}
 * @date 22/11/2017
 */
public class LogDemo {

  Logger getLogger() {
    return LoggerFactory.getLogger(LogDemo.class);
  }

  public static void main(String[] args) {
    new LogDemo().getLogger().info("hello, world");
  }
}