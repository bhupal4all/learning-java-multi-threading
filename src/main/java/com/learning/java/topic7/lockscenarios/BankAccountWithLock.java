package com.learning.java.topic7.lockscenarios;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccountWithLock {
	private double balance; 
	private String id;
	private Lock lock = new ReentrantLock();

	public BankAccountWithLock (String id, double balance) {
		this.balance=balance; 
		this.id=id; 
	}
	
	public void withdraw(double amount) throws InterruptedException {
		Thread.sleep(1000);
		this.balance=this.balance-amount; 
	}
	
	public void deposit (double amount) throws InterruptedException {
		Thread.sleep(1000);
		this.balance=this.balance+amount; 
	}
	
	public static void transfer (BankAccountWithLock from, BankAccountWithLock to, double amount) throws InterruptedException {
		from.lock.lock();
		from.withdraw(amount);
		System.out.println("Withdrawing " + amount + " from:" + from.id); 
		to.lock.lock();
		to.deposit(amount);
		System.out.println("Depositing" + amount + " to: " + to.id);
		to.lock.unlock();
		from.lock.unlock();
	}

	public static void main(String[] args) {
		BankAccountWithLock studentBankAccount = new BankAccountWithLock("StudentAcc", 30000);
		BankAccountWithLock universityBankAccount = new BankAccountWithLock("UniversityAcc", 100000);
		
		new Thread(() -> {
			try {
				BankAccountWithLock.transfer(studentBankAccount, universityBankAccount, 3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}).start();
		
		new Thread(() ->{
			try {
				BankAccountWithLock.transfer(universityBankAccount, studentBankAccount, 1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}).start();

	}

}







