package reservation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class Reservation 
{
	private String day  ; 
	private String hour ;
	private int code;
	private Activity activity;
	private int people;
	private DateFormat dayFormat;
	private DateFormat hourFormat;
	private ArrayList<Reservation> reservations = new ArrayList<>();

	public Reservation (int code, Activity activity, String day, String hour, int people) 
	{
		this.activity = activity;
		this.people = people;
		this.day = day; 
		this.hour = hour;
		dayFormat = new SimpleDateFormat("dd/MM");
		hourFormat = new SimpleDateFormat("hh:mm");
		if (this.activity.checkLimit(people, hour,day)) {
			code = code;
		}
		else {
			System.out.println("den xorane tosoi");
		}


	}
	
	public double calcTotal() 
	{
		double cost=0;
		cost = this.people*this.activity.getPrice();
		return cost;
	}
	
	//hour and das
	//payment methods
	//total
	//for search and evaluation
	
	public void addReservation(Reservation r)
	{
		reservations.add(r);
	}

	public void removeReservation(Reservation r)
	{
		reservations.remove(r);
	}
	
	public void clearReservations()
	{
		reservations = new ArrayList<>();
	}



}
