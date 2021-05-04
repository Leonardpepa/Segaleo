package reservation;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;


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
	
	// Initializing a Dictionary
    Dictionary days = new Hashtable();
    Dictionary activities = new Hashtable();
    Dictionary tennis = new Hashtable();
    Dictionary golf = new Hashtable();
    Dictionary diving = new Hashtable();
    Dictionary concert = new Hashtable();
    Dictionary beach = new Hashtable();
    Dictionary limitΤ1 = new Hashtable();
    Dictionary limitΤ2 = new Hashtable();
    Dictionary limitG1 = new Hashtable();
    Dictionary limitG2 = new Hashtable();
    Dictionary limitS = new Hashtable();
    Dictionary limitW = new Hashtable();
    Dictionary limitD1 = new Hashtable();
    Dictionary limitD2 = new Hashtable();
    Dictionary limitR = new Hashtable();
    Dictionary limitC = new Hashtable();
    Dictionary limitB = new Hashtable();
	
	
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
	
	public void initializeHashTable()
	{
		// put() method limit-hour
        limitΤ1.put("4", "17:00");
        limitΤ2.put("4", "18:00");
        limitG1.put("6", "15:00");
        limitG2.put("6", "16:00");
        limitS.put("8", "16:00");
        limitW.put("10", "10:00");
        limitD1.put("2", "10:00");
        limitD2.put("2", "11:00");
        limitR.put("12", "9:00");
        limitC.put("40", "20:00");
        limitB.put("40", "10:00");
        
        // put() method groups of 2
        tennis.put("t1",limitΤ1);
        tennis.put("t2", limitΤ2);
        golf.put("g1", limitG1);
        golf.put("g2", limitG2);
        diving.put("d1", limitD1);
        diving.put("d2", limitD2);
        
        // put() method activities
        activities.put("Tennis", tennis);
        activities.put("Golf", golf);
        activities.put("Spa", limitS);
        activities.put("WaterPark", limitW);
        activities.put("Diving", diving);
        activities.put("Roadtrip", limitR);
        activities.put("Concert", limitR);
        activities.put("Beach", limitR);
        
        // put() method days
        days.put("Δευτέρα", activities);
        days.put("Τρίτη", activities);
        days.put("Τετάρτη", activities);
        days.put("Πέμπτη", activities);
        days.put("Παρασκευή", activities);
        days.put("Σάββατο", activities);
  
        // elements() method :
        for (Enumeration i = days.elements(); i.hasMoreElements();)
        {
            System.out.println("Value in Dictionary : " + i.nextElement());
        }
  
        // get() method :
        System.out.println("\nValue at key = Tennis : " + activities.get("Tennis"));
        System.out.println("Value at key = Τρίτη : " + days.get("Τρίτη"));
  

        // keys() method :
        for (Enumeration k = days.keys(); k.hasMoreElements();)
        {
            System.out.println("Keys in Dictionary : " + k.nextElement());
        }
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