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

	private ArrayList<Product> appetizersList;
	private ArrayList<Product> mainList;
	private ArrayList<Product> saladsList;

	private String line;
	private String nameOfFood;
	private String description;
	private double price;
	private String path;

	public Menu() {
		appetizersList = new ArrayList<>();
		mainList = new ArrayList<>();
		saladsList = new ArrayList<>();

		readFood(appetizersList, "files\\appetizers\\Appetizers", TextResources.endpointPath);
		readFood(mainList, "files\\main\\Main", TextResources.endpointPath);
		readFood(saladsList, "files\\salads\\Salads", TextResources.endpointPath);
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

	public ArrayList<Product> getAppetizersList() {
		return appetizersList;
	}


	public ArrayList<Product> getMainList() {
		return mainList;
	}

	public ArrayList<Product> getSaladsList() {
		return saladsList;
	}

}
