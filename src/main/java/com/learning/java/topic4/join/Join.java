package com.learning.java.topic4.join;

import java.util.stream.IntStream;

public class Join {
    static int sum = 0;

    public static void main(String[] args) throws InterruptedException {
        int[] array = IntStream.rangeClosed(0, 5000).toArray();
        System.out.println("Correct total of 5000 integers is: " + IntStream.rangeClosed(0, 5000).sum());

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < array.length / 2; i++) {
                sum+=array[i];
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = array.length / 2; i < array.length; i++) {
                sum+=array[i];
            }
        });

        thread1.start();
        thread2.start();
//        thread1.join();
//        thread2.join();

        System.out.println("Sum of 5000 integers in parallel is: " + sum);
    }
}
