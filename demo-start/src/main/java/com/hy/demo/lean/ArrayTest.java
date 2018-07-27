package com.hy.demo.lean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ArrayTest
 *
 * @author silent
 * @date 2018/4/14
 */
public class ArrayTest {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList();
        list.add(124);
        list.add(114);
        list.add(115);
        list.add(145);
        Integer max = Collections.max(list);
        System.err.println(list.indexOf(max));

        ArrayTest arrayTest = new ArrayTest();
        boolean b = arrayTest instanceof ArrayTest;
        System.err.println(b);

        Character character = 'a';
        Integer.valueOf("1");
        Integer.parseInt("1");

    }
}
