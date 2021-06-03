package payment;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import gui.windows.CartWindow;
import resources.ColorResources;
import resources.TextResources;

public class CreditCard {

	
	private String cardNumber;
	private String name;
	private int month;
	private int year;
	private String code;
	
	public CreditCard(String cardNumber, String name, int month, int year, String code) {
		this.cardNumber = cardNumber;
		this.name = name;
		this.month = month;
		this.year = year;
		this.code = code;
	}
	
	public boolean checkCardNumber() {
		//check for 16 digits
		boolean validNum = true;
		char[] strarray=new char[cardNumber.length()];
		int[] intarray = new int[cardNumber.length()];
		for(int i=0; i<cardNumber.length();i++){
			strarray[i] = cardNumber.charAt(i);
			intarray[i] = (int) strarray[i];
			if(intarray[i] <48 || intarray[i]>57) validNum=false;
		}
		if(cardNumber.length()!=16) validNum = false;
		return validNum;
	}
	
	public boolean checkExpireDate() {
		
	    //check year
	    boolean validYear = true;
	    SimpleDateFormat currentYear = new SimpleDateFormat("yyyy");  
		Date yyyy = new Date();  
		int cyear = Integer.parseInt(currentYear.format(yyyy));
		if(cyear > year) validYear = false;
		
		if(cyear == year){
			//check month
			boolean validMonth = true;
			SimpleDateFormat currentMonth = new SimpleDateFormat("MM");  
		    Date mm = new Date();  
		    int cmonth = Integer.parseInt(currentMonth.format(mm));
		    if(cmonth > month) validMonth = false;
		    
		    return validMonth;
		}
		return validYear;
	}
	
	public boolean checkCode() {
		//check for 3 digits
		boolean validCode = true;
		char[] sarray=new char[code.length()];
		int[] iarray = new int[code.length()];
		for(int i=0; i<code.length();i++){
			sarray[i] = code.charAt(i);
			iarray[i] = (int) sarray[i];
			if(iarray[i] <48 || iarray[i]>57) validCode=false;
		}
		if(code.length()!=3) validCode = false;
		return validCode;
	}
	
	public void checkValidation(JFrame frame) {
		
		//check and print message
		if (checkCardNumber() && checkExpireDate() && checkCode()) {
			JOptionPane.showMessageDialog(frame,
				    "Card Added",
				    "Success",
				    JOptionPane.PLAIN_MESSAGE);
			CartWindow.paymentMethods.setText(TextResources.card);
			CartWindow.paymentMethods.setBackground(ColorResources.card);
			CartWindow.paymentMethods.setForeground(Color.BLACK);
			frame.dispose();
		}
		else {
			JOptionPane.showMessageDialog(frame,
				    "Payment Unsuccessful",
				    "Payment Unsucessful",
				    JOptionPane.ERROR_MESSAGE);
		}
	}

}
