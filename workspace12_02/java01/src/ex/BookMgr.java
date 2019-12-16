package ex;

import java.util.ArrayList;
import java.util.List;

public class BookMgr {
	List<Book> booklist = null;

	public BookMgr() {
		booklist = new ArrayList<Book>();
	}

	public void addBook(Book book) {
		booklist.add(book);
	}

	public void printBookList() {
		System.out.println("*******Book Info********");
		System.out.println();
		for (int i = 0; i < booklist.size(); i++) {
			booklist.get(i).print();
		}
	}

	public void printTotalPrice() {
		int sum = 0;
		for (int i = 0; i < booklist.size(); i++) {
			sum += booklist.get(i).getPrice();
		}
		System.out.printf("%-12s : %10d\n", "Total Price ", sum);
	}

	public void searchTitle(String s) {
		boolean check = false;
		System.out.println();
		System.out.println("*******Book Search********");
		for (int i = 0; i < booklist.size(); i++) {
			if (booklist.get(i).getTitle().toLowerCase().contains(s.toLowerCase())) {
				System.out.printf("index: %d \n", i);
				System.out.printf("title: %s, price: %d \n\n", booklist.get(i).getTitle(), booklist.get(i).getPrice());
				check = true;
			}
		}
		if (check) {
		} else {
			System.out.println("there is no book like that.");
		}
	}
}
