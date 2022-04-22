package com.learning.java.topic10.interrupt;

public class ThreadInterrupt {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->{
            for (int index = 0; index < 100; index++) {
                if (Thread.currentThread().interrupted()) {
                    System.err.println("Thread Interrupted, So Existing at "+ index);
                    break;
                }
                System.out.println("index = " + index);
            }
        });
        thread.start();
        Thread.sleep(1);
        thread.interrupt();
    }
}
