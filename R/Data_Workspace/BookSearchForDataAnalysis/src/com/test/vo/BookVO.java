package com.test.vo;

public class BookVO {

	private String img;
	private String title;
	private String author;
	private int price;
	private String isbn;
	

    private String date;
    private int page;
    private String translator;
    private String supplement;
    private String publisher;
    private String imgbase64;
	
	public BookVO() {		
	}

	public BookVO(String img, String title, String author, int price, String isbn) {
		super();
		this.img = img;
		this.title = title;
		this.author = author;
		this.price = price;
		this.isbn = isbn;
	}

	
	public BookVO(String img, String title, String author, int price, String isbn,
			String date, int page, String translator, String supplement, String publisher,
			String imagbase) {
		super();
		this.img = img;
		this.title = title;
		this.author = author;
		this.price = price;
		this.isbn = isbn;
		this.date = date;
		this.page = page;
		this.translator = translator;
		this.supplement = supplement;
		this.publisher = publisher;
		this.imgbase64 = imagbase;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getTranslator() {
		return translator;
	}

	public void setTranslator(String translator) {
		this.translator = translator;
	}

	public String getSupplement() {
		return supplement;
	}

	public void setSupplement(String supplement) {
		this.supplement = supplement;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getImgbase64() {
		return imgbase64;
	}

	public void setImgbase64(String imgbase64) {
		this.imgbase64 = imgbase64;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	
}
