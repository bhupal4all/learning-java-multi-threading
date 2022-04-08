package com.learning.java.topic5.share;

public class VolatileTest {
    private static volatile int SHARED_VAR_INT = 0;

    public static void main(String[] args) {
        new ChangeListener().start();
        new ChangeMaker().start();
    }

    static class ChangeListener extends Thread {
        @Override
        public void run() {
            int local_value = SHARED_VAR_INT;
            while (local_value < 5) {
                if (local_value != SHARED_VAR_INT) {
                    System.out.println("ChangeListener :: Got Change for SHARED_VAR_INT : " + SHARED_VAR_INT);
                    local_value = SHARED_VAR_INT;
                }
            }
        }
    }

    static class ChangeMaker extends Thread {
        @Override
        public void run() {
            int local_value = SHARED_VAR_INT;
            while (SHARED_VAR_INT < 5) {
                System.out.println("ChangeMaker :: Incrementing SHARED_VAR_INT to " + (local_value + 1));
                SHARED_VAR_INT = ++local_value;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}