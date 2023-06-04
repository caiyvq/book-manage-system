package model;

public class Book {

	private String no;
	private String name;
	private String author;
	private String type;
	private String publisher;
	private int storage;
	
	
	public Book() {
		super();
	}

	public Book(String no, String name, String author, String type, String publisher) {
		super();
		this.no = no;
		this.name = name;
		this.author = author;
		this.type = type;
		this.publisher = publisher;
	}

	public Book(String no, String name, String author, String type, String publisher, int storage) {
		super();
		this.no=no;
		this.name = name;
		this.author = author;
		this.type = type;
		this.publisher = publisher;
		this.storage = storage;
	}
	
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public int getStorage() {
		return storage;
	}
	public void setStorage(int storage) {
		this.storage = storage;
	}
	
	
}
