package com.itcast.thread.traditional.synchronize;
/**
 * 子线程循环10次，接着主线程循环100，接着又回到子线程循环10次，
 * 接着再回到主线程循环100次，如此循环50次，请写出程序。
 * @author adminitrator
 *
 */
public class TraditionalThreadCommunication {
	public static void main(String[] args) {
		Business b = new Business();
		//子线程
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i=1;i<=50;i++){
					b.sub(i);
				}
			}
		}).start();
		
		//主线程
		for(int i=1;i<=50;i++){
			b.main(i);
		}
	}
}

class Business{
	public static boolean flag = true;
	
	public synchronized void sub(int i){
		try {
			while(flag){
				this.wait();
			}
			flag = true;
			for(int j=1;j<+10;j++){
				System.out.println("sub thread sequence of -->" + i);
			}
			this.notify();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public synchronized void main(int i){
		try {
			while(!flag){
				this.wait();
			}
			flag = false;
			for(int j=1;j<=10;j++){
				System.out.println("main thread sequence of -->" + i);
			}
			this.notify();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}