package order;

import rating.Rating;

public class Food extends Product{
	
	private static final long serialVersionUID = -5957460565520834132L;
	
	private boolean hasDiscount = false;
	private int discount = 0;
	
	public Food(String name, String description, double price, String path, int id) {
		super(name, description, price, path, id);
	}
	public boolean isHasDiscount() {
		return hasDiscount;
	}
	public void setHasDiscount(boolean hasDiscount, int discount) {
		this.hasDiscount = hasDiscount;
		setDiscount(discount);
		if(hasDiscount) {
			this.setPrice(this.getPrice() - discount);
		}
	}
	
	public int getDiscount() {
		return discount;
	}
	
	public void setDiscount(int discount) {
		this.discount = discount;
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
