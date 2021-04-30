package order;

import java.util.Date;
import java.util.Random;

public class CouponFactory {
	private String email = "phil@michalopoulos.gr";
	Random rand = new Random();

	public Coupon GenerateCoupon() {
		String code = "";
		// Βαλαμε στατικο για τεστ αλλα θα παιρνει ορισμα τον χρηστη και θα παιρνουμε
		// απο κει το μειλ
		for (int i = 0; i < 3; i++) {
			code += email.toCharArray()[rand.nextInt(email.length())];
		}
		int randomNumber = rand.nextInt(9990 + 1 - 1000) + 1000;
		code += Integer.toString(randomNumber);
		System.out.println(code.toUpperCase());

		return new Coupon(code.toUpperCase(), new Date());
	}

	@SuppressWarnings("deprecation")
	public boolean isValid(Coupon coupon) {
    	Date today = new Date();
    	String code = coupon.getCode(); 
    	String letters = code.substring(0, 2);
    	//converting time from milliseconds to days
    	long mill = today.getTime() - coupon.getDate().getTime();
    	
    	//check if the format and the date of the coupon is right
    	if(email.toUpperCase().contains(letters) && code.length() == 7) {
    		if((long) (mill / (1000*60*60*24)) < 3) {
    			return true;
    		}
    	}
        return false;
    }
}
