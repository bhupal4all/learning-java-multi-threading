package com.learning.java.topic12.cyclicbarrier;

import java.util.concurrent.*;
import java.util.stream.IntStream;

public class SumOfNumbersUsingCyclicBarrier {

    public static int sum = 0;
    public static int[] array1 = IntStream.rangeClosed(0, 5000).toArray();
    public static int[] array2 = IntStream.rangeClosed(5001, 10000).toArray();
    public static int[] array3 = IntStream.rangeClosed(10001, 15000).toArray();
    public static int total = IntStream.rangeClosed(0, 15000).sum();
    final static CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException, ExecutionException {

        Callable callable1 = () -> {
            int sum = 0;
            sum = sum + calculateSum(0, array1.length / 2, array1);
            System.out.println("T1 :: 5000 summed");
            cyclicBarrier.await();
            sum = sum + calculateSum(0, array2.length / 2, array2);
            System.out.println("T1 :: 10000 summed");
            cyclicBarrier.await();
            sum = sum + calculateSum(0, array3.length / 2, array3);
            System.out.println("T1 :: 15000 summed");
            cyclicBarrier.await();
            return sum;
        };

        Callable callable2 = () -> {
            int sum = 0;
            sum = sum + calculateSum(array1.length / 2, array1.length, array1);
            System.out.println("T2 :: 5000 summed");
            cyclicBarrier.await();
            sum = sum + calculateSum(array2.length / 2, array2.length, array2);
            System.out.println("T2 :: 10000 summed");
            cyclicBarrier.await();
            sum = sum + calculateSum(array3.length / 2, array3.length, array3);
            System.out.println("T2 :: 15000 summed");
            cyclicBarrier.await();
            return sum;
        };

        System.out.println("Number of Parties to Trip the barrier :: " + cyclicBarrier.getParties());

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Integer> sum1 = executorService.submit(callable1);
        Future<Integer> sum2 = executorService.submit(callable2);

        System.out.println("Calculating First Sum");
        Thread.sleep(2000);
        System.out.println("Waiting :: " + cyclicBarrier.getNumberWaiting());
        cyclicBarrier.await();
        System.out.println("First Sum is calculated");

        System.out.println("Calculating Second Sum");
        Thread.sleep(2000);
        System.out.println("Waiting :: " + cyclicBarrier.getNumberWaiting());
        cyclicBarrier.await();
        System.out.println("Second Sum is calculated");

        System.out.println("Calculating Third Sum");
        Thread.sleep(2000);
        System.out.println("Waiting :: " + cyclicBarrier.getNumberWaiting());
        cyclicBarrier.await();
        System.out.println("Third Sum is calculated");

        sum = sum1.get() + sum2.get();
        System.out.println("Sum of three arrays is: " + sum);
        System.out.println("Correct sum is: " + total);
        executorService.shutdown();
    }

    private static int calculateSum(int start, int end, int[] array) {
        int sum1 = 0;
        for (int i = start; i < end; i++) {
            sum1 = sum1 + array[i];
        }
        return sum1;
    }

}
