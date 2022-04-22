package com.learning.java.topic8.waitnotify;

public class TopicNotifier {
    public static void main(String[] args) {

        Topic topic = new Topic("Java-Event");

        new Thread(() -> {
            synchronized (topic) {
                System.out.println("[" + Thread.currentThread().getName() + "] Waiting for Event");
                try {
                    topic.wait();
                    System.out.println("[" + Thread.currentThread().getName() + "] Registered for Event");
                } catch (InterruptedException e) {
                } finally {
                    System.out.println("[" + Thread.currentThread().getName() + "] Completed");
                }
            }
        }, "email").start();
        new Thread(() -> {
            synchronized (topic) {
                System.out.println("[" + Thread.currentThread().getName() + "] Waiting for Event");
                try {
                    topic.wait();
                    System.out.println("[" + Thread.currentThread().getName() + "] Registered for Event");
                } catch (InterruptedException e) {
                } finally {
                    System.out.println("[" + Thread.currentThread().getName() + "] Completed");
                }
            }
        }, "sms").start();

        new Thread(() -> {
            synchronized (topic) {
                System.out.println("[" + Thread.currentThread().getName() + "] Publishing Event");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                } finally {
                    topic.notifyAll();
                }
            }
        }, "topic-publisher").start();
    }

    static class Topic {
        String name;

        public Topic(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
