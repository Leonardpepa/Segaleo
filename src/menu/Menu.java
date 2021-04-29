package menu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import order.Coffee;
import order.Drink;
import order.Food;
import order.Product;
import resources.TextResources;

public class Menu {

	private ArrayList<Product> appetizers;
	private ArrayList<Product> coffee;
	private ArrayList<Product> desserts;
	private ArrayList<Product> drinks;
	private ArrayList<Product> main;
	private ArrayList<Product> salads;

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

		readFood(appetizers, "files/appetizers/Appetizers", TextResources.endpointPath);
<<<<<<< HEAD
=======
		readFood(coffee, "files/coffee/Coffees", TextResources.endpointPath);
		readFood(desserts, "files/desserts/Desserts", TextResources.endpointPath);
		readFood(drinks, "files/drinks/Drinks", TextResources.endpointPath);
>>>>>>> master
		readFood(main, "files/main/Main", TextResources.endpointPath);
		readFood(salads, "files/salads/Salads", TextResources.endpointPath);
	}

	private void readFood(ArrayList<Product> list, String pathName, String language) {

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

	
	public ArrayList<Product> getProductList(String category) {
<<<<<<< HEAD
		
		if(category.equalsIgnoreCase(TextResources.appetizers)) {
			return appetizers;
		}
=======

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
>>>>>>> master
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
