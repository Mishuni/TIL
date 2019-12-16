package day07;

import javax.swing.JOptionPane;

public class ThreadEx6 {
	public static void main(String[] args) {
		ThreadEx7 th1 = new ThreadEx7();
		int a;
		th1.start();
		String input = JOptionPane.showInputDialog("in");
		System.out.println("input is "+input);
		for(int i=10; i>0; i--) {
			a=i;
			System.out.println(i+".v2");
			try {
				Thread.sleep(1000);
			}catch(Exception e) {
				
			}
		}
	}
	
}

class ThreadEx7 extends Thread{
	public void run() {
		for(int i=10; i>0; i--) {
			System.out.println(i+".v1");
			try {sleep(1000);}catch(Exception e){}
		}
	}
}
