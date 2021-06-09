package order;

import java.io.Serializable;
import java.util.ArrayList;

import rating.Rating;
public abstract class Product implements Serializable {
	private String name;
	private String description;
	private double price;
	private String path;
	private ArrayList<Rating> ratings;
	private int id;
	
	
	public Product(String name, String description, double price, String path, int id) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.path = path;
		this.ratings = new ArrayList<Rating>();
		this.id = id;
	}

	public double calcAvRating() {
		double sum = 0;
		for(Rating rating: ratings) {
			sum += rating.getNumOfStars();
		}
		return ratings.isEmpty() ? 0 :sum/ratings.size();
	}
	
	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;  
        return result;
    }
	
	
	//Compare only account numbers
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Product other = (Product) obj;
        if (id != other.getId()) {
        	return false;        	
        }
        return true;
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
	public int getId() {
		return id;
	}
}
