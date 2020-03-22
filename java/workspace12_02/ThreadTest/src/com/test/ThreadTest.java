package com.test;

// interface�� ����
class MyRunnable implements Runnable {

	@Override
	public void run() {
		int i = 1;
		while(i<=10)
			System.out.println(i+++"��° MyRunnable �Դϴ�.");
		
		
	}
	
}


// ������� thread ����
class MyThread extends Thread{
	// �������� ���� �帧
	
	@Override
	public void run() {
		super.run();
		int i = 1;
		while(i<=10)
			System.out.println(i+++"��° MyThread �Դϴ�.");
		
	}
}

public class ThreadTest {

	public static void main(String[] args) {
		System.out.println("����");
		MyThread t1 = new MyThread();
		MyRunnable runnable = new MyRunnable();
		// �� ��ü�� thread�� �����
		Thread t2 = new Thread(runnable);
		
		// jvm �� ��û�� �ϰ� �ѱ� ( ����� ��ٸ��� ����)
		// non-blocking ���
		t1.start(); 
		t2.start();
		
		int i = 1;
		while(i<=10)
			System.out.println(i+++"��° Main �Դϴ�.");
	
		
		
		System.out.println("��");
		
	}

}
