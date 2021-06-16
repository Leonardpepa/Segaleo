package menu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import order.*;
import resources.TextResources;

/*	this class is responsible for reading the products from the files 
 * 	and provides all the program with array lists that contains the products
 * 	also provides useful methods to find a product and returns it and calculates the deals 
 */
public class Menu {

	private static List<Product> appetizers;
	private static List<Product> coffee;
	private static List<Product> desserts;
	private static List<Product> drinks;
	private static List<Product> main;
	private static List<Product> salads;
	private static List<Product> breakfast;
	private static List<Product> allProducts;
	private static List<Product> onlyFood;
	private String line;
	private String nameOfFood;
	private String description;
	private double price;
	private String path;
	private double alchoolPerc;
	private int id = 0;
	
	public Menu() {
		appetizers = new ArrayList<>();
		coffee = new ArrayList<>();
		desserts = new ArrayList<>();
		drinks = new ArrayList<>();
		main = new ArrayList<>();
		salads = new ArrayList<>();
		breakfast = new ArrayList<Product>();
		allProducts = new ArrayList<Product>();
		onlyFood = new ArrayList<Product>();

		readProduct(appetizers, "files/appetizers/Appetizers", TextResources.endpointPath);
		readProduct(desserts, "files/desserts/Desserts", TextResources.endpointPath);
		readProduct(main, "files/main/Main", TextResources.endpointPath);
		readProduct(salads, "files/salads/Salads", TextResources.endpointPath);
		readProduct(breakfast, "files/breakfast/Breakfast", TextResources.endpointPath);
		readProduct(drinks, "files/drinks/Drinks", TextResources.endpointPath);
		readProduct(coffee, "files/coffee/Coffees", TextResources.endpointPath);

		allProducts.addAll(appetizers);
		allProducts.addAll(coffee);
		allProducts.addAll(desserts);
		allProducts.addAll(main);
		allProducts.addAll(salads);

		allProducts.addAll(drinks);
		allProducts.addAll(breakfast);

		onlyFood.addAll(allProducts);
		onlyFood.removeAll(coffee);
		onlyFood.removeAll(drinks);

	}

	private void readProduct(List<Product> list, String pathName, String language) {

		File activitieFile = new File(pathName + language);
		FileReader reader = null;
		BufferedReader inputReader  = null;
		try {
			reader = new FileReader(activitieFile);
			inputReader = new BufferedReader(reader);
			if(pathName.equalsIgnoreCase("files/drinks/Drinks")) {
				id = 120;
			}
			if(pathName.equalsIgnoreCase("files/coffee/Coffees")) {
				id = 200;
			}

			/*
			 * The reader reads each line, separates name, price, description and adds it
			 * into the list
			 */
			line = inputReader.readLine();

			while (line != null) {
				// separation
				nameOfFood = line.split("#")[0].replace("\\n", System.lineSeparator());
				description = line.split("#")[1].replace("\\n", System.lineSeparator());
				// convertion into Double
				price = Double.parseDouble(line.split("#")[2]);
				path = line.split("#")[3];

				if (pathName.equalsIgnoreCase("files/drinks/Drinks")) {
					// convertion into Double
					alchoolPerc = Double.parseDouble(line.split("#")[4]);

					list.add(new Drink(nameOfFood, description, price, path, alchoolPerc, id++));
				} else if (pathName.equalsIgnoreCase("files/coffee/Coffees")) {
					list.add(new Coffee(nameOfFood, description, price, path, id++));
				} else {
					list.add(new Food(nameOfFood, description, price, path, id++));
				}

				line = inputReader.readLine();

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				inputReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
	}

	public static Product findProduct(int id) {
		for (Product p : allProducts) {
			if (p.getId() == id) {
				return p;
			}
		}
		return null;
	}
	
	//calculates the deals checking if the price is higher than 3 euro so we dont end up with 0 and negative numbers
	//also checks so that the same product cant have 2 discounts
	public static ArrayList<Food> GetDeals() {
		Random rand = new Random();
		ArrayList<Food> deals = new ArrayList<Food>();
		for (int i = 0; i < 3; i++) {
			Food dealdFood = (Food) onlyFood.get(rand.nextInt(onlyFood.size()));
			while (dealdFood.isHasDiscount() || dealdFood.getPrice() < 4) {
				dealdFood = (Food) onlyFood.get(rand.nextInt(onlyFood.size()));
			}
			int discount = rand.nextInt(3) + 1;
			dealdFood.setHasDiscount(true, discount);
			deals.add(dealdFood);
		}
		return deals;
	}

	public static List<Product> getProductList(String category) {

		if (category.equalsIgnoreCase(TextResources.appetizers)) {
			return appetizers;
		}

		if (category.equalsIgnoreCase(TextResources.coffee)) {
			return coffee;
		}
		if (category.equalsIgnoreCase(TextResources.desserts)) {
			return desserts;
		}
		if (category.equalsIgnoreCase(TextResources.drinks)) {
			return drinks;
		}
		if (category.equalsIgnoreCase(TextResources.main)) {
			return main;
		}
		if (category.equalsIgnoreCase(TextResources.salads)) {
			return salads;
		}
		if (category.equalsIgnoreCase(TextResources.breakfast)) {
			return breakfast;
		}
		return null;
	}

	public static List<Product> getAllProducts() {
		return allProducts;
	}

	public static List<Product> getAppetizers() {
		return appetizers;
	}

	public static List<Product> getCoffees() {
		return coffee;
	}

	public List<Product> getDesserts() {
		return desserts;
	}

	public List<Product> getDrinks() {
		return drinks;
	}

	public List<Product> getMain() {
		return main;
	}

	public List<Product> getSalads() {
		return salads;
	}

}
