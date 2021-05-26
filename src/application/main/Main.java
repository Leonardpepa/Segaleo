
package application.main;

import gui.windows.*;
import resources.TextResources;

public class Main {

	public static void main(String[] args) {
		new TextResources().changeLanguage();
		new LoginWindow();
	}
}
