package menu;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import order.Food;
import order.Product;

public class Menu {
	
	private ArrayList<Product> appetizersListENG;
	private ArrayList<Product> appetizersListGR;
	private ArrayList<Product> mainListENG;
	private ArrayList<Product> mainListGR;
	private ArrayList<Product> saladsListENG;
	private ArrayList<Product> saladsListGR;
	
	private String line;
	private String nameOfFood;
	private String description;
	private String stringPrice;
	private double price;
	private String path;
	
	public Menu() {
		appetizersListENG = new ArrayList<>();
		appetizersListGR = new ArrayList<>();
		mainListENG = new ArrayList<>();
		mainListGR = new ArrayList<>();
		saladsListENG = new ArrayList<>();
		saladsListGR = new ArrayList<>();
		
		readAppetizersEnglish();
		readAppetizersGreek();
		readMainEnglish();
		readMainGreek();
		readSaladsEnglish();
		readSaladsGreek();
	}
	
	private void readAppetizersEnglish() {
		
		File activitieFile = new File("files\\appetizers\\AppetizersEnglish");
		try {
			FileReader reader = new FileReader(activitieFile);
			BufferedReader inputReader = new BufferedReader (reader);

			/*The reader reads each line,
			 * separates name, price, description and
			 * adds it into the list
			 */
			line = inputReader.readLine();
			
			while (line != null) {
				//separation
				nameOfFood = line.split("#")[0].replace("\\n", System.lineSeparator());
				description = line.split("#")[1].replace("\\n", System.lineSeparator());
				stringPrice = line.split("#")[2].replace("\\n", System.lineSeparator());
				//convertion into Double
				price = Double.parseDouble(stringPrice);
				path = line.split("#")[3].replace("\\n", System.lineSeparator());
				
				appetizersListENG.add(new Food(nameOfFood, description, price, path));				
		}
		inputReader.close();
		reader.close();
		}
		catch(FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void readAppetizersGreek() {
		
		File activitieFile = new File("files\\appetizers\\AppetizersGreek");
		try {
			FileReader reader = new FileReader(activitieFile);
			BufferedReader inputReader = new BufferedReader (reader);

			line = inputReader.readLine();
			
			while (line != null) {
				//separation
				nameOfFood = line.split("#")[0].replace("\\n", System.lineSeparator());
				description = line.split("#")[1].replace("\\n", System.lineSeparator());
				stringPrice = line.split("#")[2].replace("\\n", System.lineSeparator());
				//convertion into Double
				price = Double.parseDouble(stringPrice);
				path = line.split("#")[3].replace("\\n", System.lineSeparator());
				
				appetizersListGR.add(new Food(nameOfFood, description, price, path));				
		}
		inputReader.close();
		reader.close();
		}
		catch(FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void readMainEnglish() {
		
		File activitieFile = new File("files\\main\\MainEnglish");
		try {
			FileReader reader = new FileReader(activitieFile);
			BufferedReader inputReader = new BufferedReader (reader);


			line = inputReader.readLine();
			
			while (line != null) {
				//separation
				nameOfFood = line.split("#")[0].replace("\\n", System.lineSeparator());
				description = line.split("#")[1].replace("\\n", System.lineSeparator());
				stringPrice = line.split("#")[2].replace("\\n", System.lineSeparator());
				//convertion into Double
				price = Double.parseDouble(stringPrice);
				path = line.split("#")[3].replace("\\n", System.lineSeparator());
				
				mainListENG.add(new Food(nameOfFood, description, price, path));				
		}
		inputReader.close();
		reader.close();
		}
		catch(FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void readMainGreek() {
		
		File activitieFile = new File("files\\main\\MainGreek");
		try {
			FileReader reader = new FileReader(activitieFile);
			BufferedReader inputReader = new BufferedReader (reader);


			line = inputReader.readLine();
			
			while (line != null) {
				//separation
				nameOfFood = line.split("#")[0].replace("\\n", System.lineSeparator());
				description = line.split("#")[1].replace("\\n", System.lineSeparator());
				stringPrice = line.split("#")[2].replace("\\n", System.lineSeparator());
				//convertion into Double
				price = Double.parseDouble(stringPrice);
				path = line.split("#")[3].replace("\\n", System.lineSeparator());
				
				mainListGR.add(new Food(nameOfFood, description, price, path));				
		}
		inputReader.close();
		reader.close();
		}
		catch(FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void readSaladsEnglish() {
		
		File activitieFile = new File("files\\salads\\SaladsEnglish");
		try {
			FileReader reader = new FileReader(activitieFile);
			BufferedReader inputReader = new BufferedReader (reader);


			line = inputReader.readLine();
			
			while (line != null) {
				//separation
				nameOfFood = line.split("#")[0].replace("\\n", System.lineSeparator());
				description = line.split("#")[1].replace("\\n", System.lineSeparator());
				stringPrice = line.split("#")[2].replace("\\n", System.lineSeparator());
				//convertion into Double
				price = Double.parseDouble(stringPrice);
				path = line.split("#")[3].replace("\\n", System.lineSeparator());
				
				saladsListENG.add(new Food(nameOfFood, description, price, path));				
		}
		inputReader.close();
		reader.close();
		}
		catch(FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void readSaladsGreek() {
		
		File activitieFile = new File("files\\salads\\SaladsGreek");
		try {
			FileReader reader = new FileReader(activitieFile);
			BufferedReader inputReader = new BufferedReader (reader);


			line = inputReader.readLine();
			
			while (line != null) {
				//separation
				nameOfFood = line.split("#")[0].replace("\\n", System.lineSeparator());
				description = line.split("#")[1].replace("\\n", System.lineSeparator());
				stringPrice = line.split("#")[2].replace("\\n", System.lineSeparator());
				//convertion into Double
				price = Double.parseDouble(stringPrice);
				path = line.split("#")[3].replace("\\n", System.lineSeparator());
				
				saladsListGR.add(new Food(nameOfFood, description, price, path));				
		}
		inputReader.close();
		reader.close();
		}
		catch(FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public ArrayList<Product> getAppetizersListENG(){
		return appetizersListENG;
	}
	
	public ArrayList<Product> getAppetizersListGR(){
		return appetizersListGR;
	}
	
	public ArrayList<Product> getMainListENG(){
		return mainListENG;
	}
	
	public ArrayList<Product> getMainListGR(){
		return mainListGR;
	}
	
	public ArrayList<Product> getSaladsListENG(){
		return saladsListENG;
	}
	
	public ArrayList<Product> getSaladsListGR(){
		return saladsListGR;
	}
}
