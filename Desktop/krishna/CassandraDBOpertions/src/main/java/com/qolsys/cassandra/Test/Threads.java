package com.qolsys.cassandra.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Threads {
	public static class ThreadExample implements Runnable{

		public void run() {
			for(int i=0; i<10; i++){
				System.out.println("First Thread:: "+ i);
				try{
					Thread.sleep(50);
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
			System.out.println("First Thread Finished");
		}	
	}
		
	public static void main(String[] args)throws Exception{
		// TODO Auto-generated method stub
		ExecutorService executor = Executors.newFixedThreadPool(5);
		executor.submit(new ThreadExample() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
					for(int i=0; i<10; i++){
						System.out.println("2nd Thread:: "+ i);
						try{
							Thread.sleep(100);
						}catch (Exception e) {
							e.printStackTrace();
						}
					}
					System.out.println("2nd Thread Finished");
			}
		});
	}
}
