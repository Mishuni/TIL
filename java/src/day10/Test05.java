package day10;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Test05 {

	public static void main(String[] args) {
		Set<String> set = new HashSet<String>();
		set.add("A");
		set.add("S");
		set.add("D");
		set.add("S");
		set.add("B");
		set.add("C");
		set.add("Z");
		IteratorPrint(set);
		System.out.println();
		Set<String> set2 = new TreeSet<String>();
		set2.add("A");
		set2.add("S");
		set2.add("B");
		set2.add("D");
		set2.add("C");
		set2.add("Z");
		IteratorPrint(set2);
	}
	
	public static void IteratorPrint(Set<String> a) {
		Iterator<String> it = a.iterator();
		while(it.hasNext()) {
			System.out.print(it.next()+" ");
		}
	}

}
