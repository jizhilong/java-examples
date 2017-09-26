package com.jizhilong.examples.annotations;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */

@MyAnnotations.TesterInfo(
        priority = MyAnnotations.TesterInfo.Priority.HIGH,
        createdBy = "jizhilong",
        tags = {"sales", "test"}
)
class TestExample {
    @MyAnnotations.Test
    void testA() {
        if (true) {
            throw new RuntimeException("This test always failed");
        }
    }

    @MyAnnotations.Test(enabled = false)
    void testB() {
        if (false) {
            throw new RuntimeException("This test always passed");
        }
    }

    @MyAnnotations.Test(enabled = true)
    void testC() {
    }
}

@MyAnnotations.Author("jizhilong")
public class App
{
    @MyAnnotations.Property(editor = "haha")
    public static void main( String[] args )
    {
        System.out.println("Testing...");

        int passed = 0, failed = 0, count = 0, ignore = 0;

        Class<TestExample> obj = TestExample.class;

        // Process @TesterInfo
        if (obj.isAnnotationPresent(MyAnnotations.TesterInfo.class)) {

            MyAnnotations.TesterInfo testerInfo = (MyAnnotations.TesterInfo) obj.getAnnotation(MyAnnotations.TesterInfo.class);

            System.out.printf("%nPriority :%s", testerInfo.priority());
            System.out.printf("%nCreatedBy :%s", testerInfo.createdBy());
            System.out.printf("%nTags : %s", String.join(", ", Arrays.asList(testerInfo.tags())));
            System.out.printf("%nLastModified :%s%n%n", testerInfo.lastModified());
        }

        for (Method method: Arrays.stream(obj.getDeclaredMethods()).
                filter(m -> m.isAnnotationPresent(MyAnnotations.Test.class)).
                collect(Collectors.toList())) {

            MyAnnotations.Test test = method.getAnnotation(MyAnnotations.Test.class);
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
