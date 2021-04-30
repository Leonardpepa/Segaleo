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
	
	public void deleteProduct(Product product) {
		products.remove(product);
	}
	
	public double calcCost() {
		totalCost = 0;
		for(Product product: products) {
			totalCost += product.getPrice();
		}
		return totalCost;
	}

	public ArrayList<Product> getProducts() {
		return products;
	}

	public Product isProduct(String name) {
		Product temp = null;

		for(Product product: products) {
			if(name.equals(product.getName())) {
				temp = product;
				break;
			}
		}

		return temp;
	}
	
	//TODO tha dexetai ena kouponi
	//an to kouponi == null tote ekptwsh 0
	//alliw ekptwsh calcCost - ekptwsh
	public double calcDiscount(CouponFactory aCoupon) {

		return 0;
	}
	
}
