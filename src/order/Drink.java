package order;

public class Drink extends Product{

	private double alcoholPercentage;
	
	public Drink(String name, String description, double price, String path, double alcoholPercentage) {
		super(name, description, price, path);
		this.alcoholPercentage = alcoholPercentage;
	}

	public double getAlcoholPercentage() {
		return alcoholPercentage;
	}

	public void setAlcoholPercentage(double alcoholPercentage) {
		this.alcoholPercentage = alcoholPercentage;
	}
	

}
