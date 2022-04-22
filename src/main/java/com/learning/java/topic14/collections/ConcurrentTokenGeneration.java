package com.learning.java.topic14.collections;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentTokenGeneration {

    public static void main(String[] args) {
        ConcurrentHashMap<Integer, Integer> concurrentMap = new ConcurrentHashMap<Integer, Integer>();

        new Thread(() -> {
            Random numGenerator = new Random();
            for (int i = 0; i < 10000; i++) {
                concurrentMap.put(i, numGenerator.nextInt(10000));
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            Random numGenerator = new Random();
            for (int i = 10000; i < 20000; i++) {
                concurrentMap.put(i, numGenerator.nextInt(20000));
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                Random numGenerator = new Random();
                int key = numGenerator.nextInt(20000);
                Integer value = concurrentMap.get(key);
                if (value != null)
                    System.out.println("Key: " + key + " Value is: " + value);

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }

        }).start();

    }

}
