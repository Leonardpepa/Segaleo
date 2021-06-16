package contact;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
/*
 * This class is used to add functionality to the buttons of the ContactWindow
 * 
 * The openURL method accepts a String as an input and uses it to open the url of the social media page
 * Library used: java.awt.Desktop and java.net.URI
 * 
 */

public class SocialMedia {

	public void openURL(String social) {
		Desktop desktop = java.awt.Desktop.getDesktop();
		try {
			// specify the protocol along with the URL
			URI oURL = new URI("https://www." + social + ".com/segaleo.uom");
			desktop.browse(oURL);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
