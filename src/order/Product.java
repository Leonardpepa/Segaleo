package order;

import java.io.Serializable;

/*	Abstract class that holds data that other products share like name, description, price, path for the image etc...
 * 	
 * 
 */
import java.util.ArrayList;

import rating.Rating;
public abstract class Product implements Serializable {
	
	private static final long serialVersionUID = -5664405825052016146L;
	
	protected String name;
	protected String description;
	protected double price;
	protected String path;
	protected ArrayList<Rating> ratings;
	protected int id;
	
	
	public Product(String name, String description, double price, String path, int id) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.path = path;
		this.ratings = new ArrayList<Rating>();
		this.id = id;
	}

	//it needs to calculate the average rating
	public abstract double calcAvRating();
	
	/* overrides the hash code method so it returns a unique number based on id*/
	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;  
        return result;
    }
	
	
	/* ovverrides equal method so we can use this class as a key in the hash map we use in teh order*/
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
