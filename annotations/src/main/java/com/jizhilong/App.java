package com.jizhilong;

import com.jizhilong.MyAnnotations.Author;
import com.jizhilong.MyAnnotations.Test;
import com.jizhilong.MyAnnotations.TesterInfo;
import com.jizhilong.MyAnnotations.TesterInfo.Priority;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Hello world!
 *
 */
@Author("jizhilong")
public class App
{
    @TesterInfo(
            priority = Priority.HIGH,
            createdBy = "jizhilong",
            tags = {"sales", "test"}
    )
    private static class TestExample {
        @Test
        void testA() {
            if (true) {
                throw new RuntimeException("This test always failed");
            }
        }

        @Test(enabled = false)
        void testB() {
            if (false) {
                throw new RuntimeException("This test always passed");
            }
        }

        @Test(enabled = true)
        void testC() {
            if (10 > 1) {
                // do nothins, this test always passed
            }
        }
    }

    public static void main( String[] args )
    {
        System.out.println("Testing...");

        int passed = 0, failed = 0, count = 0, ignore = 0;

        Class<TestExample> obj = TestExample.class;

        // Process @TesterInfo
        if (obj.isAnnotationPresent(TesterInfo.class)) {

            TesterInfo testerInfo = (TesterInfo) obj.getAnnotation(TesterInfo.class);

            System.out.printf("%nPriority :%s", testerInfo.priority());
            System.out.printf("%nCreatedBy :%s", testerInfo.createdBy());
            System.out.printf("%nTags : %s", String.join(", ", Arrays.asList(testerInfo.tags())));
            System.out.printf("%nLastModified :%s%n%n", testerInfo.lastModified());
        }

        for (Method method: Arrays.stream(obj.getDeclaredMethods()).
                filter(m -> m.isAnnotationPresent(Test.class)).
                collect(Collectors.toList())) {

            Test test = method.getAnnotation(Test.class);
            // if enabled = true (default)
            if (test.enabled()) {
                try {
                    method.invoke(obj.newInstance());
                    System.out.printf("%s - Test '%s' - passed %n", ++count, method.getName());
                    passed++;
                } catch (Throwable ex) {
                    System.out.printf("%s - Test '%s' - failed: %s %n", ++count, method.getName(), ex.getCause());
                    failed++;
                }

            } else {
                System.out.printf("%s - Test '%s' - ignored%n", ++count, method.getName());
                ignore++;
            }
        }

        System.out.printf("%nResult : Total : %d, Passed: %d, Failed %d, Ignore %d%n", count, passed, failed, ignore);
    }
}
