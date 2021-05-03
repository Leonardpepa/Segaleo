package gui.factory;
import java.awt.Font;
import java.awt.font.TextAttribute;
import java.util.Map;

public class FontFactory {

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
	
	public static Font lineThrough(int size) {
		Font font = new Font("Poppins", Font.PLAIN, size);
		Map  attributes = font.getAttributes();
		attributes.put(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON);
		Font newFont = new Font(attributes);
		return newFont;
	}
}
