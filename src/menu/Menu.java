package menu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import order.*;
import resources.TextResources;

public class Menu {


	private ArrayList<Product> appetizers;
	private ArrayList<Product> coffee;
	private ArrayList<Product> desserts;
	private ArrayList<Product> drinks;
	private ArrayList<Product> main;
	private ArrayList<Product> salads;
	private static ArrayList<Product> allProducts;
	private ArrayList<Product> onlyFood;
	private String line;
	private String nameOfFood;
	private String description;
	private double price;
	private String path;
	private double alchoolPerc;

	public Menu() {
		appetizers = new ArrayList<>();
		coffee = new ArrayList<>();
		desserts = new ArrayList<>();
		drinks = new ArrayList<>();
		main = new ArrayList<>();
		salads = new ArrayList<>();
		allProducts = new ArrayList<Product>();
		onlyFood = new ArrayList<Product>();

		readProduct(appetizers, "files/appetizers/Appetizers", TextResources.endpointPath);
		readProduct(coffee, "files/coffee/Coffees", TextResources.endpointPath);
		readProduct(desserts, "files/desserts/Desserts", TextResources.endpointPath);
		readProduct(drinks, "files/drinks/Drinks", TextResources.endpointPath);
		readProduct(main, "files/main/Main", TextResources.endpointPath);
		readProduct(salads, "files/salads/Salads", TextResources.endpointPath);
		
		allProducts.addAll(appetizers);
		allProducts.addAll(desserts);
		allProducts.addAll(main);
		allProducts.addAll(salads);	
		allProducts.addAll(coffee);
		allProducts.addAll(drinks);
		
		onlyFood.addAll(allProducts);
		onlyFood.removeAll(coffee);
		onlyFood.removeAll(drinks);
	}

	private void readProduct(ArrayList<Product> list, String pathName, String language) {

		File activitieFile = new File(pathName + language);
		try {
			FileReader reader = new FileReader(activitieFile);
			BufferedReader inputReader = new BufferedReader(reader);

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
				
				if (pathName.contentEquals("files/drinks/Drinks")) {
					//convertion into Double
					alchoolPerc = Double.parseDouble(line.split("#")[4]);
					
					list.add(new Drink(nameOfFood, description, price, path, alchoolPerc));
				}
				else if (pathName.equalsIgnoreCase("files/coffee/Coffees")){
					list.add(new Coffee(nameOfFood, description, price, path));
				}
				else {
				list.add(new Food(nameOfFood, description, price, path));
				}
				
				line = inputReader.readLine();

			}
			inputReader.close();
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Product findProduct(String name) {
		for(Product p: allProducts) {
			if(p.getName().equalsIgnoreCase(name)) {
				return p;
			}
		}
		return null;
	}
	
	public ArrayList<Food> GetDeals(){
		Random rand = new Random();
		ArrayList<Food> deals = new ArrayList<Food>();
		for(int i=0; i<3; i++) {
			Food dealdFood = (Food) onlyFood.get(rand.nextInt(onlyFood.size()));
			int discount = rand.nextInt(3) + 1;
			dealdFood.setHasDiscount(true, discount);
			deals.add(dealdFood);
		}
		return deals;
	}
	
	
	public ArrayList<Product> getProductList(String category) {
		
		if(category.equalsIgnoreCase(TextResources.appetizers)) {
			return appetizers;
		}

		if(category.equalsIgnoreCase(TextResources.appetizers)) {
			return appetizers;
		}

		if(category.equalsIgnoreCase(TextResources.coffee)) {
			return coffee;
		}
		if(category.equalsIgnoreCase(TextResources.desserts)) {
			return desserts;
		}
		if(category.equalsIgnoreCase(TextResources.drinks)) {
			return drinks;
		}
		if(category.equalsIgnoreCase(TextResources.main)) {
			return main;
		}
		if(category.equalsIgnoreCase(TextResources.salads)) {
			return salads;
		}
		return null;
	}
	
	
	public ArrayList<Product> getAppetizers() {
		return appetizers;
	}

	public ArrayList<Product> getCoffees() {
		return coffee;
	}
	
	public ArrayList<Product> getDesserts() {
		return desserts;
	}
	
	public ArrayList<Product> getDrinks() {
		return drinks;
	}

	public ArrayList<Product> getMain() {
		return main;
	}

	public ArrayList<Product> getSalads() {
		return salads;
	}

}
