package com.learning.java.topic13.dining;

public class DiningPhilosophersSynchronized {

    public static void main(String[] args) {
        Philosopher[] philosophers = new Philosopher[5];
        Object[] forks = new Object[philosophers.length];

        for (int i = 0; i < forks.length; i++) {
            forks[i] = new Object();
        }

        for (int i = 0; i < philosophers.length; i++) {
            Object leftFork = forks[i];
            Object rightFork = forks[(i + 1) % forks.length];
            philosophers[i] = new Philosopher(leftFork, rightFork);

            Thread t = new Thread(philosophers[i], "Philosopher " + (i + 1));
            t.start();
        }

    }

}

class Philosopher implements Runnable {

    private Object leftFork;
    private Object rightFork;

    public Philosopher(Object leftFork, Object rightFork) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    @Override
    public void run() {
        while (true) {
            doWork(System.currentTimeMillis() + ": Thinking");
            synchronized (leftFork) {
                doWork(System.currentTimeMillis() + ": Picked up left Fork");
                synchronized (rightFork) {
                    doWork(System.currentTimeMillis() + ": Picked up right Fork and eating");
                }
                doWork(System.currentTimeMillis() + ": Put down Right Fork");
            }
            doWork(System.currentTimeMillis() + ": Put down left Fork, and back to thinking");
        }

    }

    private void doWork(String string) {
        System.out.println(Thread.currentThread().getName() + " " + string);

        try {
            Thread.sleep(((int) (Math.random() * 100)));
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
