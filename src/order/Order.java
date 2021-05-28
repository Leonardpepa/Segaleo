package order;

import resources.TextResources;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

import menu.Menu;

public class Order {

	private double totalCost;
	private ArrayList<Product> products;
	public HashMap<Product, Integer> prod;
	public Order() {
		products = new ArrayList<Product>();
		prod = new HashMap<>();
		initializeHashMap();
	}

	public void addProduct(Product product) { 
		if(!products.contains(product)) {
			products.add(product);
		}
		prod.put(product, prod.get(product) + 1);			
	}

	public void clearOrder() {
		products = new ArrayList<>();
		initializeHashMap();
	}

	public void removeProduct(Product product) {
		if(prod.get(product) == 1) {
			products.remove(product);
			prod.put(product, prod.get(product) - 1);			
		}
		else {
			prod.put(product, prod.get(product) - 1);
		}
	}

	public double calcCost() {
		totalCost = 0;
		for (Product product : products) {
			totalCost += prod.get(product) * product.getPrice();
		}
		return totalCost;
	}

	public ArrayList<Product> getProducts() {
		return products;
	}

	public Product isProduct(String name) {
		Product temp = null;

		for (Product product : products) {
			if (name.equals(product.getName())) {
				temp = product;
				break;
			}
		}

		return temp;
	}

	public double calcDiscount(Coupon coupon) {
		if (CouponFactory.isValid(coupon)) {
			return totalCost -= 3;
		}
		else {
			JOptionPane.showMessageDialog(null, TextResources.invalidCoupon, TextResources.invalidCouponTitle, JOptionPane.INFORMATION_MESSAGE);
		}
		return totalCost;
	}
	
	public void initializeHashMap() {
		for(Product p: Menu.getAllProducts()) {
			prod.put(p, 0);
		}
	}

	public double getTotalCost() {
		return totalCost;
	}
}
