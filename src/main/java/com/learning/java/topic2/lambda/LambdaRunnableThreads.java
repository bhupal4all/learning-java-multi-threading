package com.learning.java.topic2.lambda;

import static java.lang.Thread.sleep;

public class LambdaRunnableThreads {
    public static void main(String[] args) {
        // Runnable Interface
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " count = " + i);
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Worker 1").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " count = " + i);
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Worker 2").start();
    }
}
