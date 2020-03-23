package com.test;

import javax.swing.JOptionPane;

public class Thread01 {
	public static void main(String[] args) {
		
		Thread th = new Thread(new Thread01_1());
		th.start();
		
		String input = JOptionPane.showInputDialog("Put your message");
		System.out.println(input);
		
	}
}

class Thread01_1 implements Runnable {

	@Override
	public void run() {

		for (int i = 1; i <= 1000000; ++i) {
			System.out.println((i) + "번째 Thread");
		}

	}

}