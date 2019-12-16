package day10;

public class Test01 {

	public static void main(String[] args) {
		Employee<String> emp1 = new Employee<String>("Hi","2019001");
		System.out.println(emp1);
		
		Employee<Integer> emp2 = new Employee<Integer>("Go",2019002);
		System.out.println(emp2);
 		
		Employee emp3 = new Employee("Rr",2019003);
		System.out.println(emp3);
		
		}

}

class Employee<T>{
	
	String name;
	T number;
	
	public Employee(String name, T number) {
		super();
		this.name = name;
		this.number = number;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", number=" + number + "]";
	}
	
}