package order;

import java.util.ArrayList;

import rating.Rating;

public abstract class Product {
	private String name;
	private String description;
	private double price;
	private String path;
	private ArrayList<Rating> ratings;
	
	public Product(String name, String description, double price, String path) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.path = path;
		this.ratings = new ArrayList<Rating>();
	}

	
	public void addRating(Rating rating) {
		ratings.add(rating);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
