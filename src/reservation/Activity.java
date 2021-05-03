package reservation;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Activity 
{
	private String id;
	private String name;
	private int price;
	private String path;
	private int availability;
	private String hour;

	//retrieve them from file
	private int tennis1 = 4;
	private int tennis2 = 4; 
	private int golf1 = 6;
	private int golf2 = 6; 
	private int spa1 = 8; 
	private int waterpark = 10; 
	private int diving1 = 2; 
	private int diving2 = 2; 
	private int roadtrip = 12;
	int [][] a = new int[7][9];
	
	
	public Activity(String id, String name,  int price, String path, int availability, String hour ){
		this.id = id;
		this.name = name;
		this.price = price;
		this.path = path;
		this.availability = availability;
		this.hour = hour;

	}

	//Getters
	public String getName() {
		return name;
	}
	
	public int getPrice() {
		return price;
	}
	
	public String getId() {
		return id;
	}

	public String getPath() {
		return path;
	}

	public int getAvailability() {
		return availability;
	}

	public String getHour() {
		return hour;
	}

	public void printActivity() {
		System.out.println("Activity: " +this.name+ " with id: " + this.id + " , hour: " + this.hour + ", max people : " + this.availability + " and price per people : " + this.price);;
	}
	//tennis     4 people    17:00-18:00, 18:00-19:00
	//golf       6 people	 15:00-16:00, 16:00-17:00		
	//spa        8 people	 16:00-18:00
	//waterpark  10 people   10:00-15:00
	//diving     2 people 	 10:00-11:00, 11:00-12:00
	//road trip  12 people	 9:00-12:00
	//concerts   no limit
	// beach res
	// pulls them from file
	
	
	

	//ελέγχει δραστηριότητα και ώρα για να προσδιορίσει στήλη πίνακα
	public int checkActivity(String hour) {
		int j;
		if (this.name == "Tennis") {
			if(hour == "17:00") j=0;
			else j=1;
	
		}
		else if(this.name == "Golf"){
			if(hour == "15:00") j=2;
			else j=3;
		} 
		else if(this.name == "Spa") j=4;
		else if (this.name == "WaterPark") j=5;
		else if (this.name == "Diving") {
			if(hour == "10:00") j=6;
			else j=7;
		}
		else if(this.name == "Road Trip") j=8;
		else return -1;
		
		return j;
	}
	
	//γίνεται ο έλεγχος διαθεσιμότητας θέσης
	public boolean checkLimit(int people,String hour, String day) 
	{
		boolean valid = false;
		int x = this.checkActivity(hour);
		switch(day) {
		  case "Δευτέρα":
		    if (people <= a[0][x]) {
		    	a[0][x] -= people;
		    	valid = true;
		    }
		    else {
		    	System.out.println("oxi");
		    	valid = false;
		    }
		    break;
		  case "Τρίτη":
			  if (people <= a[1][x]) {
			    	a[1][x] -= people;
			    	valid = true;
			    }
			    else {
			    	System.out.println("oxi");
			    	valid = false;
			    }
		    break;
		  case "Τετάρτη":
			  if (people <= a[2][x]) {
			    	a[2][x] -= people;
			    	valid = true;
			    }
			    else {
			    	System.out.println("oxi");
			    	valid = false;
			    }
		    break;
		  case "Πέμπτη":
			  if (people <= a[3][x]) {
			    	a[3][x] -= people;
			    	valid = true;
			    }
			    else {
			    	System.out.println("oxi");
			    	valid = false;
			    }
		    break;
		  case "Παρασκευή":
			  if (people <= a[4][x]) {
			    	a[4][x] -= people;
			    	valid = true;
			    }
			    else {
			    	System.out.println("oxi");
			    	valid = false;
			    }
		    break;
		  case "Σάββατο":
			  if (people <= a[5][x]) {
			    	a[5][x] -= people;
			    	valid = true;
			    }
			    else {
			    	System.out.println("oxi");
			    	valid = false;
			    }
		    break;
		  case "Κυριακή":
			  if (people <= a[6][x]) {
			    	a[6][x] -= people;
			    	valid = true;
			    }
			    else {
			    	System.out.println("oxi");
			    	valid = false;
			    }
		    break;
		 
		}
		return valid;
	}

	public void initialArray() {
		for(int i=0; i<=6;i ++) {
			a[i][0] =4;
			a[i][1] =4;
		}
		for(int i=0; i<=6;i ++) {
			a[i][2] =6;
			a[i][3] =6;
		}
		for(int i=0; i<=6;i ++) {
			a[i][4] =8;
		}
		for(int i=0; i<=6;i ++) {
			a[i][5] =10;
		}
		for(int i=0; i<=6;i ++) {
			a[i][6] =2;
			a[i][7] =2;
		}
		for(int i=0; i<=6;i ++) {
			a[i][8] =12;
		}
		
		
		for(int i=0; i<=6; i++) {
			for(int j=0; j<=8; j++) {
				System.out.println(a[i][j]);
			}
		}
	}
	public void printArray() {
		for(int i=0; i<=6; i++) {
			for(int j=0; j<=8; j++) {
				System.out.println(a[i][j]);
			}
		}
	}
	

}