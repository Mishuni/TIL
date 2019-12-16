package day07;

public class Dog extends Animal{
	
	String kind = "dog";
	String name ;

	public Dog() {
		super("cat");
	}

	public Dog(String kind, String name) {
		super();
		this.kind = kind;
		this.name = name;
	}
	
	public String getSuperKind() {
		return super.kind;
	}

	public void print() {
		System.out.printf("%s, %s, %s \n", super.kind, 
				this.kind, this.name);
	}
	
	public void breath() {
		System.out.println("wow");
	}

}
