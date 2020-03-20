package com.test;

// interface로 구현
class MyRunnable implements Runnable {

	@Override
	public void run() {
		int i = 1;
		while(i<=10)
			System.out.println(i+++"번째 MyRunnable 입니다.");
		
		
	}
	
}


// 상속으로 thread 구현
class MyThread extends Thread{
	// 독립적인 실행 흐름
	
	@Override
	public void run() {
		super.run();
		int i = 1;
		while(i<=10)
			System.out.println(i+++"번째 MyThread 입니다.");
		
	}
}

public class ThreadTest {

	public static void main(String[] args) {
		System.out.println("시작");
		MyThread t1 = new MyThread();
		MyRunnable runnable = new MyRunnable();
		// 위 객체를 thread로 만들기
		Thread t2 = new Thread(runnable);
		
		// jvm 에 요청만 하고 넘김 ( 결과를 기다리지 않음)
		// non-blocking 방식
		t1.start(); 
		t2.start();
		
		int i = 1;
		while(i<=10)
			System.out.println(i+++"번째 Main 입니다.");
	
		
		
		System.out.println("끝");
		
	}

}
