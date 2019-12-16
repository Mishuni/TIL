package day07;

public class Teacher extends Person{
	
	String subject;

	
	public Teacher() {
		super();
	}

	public Teacher(String subject) {
		super();
		this.subject = subject;
	}

	public Teacher(String name, int age, String subject) {
		super(name, age);
		this.subject = subject;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public void print() {
		System.out.printf("이  름:%s\t나  이:%-5d\t담당과목:%-5s\n"
				,this.getName(),this.getAge(), this.getSubject());
	}

}
