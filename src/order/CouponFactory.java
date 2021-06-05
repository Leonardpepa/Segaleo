package order;

import java.awt.Color;
import java.util.Date;
import java.util.Random;

import javax.swing.JOptionPane;

import gui.windows.CartWindow;
import login.Login;
import resources.TextResources;

public class CouponFactory {
	static Random rand = new Random();

	public static Coupon GenerateCoupon(String email) {
		String code = "";
		// Βαλαμε στατικο για τεστ αλλα θα παιρνει ορισμα τον χρηστη και θα παιρνουμε
		// απο κει το μειλ
		for (int i = 0; i < 3; i++) {
			code += email.toCharArray()[rand.nextInt(email.length())];
		}
		int randomNumber = rand.nextInt(9990 + 1 - 1000) + 1000;
		code += Integer.toString(randomNumber);

		return new Coupon(code.toUpperCase(), new Date());
	}

	public static boolean isValid(String code) {
		Coupon couponProvided = searchCoupon(code);

		Date today = new Date();
		try {
			// calculates the time that takes for a useer to use the coupon in milliseconds
			long mill = today.getTime() - couponProvided.getDate().getTime();

			// converts the millseconds to days
			long days = (long) (mill / (1000 * 60 * 60 * 24));

			if (days < 3) {
				Login.loggedCustomer.removeCoupon(couponProvided);
				CartWindow.couponField.setBackground(new Color(158, 232, 178));
				CartWindow.couponField.setText(TextResources.submitted);
				return true;
			}

		} catch (NullPointerException e) {
			CartWindow.couponField.setBackground(new Color(232, 158, 158));
			CartWindow.couponField.setText(TextResources.invalidCoupon);
		}

		return false;
	}

	public static Coupon searchCoupon(String code) {
		for (Coupon coupon : Login.loggedCustomer.getCoupons()) {
			if (coupon.getCode().equals(code)) {
				return coupon;
			}
		}
		return null;
	}
}
