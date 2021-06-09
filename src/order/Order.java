package order;

import rating.Rating;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import menu.Menu;

public class Order {

	private double totalCost;
	private ArrayList<Product> products;
	private ArrayList<Rating> ratings;
	private HashMap<Product, Integer> prod;
	private int quantity = 0;
	private Date date;
	private int maximumOffers = 2;
	private String paymentMethod;
	public static int numberOfOrders;
	private int id;
	private Coupon coupon;

	public Order() {
		products = new ArrayList<Product>();
		ratings = new ArrayList<>();
		prod = new HashMap<>();
		initializeHashMap();
	}

	public void addRating(Rating rating) {
		ratings.add(rating);
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

	public boolean isProduct(String name) {
		for (Product product : products) {
			if (name.equals(product.getName())) {
				return true;
			}
		}

		return false;
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
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getPaymentMethod() {
		return paymentMethod;
	}
	
	public void setPaymentMethod(String payment) {
		paymentMethod  = payment;
	}

	public Coupon getCoupon() {
		return coupon;
	}

	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}

	
}
