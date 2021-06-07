package contact;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


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
