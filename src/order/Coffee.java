package order;

import rating.Rating;

public class Coffee  extends Product{

	private static final long serialVersionUID = -4148346724991891535L;
	
	private int size = 0;
	private int sugar = 0;
	private boolean cold = true;
	
	public Coffee(String name, String description, double price, String path, int id) {
		super(name, description, price, path, id);
	}
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}

	public int getSugar() {
		return sugar;
	}

	public void setSugar(int sugar) {
		this.sugar = sugar;
	}

	public boolean isCold() {
		return cold;
	}

	public void setCold(boolean cold) {
		this.cold = cold;
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

