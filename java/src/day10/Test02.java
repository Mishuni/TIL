package day10;

public class Test02 {

	public static void main(String[] args) {
	
		Employee2<String, Integer> emp2 = 
				new Employee2<String,Integer>("Ha", 3029);
		System.out.println(emp2);
		System.out.println(emp2.name.getClass());
		Employee2 emp4 = new Employee2("GO",201701);
		System.out.println(emp4);
		System.out.println(emp4.number.getClass());
	}

}

class Employee2<T, P extends Number> {
	T name;
	P number;

	public Employee2(T name, P number) {
		super();
		this.name = name;
		this.number = number;
	}

	@Override
	public String toString() {
		return "Employee2 [name=" + name + ", number=" + number + "]";
	}

}
