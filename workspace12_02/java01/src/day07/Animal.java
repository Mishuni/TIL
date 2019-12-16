package day07;

public class Animal {
	String kind ="animal";
	
	
	public Animal() {
		super();
	}

	public Animal(String kind) {
		super();
		this.kind = kind;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}
	
	
	public void breath() {
		System.out.println("lug");
	}
}
