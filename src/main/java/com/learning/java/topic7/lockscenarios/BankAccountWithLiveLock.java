package com.learning.java.topic7.lockscenarios;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccountWithLiveLock {

    final Lock lock = new ReentrantLock();
    private double balance;
    private String id;

    public BankAccountWithLiveLock(String id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    public static void main(String[] args) {
        BankAccountWithLiveLock studentBankAccount = new BankAccountWithLiveLock("StudentAcc", 50000);
        BankAccountWithLiveLock universityBankAccount = new BankAccountWithLiveLock("UniversityAcc", 100000);
        System.out.println("Student Balance :: " + studentBankAccount.balance);
        System.out.println("University Balance :: " + universityBankAccount.balance);

        new Thread(() -> {
            try {
                while (!studentBankAccount.transfer(studentBankAccount, universityBankAccount, 3000)) {
                    System.out.println(">> Student Balance :: " + studentBankAccount.balance);
                    continue;
                }
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                while (!universityBankAccount.transfer(universityBankAccount, studentBankAccount, 1000)) {
                    System.out.println(">> University Balance :: " + universityBankAccount.balance);
                    continue;
                }
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }).start();

    }

    public boolean withdraw(double amount) throws InterruptedException {
        System.out.println("[" + id + "] Withdraw :: Trying Acquire Lock for " + amount);
        if (this.lock.tryLock()) {
            System.out.println("[" + id + "] Withdraw :: Lock Acquired for " + amount);
            Thread.sleep(1000);
            balance = balance - amount;
            return true;
        }
        System.out.println("[" + id + "] Withdraw :: No Lock Acquired  for " + amount);
        return false;
    }

    public boolean transfer(BankAccountWithLiveLock from, BankAccountWithLiveLock to, double amount) throws InterruptedException {
        if (from.withdraw(amount)) {
            System.out.println("[" + id + "] Withdraw Amount: " + amount);
            if (to.deposit(amount, from.id)) {
                System.out.println("[" + id + "] Deposit Amount: " + amount);
                return true;
            } else {
                from.deposit(amount, from.id);
                System.out.println("[" + id + "] Reverting Amount: " + amount);
            }
        }
        return false;
    }

    public boolean deposit(double amount, String depositeId) throws InterruptedException {
        System.out.println("[" + depositeId + "] Deposit :: Trying Acquire Lock on " + id + " for " + amount);
        if (this.lock.tryLock()) {
            System.out.println("[" + depositeId + "] Deposit :: Lock Acquired " + id + " for " + amount);
            Thread.sleep(1000);
            balance = balance + amount;
            return true;
        }
        System.out.println("[" + depositeId + "] Deposit :: No Lock Acquired " + id + " for " + amount);
        return false;
    }

}

















