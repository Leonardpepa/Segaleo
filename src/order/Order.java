package order;

import java.util.ArrayList;

public class Order {
	
	private double totalCost;

	private ArrayList<Product> products;
	
	public Order() {
		products = new ArrayList<Product>();
	}
	

	public void addProduct(Product product) {
		products.add(product);
	}
	
	public void clearOrder() {
		products = new ArrayList<>();
	}
	
	public void removeProduct(Product product) {
		products.remove(product);
	}
	
	public double calcCost() {
		totalCost = 0;
		for(Product product: products) {
			totalCost += product.getPrice();
		}
		return totalCost;
	}
	
	//TODO tha dexetai ena kouponi
	//an to kouponi == null tote ekptwsh 0
	//alliw ekptwsh calcCost - ekptwsh
	public double calcDiscount(/* orisma kouponi*/) {
		
		return 0;
	}
	
}
