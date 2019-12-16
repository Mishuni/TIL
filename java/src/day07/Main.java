package day07;

public class Main {

	public static void main(String[] args) {
		Person[] p = {
				new Student("홍길동",20,300302),
				new Teacher("이순신",30,"JAVA"),
				new Employee("유관순",40,"교무과")
		};
		
		for(Person data: p) {
			if(data instanceof Student) {
				((Student)data).print();
			}
			else if(data instanceof Teacher) {
				((Teacher)data).print();
			}
			else if(data instanceof Employee) {
				((Employee)data).print();
			}
		}
	}

}
