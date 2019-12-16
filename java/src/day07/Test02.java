package day07;

public class Test02 {

	public static void main(String[] args) {
		
		Dog d = new Dog ();
		System.out.println(d.kind);
		System.out.println(((Animal)d).kind);
		Object obj2 = d;
		Animal a3 = (Animal)obj2;
		System.out.println(a3.kind);
		Dog d3 = (Dog)obj2;
		Object obj7;
		
		
		
	}

}
