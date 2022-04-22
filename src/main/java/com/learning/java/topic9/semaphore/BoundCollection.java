package com.learning.java.topic9.semaphore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.logging.*;

public class BoundCollection {

    static Logger logger = Logger.getLogger(BoundCollection.class.getName());

    private final Semaphore semaphore;
    private List<Integer> arrayList;

    public BoundCollection(int limit) {
        this.arrayList = Collections.synchronizedList(new ArrayList<Integer>());
        this.semaphore = new Semaphore(limit);
        System.setProperty("java.util.logging.SimpleFormatter.format", "%1$tF %1$tT %4$s %2$s %5$s%6$s%n");
    }

    public boolean add(Integer i) throws InterruptedException {
        boolean added = false;
        semaphore.acquire();
        added = arrayList.add(i);
        if (!added) semaphore.release();
        return added;
    }

    public boolean remove(Integer i) {
        boolean removed = arrayList.remove(i);
        if (removed)
            semaphore.release();
        return removed;
    }


    public static void main(String[] args) {
        final BoundCollection boundCollection = new BoundCollection(5);

        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    if (boundCollection.add(i)) {
                        logger.info("[" + Thread.currentThread().getName() + "] added value: " + i);
                    }
                } catch (InterruptedException e) {
                    logger.severe(e.getLocalizedMessage());
                }
            }

        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                logger.severe(e.getLocalizedMessage());
            }
            for (int i = 0; i < 20; i++) {
                if (boundCollection.remove(i)) {
                    logger.info("[" + Thread.currentThread().getName() + "] removed value: " + i);
                }
            }

        }).start();

    }

}












