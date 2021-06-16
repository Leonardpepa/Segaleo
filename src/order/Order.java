package order;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import menu.Menu;
import rating.Rating;

/* This class is responsible for the order of a customer
 * it holds data such as the list of products, the quantity of products, the date of the order, the payment method, coupon used and the coupon
 * it is also responsible to calculate the total cost of that order and to check if and discount with a coupon is possible
 */

public class Order implements Serializable{
	
	private static final long serialVersionUID = -7785618513835322110L;
	
	private double totalCost;
	//holds the products that user wants to buy
	private List<Product> products;
	//holds the amount of each product
	
	private HashMap<Product, Integer> prod;
	private Rating rating = null;
	
	//hods the total quantity of the orders products
	private int quantity = 0;
	
	private Date date;
	private int maximumOffers = 2;
	private String paymentMethod;
	public static int numberOfOrders;
	private int id;
	private Coupon coupon;

	public Order() {
		//initialize the data structures the  class uses
		products = new ArrayList<Product>();
		prod = new HashMap<>();
		initializeHashMap();
	}
	
	/*	if product already in the order it just increases the amount of that product
	 *	else it adds it
	 * 	in the end increases the total quantity by one
	 */
	public void addProduct(Product product) {
		if(!products.contains(product)) {
			products.add(product);
		}
		prod.put(product, prod.get(product) + 1);
		quantity++;
	}

	/*it sets everything to 0 or null*/
	public void clearOrder() {
		products = new ArrayList<>();
		initializeHashMap();
		quantity = 0;
	}
	
	/*	if the product amount is 1 then it removes teh product from teh array list
	 * 	else it just decreases the amount by 1
	 * 	in the end decreases the total quantity by 1
	 */
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

	/*	calculates the cost of the order */
	public double calcCost() {
		totalCost = 0;
		for (Product product : products) {
			totalCost += prod.get(product) * product.getPrice();
		}
		return totalCost;
	}
	
	/* calculates the discount if the coupon is valid && if he can still use a coupon as it has a limit of 2*/
	public double calcDiscount(String code) {
		if (maximumOffers > 0 &&  CouponFactory.isValid(code)) {
			maximumOffers--;
			return totalCost -= 3;
		}
		return totalCost;
	}
	
	/* initialize each product to 0 amount in the beginning*/
	public void initializeHashMap() {
		for(Product p: Menu.getAllProducts()) {
			prod.put(p, 0);
		}
	}
	public List<Product> getProducts() {
		return products;
	}
	public HashMap<Product, Integer> getProd(){
		return prod;
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
	
	public void setRating(Rating rating) {
		this.rating  = rating;
	}
	public Rating getRating() {
		return rating;
	}



	
}
