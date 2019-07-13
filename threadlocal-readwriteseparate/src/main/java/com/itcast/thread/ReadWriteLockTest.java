package com.itcast.thread;

import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {
	public static void main(String[] args) {
		final Queue3 q = new Queue3();
		for(int i=0;i<3;i++){
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					while(true)
						q.get();
					
				}
			}).start();
		}
		
		for(int i=0;i<3;i++){
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					while(true)
						q.put(new Random().nextInt(1000));
				}
			}).start();
		}
	}
}
class Queue3{
	private Object data = null;
	private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	public void get(){
		rwl.readLock().lock();
		try {
			System.out.println(Thread.currentThread().getName() + " is ready to read data!");
			Thread.sleep((long)Math.random() * 1000);
			System.out.println(Thread.currentThread().getName() + " has read data : "+ data);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			rwl.readLock().unlock();
		}

	}
	public void put(Object data){
		rwl.writeLock().lock();
		try {
			System.out.println(Thread.currentThread().getName() + " is ready to write data!");
			Thread.sleep((long)Math.random() * 1000);
			this.data = data;
			System.out.println(Thread.currentThread().getName() + " has write data : "+ data);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			rwl.writeLock().unlock();
		}

	}
}