package model;

import java.io.Serializable;

public class Book implements Serializable{
	private String name;
	private String image;
	private String title;
	private String author;
	private String isbn;
	private String price;
	private String date;
	private String register;
	private String status;
	private String evaluation;
	private String overview;
	private String review;
	
	public Book() {}
	
	public Book(String name,String image,String title,String author,String isbn,String price,
			String date,String register,String status,String evaluation,String overview,String review) {
		this.name = name;
		this.image = image;
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.price = price;
		this.date = date;
		this.register = register;
		this.status = status;
		this.evaluation = evaluation;
		this.overview = overview;
		this.review = review;
	}
	public  Book(String name,String title,String  author,
			String isbn,String price,String date,String register,String status) {
		this.name= name;
		this.title=title;
		this.author=author;
		this.isbn=isbn;
		this.price=price;
		this.date=date;
		this.register=register;
		this.status=status;
	}
	public Book(String name,String title,String author,String isbn,String price,String date,String register,String status,String evaluation,String overview,String
			review){
		this.name = name;
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.price = price;
		this.date = date;
		this.register = register;
		this.status = status;
		this.evaluation = evaluation;
		this.overview = overview;
		this.review = review;
	}
	public Book(String name,String image,String title,String author,String isbn,String price,String date,String register,String status) {
		this.name = name;
		this.image = image;
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.price = price;
		this.date = date;
		this.register = register;
		this.status = status;
	}
	public Book(String name,String title,String date,String status,String evaluation,String overview,
			String review) {
		this.name = name;
		this.title = title;
		this.date = date;
		this.status = status;
		this.evaluation = evaluation;
		this.overview = overview;
		this.review = review;
	}

	public String getName() {
		return name;
	}

	public String getImage() {
		return image;
	}

	public String getTitle() {
		return title;
	}
	
	public String getAuthor() {
		return author;
	}
	public String getIsbn() {
		return isbn;
	}

	public String getPrice() {
		return price;
	}
		
	public String getDate() {
		return date;
	}
	public String getRegister() {
		return register;
	}

	public String getStatus() {
		return status;
	}

	public String getEvaluation() {
		return evaluation;
	}

	public String getOverview() {
		return overview;
	}

	public String getReview() {
		return review;
	}
	
	
}
