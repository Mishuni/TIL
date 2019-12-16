package day10;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;

public class Test04 {

	public static void main(String[] args) {
		List<String> a = new ArrayList<String>();
		//List<String> a = new Vector<String>();
		a.add("a");
		a.add("b");
		
		System.out.println(a.contains("a"));
		System.out.println(a.contains("c"));
		
		Iterator<String> it = a.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		System.out.println(a);
		a.add(1,"~~");
		System.out.println(a);
	}

}
