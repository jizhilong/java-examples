package com.jizhilong.examples.logdemo;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest({LoggerFactory.class})
public class LogDemoTest {

  @Test
  public void testGetLogger() {
    mockStatic(LoggerFactory.class);
    Logger mocked = mock(Logger.class);
    when(LoggerFactory.getLogger(LogDemo.class)).thenReturn(mocked);
    LogDemo.main(new String[]{});
    verify(mocked, times(1)).info("hello, world");
  }
}