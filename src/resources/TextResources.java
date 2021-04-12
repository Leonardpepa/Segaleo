package resources;

public class TextResources {
	public static boolean  isEnglish = true;
	
	public static String roomService;
	public static String activities;
	public static String welcome;
	public static String loginto;
	public static String roomField;
	public static String passField;
	public static String loginBtn;
	public static String languageLabel;
	public static String greek;
	public static String english;
	
	public  void changeLanguage() {
		greek = isEnglish ? "Greek" : "Ελληνικά";
		english = isEnglish ? "English" : "Αγγλικά";
		roomService = isEnglish ? "ROOM SERVICE" : "ΠΑΡΑΓΓΕΛΙΑ";
		activities = isEnglish ? "ACTIVITIES" : "ΔΡΑΣΤΗΡΙΟΤΗΤΕΣ";
		welcome = isEnglish ? "Welcome to Segaleo" : "Καλώς ήρθατε στο Segaleo";
		loginto = isEnglish ?  "Login to your account" : "Συνδεθείτε στον λογαριασμό σας";
		roomField = isEnglish ?  "  Room Number" : "  Αριθμός δωματίου";
		passField = isEnglish ? "  Password" : "  Κωδικός";
		loginBtn = isEnglish ? "LOGIN" : "ΕΙΣΟΔΟΣ";
		languageLabel = isEnglish ? "Languages" : "Γλώσσες";
	}
	
}
