package com.test;

class SharedArea02{
	
	Account Acc1;
	Account Acc2;
	
}

class Account{
	
	String accountNo;
	String ownerName;
	int balance;
	
	Account(String accountNo, String ownerName,
			int balance){
		this.accountNo = accountNo;
		this.ownerName = ownerName;
		this.balance = balance;
	}
	
	void deposit(int amount) {
		this.balance += amount;
	}
	
	int withdraw(int amount) {
		if(balance < amount) {
			return 0;
		}
		balance -= amount;
		return amount;
	}
}

class TransferThread extends Thread{
	
	int money;
	SharedArea02 sharedArea;
	TransferThread(SharedArea02 area){
		sharedArea = area;
	}
	public void run() {
		for(int month=1; month<=100; ++month) {
			// 객체를 동기화 하기
			synchronized(sharedArea) {
				
				// 동기화 되면 Acc1에서 Acc2로 송금
				money = sharedArea.Acc1.withdraw(1000);
				System.out.println("Month "+month+": 1000 drawal from Acc1");
				
				sharedArea.Acc2.deposit(money);
				System.out.println("Month "+month+": 1000 deposit in Acc2");
				
				try {
					Thread.sleep(1000);
				}catch(Exception e) {
					
				}
				
			}
		}
	}	
}

class PThread extends Thread{
	SharedArea02 sharedArea;
	PThread(SharedArea02 area){
		this.sharedArea = area;
	}
	public void run() {
		for(int i=0; i<10; ++i) {
			synchronized(sharedArea) {
				int money_sum = sharedArea.Acc1.balance+sharedArea.Acc2.balance;
				System.out.println("Sum : "+money_sum);
				
			}
			try {
				Thread.sleep(1);
			}catch(Exception e) {
				
			}
		}
	}
}

public class Thread03 {

	public static void main(String[] args) {
		
		SharedArea02 sharedArea = new SharedArea02();
		
		sharedArea.Acc1=new Account("Tom","111",3000000);
		sharedArea.Acc2=new Account("Jane","222",1000000);
		
		TransferThread thread1 = new TransferThread(sharedArea);
		PThread thread2 = new PThread(sharedArea);
		
		// 결과적으로 thread1 이 for문 돌릴 동안
		// 공용 자원을 계속 쥐고 있으므로
		// thread2 는 진행되지 못함
		// 따라서, thread1에서 송금 작업 후에 (공용 자원 안 쓰는 때에)
		// 틈새로 혹은 아예 다 끝나고 thread2 가 동작
		thread1.start();
		thread2.start();
	}

}
