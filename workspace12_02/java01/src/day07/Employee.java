package day07;

public class Employee extends Person{
	private String dept;
	
	public Employee() {
		super();
	}


	public Employee(String name, int age, String dept) {
		super(name, age);
		this.dept = dept;
	}

	public Employee(String name, int age) {
		super(name, age);
	}


	public Employee(String dept) {
		super();
		this.dept = dept;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}
	
	public void print() {
		System.out.printf("이  름:%s\t나  이:%-5d\t부      서:%-5s\n"
				,this.getName(),this.getAge(), this.getDept());
	}
}
