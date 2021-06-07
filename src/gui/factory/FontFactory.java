package gui.factory;
import java.awt.Font;

public class FontFactory {
	
	/*
	 * This class contains all the fonts used for the projects GUI windows
	 */

	public static Font poppins(int size) {
		return new Font("Poppins", Font.PLAIN, size);
	}
	
	public static Font arial(int size) {
		return new Font("Arial", Font.PLAIN, size);
	}
	
	public static Font robboto(int size) {
		return new Font("Robboto", Font.PLAIN, size);
	}
	
	public static Font sansSerif(int size) {
		return new Font(Font.SANS_SERIF, Font.PLAIN, size);
	}
	
	public static Font monospace(int size) {
		return new Font(Font.MONOSPACED, Font.PLAIN, size);
	}
	
	public static Font helvetica(int size) {
		return new Font("helvetica", Font.PLAIN, size);
	}
	
	public static Font avenir(int size) {
		return new Font("Avenir", Font.PLAIN, size);
	}
	
	public static Font boldavenir(int size) {
		return new Font("Avenir", Font.BOLD, size);
	}
	
}
