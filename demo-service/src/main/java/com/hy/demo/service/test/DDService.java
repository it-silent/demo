package com.hy.demo.service.test;

import org.springframework.stereotype.Service;

/**
 * DDService
 *
 * @author wb-yhy282733
 */
public interface DDService {

    String serviceTest(String attach);

    String reminds();

    default void insert() {
        System.err.println("default method");
    }

    public static void staticMethod() {
        System.err.println("static method");
    }
}
