package order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JOptionPane;

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
		for (Product product : products) {
			totalCost += product.getPrice();
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
			JOptionPane.showMessageDialog(null, "Invalid Coupon");
		}
		return totalCost;
	}

	public double getTotalCost() {
		return totalCost;
	}
}
