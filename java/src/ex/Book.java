package ex;

public class Book {
	private String title;
	private int price;
	
	public Book() {
		super();
	}
	
	public Book(String title, int price) {
		super();
		this.title = title;
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}



	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	
	
	public void print() {
		System.out.printf("%-12s : %10d\n",title,price);
	}
}
