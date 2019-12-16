package day05;

public class Test05 {

	public static void main(String[] args) {
		Employee emp1 = new Employee();
		Employee emp2 = new Employee();
		emp1.print();
		emp2.print();
	}

}

class Employee {
	String name;
	String dept;
	int age;
	
	public void print() {
		System.out.println(name);
	}
}
