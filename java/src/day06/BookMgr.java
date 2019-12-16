package day06;

public class BookMgr {
	Book[] booklist = null;
	int cnt = 0;

	public BookMgr() {
		booklist = new Book[10];
	}

	public BookMgr(int size) {
		booklist = new Book[size];
	}

	public void addBook(Book book) {
		if (cnt == booklist.length) {
			Book[] tmp = new Book[booklist.length * 2];
			System.arraycopy(booklist, 0, tmp, 0, booklist.length);
			booklist = tmp;
			tmp = null;
		}
		booklist[cnt] = book;
		cnt++;

	}

	public void printBookList() {
		System.out.println("*******Book Info********");
		System.out.println();
		for (int i = 0; i < cnt; i++) {
			booklist[i].print();
		}
	}

	public void printTotalPrice() {
		int sum = 0;
		for (int i = 0; i < cnt; i++) {
			sum += booklist[i].getPrice();
		}
		System.out.printf("%11s : %6d\n", "Total Price ", sum);
	}

	public void searchTitle(String s) {
		boolean check = false;
		System.out.println();
		System.out.println("*******Book Search********");
		for (int i = 0; i < cnt; i++) {
			if (booklist[i].getTitle().toLowerCase().contains(s.toLowerCase())) {
				System.out.printf("index: %d \n", i);
				System.out.printf("title: %s, price: %d \n\n", booklist[i].getTitle(), booklist[i].getPrice());
				check = true;
			}
		}
		if (check) {
		} else {
			System.out.println("there is no book like that.");
		}
	}
}
