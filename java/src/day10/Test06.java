package day10;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JOptionPane;

public class Test06 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Set<Book> booklist = new HashSet<Book>();
		// Register
		if(booklist.add(new Book("Java",31000))) {
			System.out.println("Registration Complete.");
		}else {
			System.out.println("Duplicated Contents.");
		}
		
		booklist.add(new Book("SQL",15000));
		booklist.add(new Book("JSP",16000));
		booklist.add(new Book("HTML",30000));
		booklist.add(new Book("Java",31000));
		booklist.add(new Book("Fish",31000));
		booklist.add(new Book("Love is good",31000));
		booklist.add(new Book("Making decision",31000));
		
		// Print
		Iterator<Book> it = booklist.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		System.out.println();
		
		// Search
		String keyword = JOptionPane.showInputDialog("What is the title you want?");
		it = booklist.iterator();
		System.out.println("The result is...");
		while(it.hasNext()) {
			Book tmp = it.next();
			if(tmp.title.toLowerCase().contains(keyword.toLowerCase())) {
				System.out.println(tmp);
			}
		}
		System.out.println();
		
		// Remove
		keyword = sc.nextLine().trim();
		it = booklist.iterator();
		while(it.hasNext()) {
			Book tmp = (Book)it.next();
			if(tmp.title.toLowerCase().contains(keyword.toLowerCase())) {
				System.out.println(tmp);
				keyword = sc.nextLine().trim();
				if(keyword.equalsIgnoreCase("y")) {
					it.remove();
				}
			}
		}
		System.out.println(booklist);
		
		// Edit
		System.out.println("what book is you want to edit?");
		String msg = sc.next();
		it = booklist.iterator();
		System.out.println("The result is...");
		while(it.hasNext()) {
			Book tmp = it.next();
			if(tmp.title.contains(msg)) {
				tmp.title = tmp.title+"_____";
			}
		}
		System.out.println(booklist);
	}

}

class Book {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + price;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (price != other.price)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	String title;
	int price;
	public Book(String title, int price) {
		super();
		this.title = title;
		this.price = price;
	}
	@Override
	public String toString() {
		return "Book [title=" + title + ", price=" + price + "]\n";
	}

	
}