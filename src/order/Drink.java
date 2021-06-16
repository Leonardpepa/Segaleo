package order;

import rating.Rating;

public class Drink extends Product{

	private static final long serialVersionUID = -2429031331393022338L;

	private double alcoholPercentage;
	
	public Drink(String name, String description, double price, String path, double alcoholPercentage, int id) {
		super(name, description, price, path, id);
		this.alcoholPercentage = alcoholPercentage;
	}

	public double getAlcoholPercentage() {
		return alcoholPercentage;
	}

	public void setAlcoholPercentage(double alcoholPercentage) {
		this.alcoholPercentage = alcoholPercentage;
	}

	@Override
	public double calcAvRating() {
		double sum = 0;
		for(Rating rating: ratings) {
			sum += rating.getNumOfStars();
		}
		return ratings.isEmpty() ? 0 :sum/ratings.size();
	}
	

}
