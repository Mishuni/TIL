package day07;

public class Test05 {

	public static void main(String[] args) {
		Dog[] d;
		Fish[] f;
		Animal[] a = {
			new Dog("puppy","momo")
			, new Fish("moim"),
			new Fish("ruru"),
			new Dog("siberian","happy"),
			new Animal("it's me")
		};
		
		for(Animal data : a) {
			data.breath();
			if(data instanceof Dog) {
				((Dog) data).print();
			}
			else if(data instanceof Fish) {
				((Fish)data).print();
			}
			else {
				System.out.println(data.kind);
			}
		}
		
	}

}
