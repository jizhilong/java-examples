package com.jizhilong.examples.annotations;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.Set;

/**
 *
 * @author zhilong.jzl
 * @version $Id: MyAnnotationProcessor.java, v 0.1 2017年06月16日 12:51 AM zhilong.jzl Exp $
 *
 * test it with command <pre>javac -cp target/classes/ -processor com.jizhilong.examples.annotations.MyAnnotationProcessor src/main/java/com/jizhilong/examples/annotations/App.java</pre>
 */
@SupportedAnnotationTypes("com.jizhilong.MyAnnotations.Property")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class MyAnnotationProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (TypeElement annotation : annotations) {
            System.out.println(annotation.getEnclosedElements());
            System.out.println(annotation.getInterfaces());
            System.out.println(annotation.getQualifiedName());
            System.out.println(annotation.getKind());
            System.out.println(annotation.getTypeParameters());
            System.out.println(annotation.getModifiers());

            Set<? extends Element> elementsAnnotatedWith = roundEnv.getElementsAnnotatedWith(annotation);
            for (Element element : elementsAnnotatedWith) {
                System.out.println(element.getSimpleName());
                System.out.println(element.getModifiers());
            }
        }
        return false;
    }
}