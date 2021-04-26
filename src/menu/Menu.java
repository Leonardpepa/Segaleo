package menu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import order.Food;
import order.Product;
import resources.TextResources;

public class Menu {

	private ArrayList<Product> appetizers;
	private ArrayList<Product> main;
	private ArrayList<Product> salads;

	private String line;
	private String nameOfFood;
	private String description;
	private double price;
	private String path;

	public Menu() {
		appetizers = new ArrayList<>();
		main = new ArrayList<>();
		salads = new ArrayList<>();

		readFood(appetizers, "files\\appetizers\\Appetizers", TextResources.endpointPath);
		readFood(main, "files\\main\\Main", TextResources.endpointPath);
		readFood(salads, "files\\salads\\Salads", TextResources.endpointPath);
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

				list.add(new Food(nameOfFood, description, price, path));
				
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
		String upperCaseCategory = category.toUpperCase();
		switch(upperCaseCategory) {
		case "APPETIZERS":
			return appetizers;
		case "MAIN":
			return main;
		case "SALADS":
			return salads;
		}
		return null;
	}
	
	
	public ArrayList<Product> getAppetizers() {
		return appetizers;
	}


	public ArrayList<Product> getMain() {
		return main;
	}

	public ArrayList<Product> getSalads() {
		return salads;
	}

}
