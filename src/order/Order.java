package order;

import resources.TextResources;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JOptionPane;

import menu.Menu;

public class Order {

	private double totalCost;
	private ArrayList<Product> products;
	private HashMap<Product, Integer> prod;
	private int quantity = 0;
	private Date date;
	private int maximumOffers = 2;
	
	public Order() {
		date = new Date();
		products = new ArrayList<Product>();
		prod = new HashMap<>();
		initializeHashMap();
	}

	public void addProduct(Product product) { 
		if(!products.contains(product)) {
			products.add(product);
		}
		prod.put(product, prod.get(product) + 1);
		quantity++;
	}

	public void clearOrder() {
		products = new ArrayList<>();
		initializeHashMap();
		quantity = 0;
	}

	public void removeProduct(Product product) {
		if(prod.get(product) == 1) {
			products.remove(product);
			prod.put(product, prod.get(product) - 1);			
		}
		else {
			prod.put(product, prod.get(product) - 1);
		}
		quantity--;
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
	public HashMap<Product, Integer> getProd(){
		return prod;
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

	public double calcDiscount(String code) {
		if (maximumOffers > 0 &&  CouponFactory.isValid(code)) {
			maximumOffers--;
			return totalCost -= 3;
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
	public int getQuantity() {
		return quantity;
	}
	public Date getDate() {
		return date;
	}
}
