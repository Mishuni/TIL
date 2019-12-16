package day07;

public class Fish extends Animal {
	
	public Fish() {
		super("fish");
	}

	public Fish(String name) {
		super("fish");
		this.name = name;
	}

	String name;

	public void print() {
		System.out.printf("%s, %s\n",kind,name);
	}
	
	@Override
	public void breath() {
		System.out.println("mimi");
	}

}
