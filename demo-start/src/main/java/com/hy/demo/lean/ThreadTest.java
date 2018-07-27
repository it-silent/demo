package com.hy.demo.lean;

/**
 * ThreadTest
 *
 * @author silent
 * @date 2018/4/18
 */
public class ThreadTest implements Runnable {


    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            System.err.println(Thread.currentThread().getName()+"--------"+i);

            if (i == 25) {
                try {
                    new Thread(new ThreadTest(), "join").join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
