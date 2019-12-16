package ex;

import javax.swing.JOptionPane;

public class BookApp {

	public static void main(String[] args) {
		BookMgr mgr = new BookMgr();
		Book book = new Book("Java", 10000);
		mgr.addBook(book);
		mgr.addBook(new Book("sql", 20000));
		mgr.addBook(new Book("Human", 10000));
		mgr.addBook(new Book("hekdn", 20000));
		
		while(true) {
			String choice = JOptionPane.showInputDialog("Action: \n"
					+ "1: show, 2: total, 3: search, 4: add, 0: quit");
			if(choice.equals("0")) {
				break;
			}
			else if(choice.equals("1")) {
				mgr.printBookList();
			}
			else if(choice.equals("2")) {
				mgr.printTotalPrice();
			}
			else if(choice.equals("3")) {
				String title = JOptionPane.showInputDialog("Search:");
				mgr.searchTitle(title);
			}
			else if(choice.equals("4")) {
				String title = JOptionPane.showInputDialog("Title:");
				String price = JOptionPane.showInputDialog("Price:");
				try {
				mgr.addBook(new Book(title,Integer.parseInt(price)));
				}
				catch(NumberFormatException e){
					System.out.println("Price is number");
				}
				
			}
			else {
				System.out.println("Wrong Number");
			}
			
		}
		System.out.println("------END------");
		return;
	}
	

}
