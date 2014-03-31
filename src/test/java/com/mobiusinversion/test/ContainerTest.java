package com.mobiusinversion.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ContainerTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.scan("com.mobiusinversion.web");
        System.out.println(annotationConfigApplicationContext);
    }

}