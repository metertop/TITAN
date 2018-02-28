package com.haoyx.annotations;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by haoyuexun on 2018/2/24.
 */

public class AnnotationExample {
    public static void main(String[] args) {
        AnnotationExample ae = new AnnotationExample();
        String msg = ae.toString();
        System.out.println(msg);
//
//        AnnotationExample.oldMethod();
        AnnotationExample.genericsTest();

    }

    @Override
    @MethodInfo(author = "Pankaj", comments = "Main method", date = "Nov 17 2012", revision = 1)
    public String toString() {
        return "Overriden toString method" ;
    }

    @Deprecated
    @MethodInfo(comments = "deprecated method", date = "Nov 17 2012")
    public static void oldMethod() {
        System.out.println("old method, don't  use it.");
    }

    @SuppressWarnings(value={"deprecation"})
    @MethodInfo(author = "Pankaj", comments = "Main method", date = "Nov 17 2012", revision = 10)
    public static void genericsTest() {//throws FileNotFoundException {
//        List l = new ArrayList();
        List l = new ArrayList();
        l.add("abc");
        oldMethod();
    }
}
