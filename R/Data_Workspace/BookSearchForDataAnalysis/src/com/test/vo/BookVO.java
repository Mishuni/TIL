package com.test.vo;

public class BookVO {

	private String img;
	private String title;
	private String author;
	private String price;
	private String isbn;
	
	public BookVO() {		
	}

	public BookVO(String img, String title, String author, String price, String isbn) {
		super();
		this.img = img;
		this.title = title;
		this.author = author;
		this.price = price;
		this.isbn = isbn;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	
}
