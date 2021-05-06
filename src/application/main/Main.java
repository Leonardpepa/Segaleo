 package application.main;


import gui.windows.CartWindow;
import gui.windows.LoginWindow;
import resources.ColorResources;
import resources.TextResources;

public class Main {

	public static void main(String[] args) {
		//new LoginWindow();
		new ColorResources();
		new TextResources().changeLanguage();
		new CartWindow();
	}
}
