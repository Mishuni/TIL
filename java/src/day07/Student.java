package day07;

public class Student extends Person{
	int id;
	
	public Student() {
		super();
	}

	public Student(int id) {
		super();
		this.id = id;
	}
	
	public Student(String name, int age) {
		super(name, age);
	}


	public Student(String name, int age, int id) {
		super(name, age);
		this.id = id;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void print() {
		System.out.printf("이  름:%s\t나  이:%-5d\t학      번:%-5d \n"
				,this.getName(),this.getAge(), this.getId());
	}
	
}
