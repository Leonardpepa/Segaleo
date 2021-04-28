package order;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Order {
	
	private double totalCost;

	private ArrayList<Product> products;
	
	public Order() {
		products = new ArrayList<Product>();
	}

	private Pattern pattern = Pattern.compile("12345");
	

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
	public double calcDiscount(Coupon aCoupon) {

		Matcher matcher = pattern.matcher(aCoupon.getCode());
		boolean matchFound = matcher.matches();

		if(matchFound)
			totalCost -= 3;
		else
			System.out.println("Invalid Coupon");

		return totalCost;
	}
	
}
