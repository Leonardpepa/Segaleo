package order;

public class Food extends Product{
	private boolean hasDiscount = false;
	private int discount = 0;
	
	public Food(String name, String description, double price, String path) {
		super(name, description, price, path);
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
}
