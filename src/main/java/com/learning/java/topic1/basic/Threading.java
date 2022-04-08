package com.learning.java.topic1.basic;

public class Threading {
    public static void main(String[] args) {
        new TWorker1().start();
        new TWorker2().start();
    }
}

class TWorker1 extends Thread {
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

class TWorker2 extends Thread {
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