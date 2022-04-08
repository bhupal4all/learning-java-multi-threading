package com.learning.java.topic6.synchronization;

public class SyncBlock {

    public static void main(String[] args) throws InterruptedException {

        WorkerThread worker1 = new WorkerThread("Worker1");
        WorkerThread worker2 = new WorkerThread("Worker2");

        worker1.start();
        worker2.start();

        worker1.join();
        worker2.join();

    }

    static class WorkerThread extends Thread {

        public WorkerThread(String name) {
            super(name);
        }

        void print(int val) {
            synchronized (this) {
                System.out.println("[ Sync Block ] " + Thread.currentThread().getName() + " :: " + val);
            }
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                print(i);
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
