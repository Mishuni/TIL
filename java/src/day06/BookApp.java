package day06;

import javax.swing.JOptionPane;

public class BookApp {

	public static void main(String[] args) {
		BookMgr mgr = new BookMgr(3);
		Book book = new Book("Java", 10000);
		mgr.addBook(book);
		mgr.addBook(new Book("sql", 20000));
		mgr.addBook(new Book("Human", 10000));
		mgr.addBook(new Book("hekdn", 20000));
		
		String title = JOptionPane.showInputDialog("Insert title");
		
		mgr.printBookList();
		mgr.printTotalPrice();
		mgr.searchTitle(title);
	}
	

}
