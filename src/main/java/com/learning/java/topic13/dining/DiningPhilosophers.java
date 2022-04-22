package com.learning.java.topic13.dining;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DiningPhilosophers {

	static Lock spoon1 = new ReentrantLock(); 
	static Lock spoon2 = new ReentrantLock(); 
	static Lock spoon3 = new ReentrantLock(); 
	static Lock spoon4 = new ReentrantLock(); 
	static Lock spoon5 = new ReentrantLock(); 
	
	
	public static void main(String[] args) {
		 new Thread(() -> {
			spoon2.lock();
			spoon1.lock();
			System.out.println("Philisoper 1 is eating");
			spoon1.unlock();
			spoon2.unlock();
		 }).start();
		 
		 new Thread(() -> {
				spoon3.lock();
				spoon2.lock();
				System.out.println("Philisoper 2 is eating");
				spoon2.unlock();
				spoon3.unlock();
			 }).start();
		 
		 new Thread(() -> {
				spoon4.lock();
				spoon3.lock();
				System.out.println("Philisoper 3 is eating");
				spoon3.unlock();
				spoon4.unlock();
			 }).start();
		 
		 new Thread(() -> {
				spoon5.lock();
				spoon4.lock();
				System.out.println("Philisoper 4 is eating");
				spoon4.unlock();
				spoon5.unlock();
			 }).start();
		 
		 new Thread(() -> {
				spoon1.lock();
				spoon5.lock();
				System.out.println("Philisoper 5 is eating");
				spoon5.unlock();
				spoon1.unlock();
			 }).start();


	}

}
