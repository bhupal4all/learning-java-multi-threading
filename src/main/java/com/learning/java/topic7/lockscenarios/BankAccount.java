package com.learning.java.topic7.lockscenarios;

public class BankAccount {
	
	private double balance;
	private String id;
	
	public BankAccount(String id, double balance) {
		this.id=id;
		this.balance=balance;
	}
	
	public void withdraw(double amount) throws InterruptedException {
		Thread.sleep(1000);
		this.balance=this.balance-amount; 
	}
	
	public void deposit(double amount) throws InterruptedException {
		Thread.sleep(1000);
		this.balance=this.balance+amount; 
	}
	
	public static void transfer(BankAccount from, BankAccount to, double amount) throws InterruptedException {
		synchronized(from) {
			from.withdraw(amount);
			System.out.println ("Withdrawing: " + amount  + " from : " + from.id); 
			synchronized(to) {
				to.deposit(amount);
				System.out.println("Depositing: " + amount + " to: " + to.id);
			}
		}
	}
	

	public static void main(String[] args) {
		BankAccount studentBankAccount = new BankAccount("StudentAcc", 30000);
		BankAccount universityBankAccount = new BankAccount("UniversityAcc", 100000);
		
		new Thread (() ->  {
			try {
				BankAccount.transfer(studentBankAccount, universityBankAccount, 3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}).start();
		
		new Thread (() ->  {
			try {
				BankAccount.transfer(universityBankAccount, studentBankAccount, 1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}).start();
	

	}

}








