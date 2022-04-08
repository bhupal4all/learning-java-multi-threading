package com.learning.java.topic1.basic;

import static java.lang.Thread.sleep;

public class Sequential {
    public static void main(String[] args) {
        new Worker1().run();
        new Worker2().run();
    }
}

class Worker1 {
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Worker 1 Count  = " + i);
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Worker2 {
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Worker 2 Count  = " + i);
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
