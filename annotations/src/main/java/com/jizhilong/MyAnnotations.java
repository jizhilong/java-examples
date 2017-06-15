/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.jizhilong;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author zhilong.jzl
 * @version $Id: MyAnnotations.java, v 0.1 2017年06月14日 8:38 PM zhilong.jzl Exp $
 */
public class MyAnnotations {
    public @interface Author {
        String value();
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface Test {
        // should ignore this test?
        public boolean enabled() default true;
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface TesterInfo {
        public enum Priority {
            LOW,
            MEDIUM,
            HIGH
        }

        Priority priority() default Priority.MEDIUM;

        String[] tags() default "";

        String createdBy() default "jizhilong";

        String lastModified() default "14/06/2017";
    }

    @Documented
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface Property {
        String editor() default "";
    }
}