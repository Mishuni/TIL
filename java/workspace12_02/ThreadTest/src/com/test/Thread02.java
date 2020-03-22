package com.test;

class SharedArea {
	double result;
	boolean isWritten;
}

class ComputeThread extends Thread{
	
	SharedArea sharedArea;
	public void run() {
		// pi calculation
		double res = 0.0;
		for(int i=1; i<100000; i+=2) {
			if(i/2%2==0) res+=1.0/i;
			else res -= 1.0/i;
		}
		sharedArea.result=res*4;
		sharedArea.isWritten=true;
	}
	
}

class PrintThread extends Thread{
	SharedArea sharedArea;
	public void run() {
		while(!sharedArea.isWritten) {
			continue;
		}

		System.out.println(sharedArea.result);

		
	}
}

public class Thread02 {

	public static void main(String[] args) {
		ComputeThread thread1 = new ComputeThread();
		PrintThread thread2 = new PrintThread();
		SharedArea SH = new SharedArea();
		
		thread1.sharedArea = SH;
		thread2.sharedArea = SH;
		
		thread1.start();
		thread2.start();
		
	}

}
