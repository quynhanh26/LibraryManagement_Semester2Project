package helper;

public class Regex {
	public final static String NUM = "[0-9]+";
	public final static String ID = "^([a-zA-Z0-9]\\s*){3,20}$";
	public final static String TITLE = "^([a-zA-Z0-9]\\s*){5,250}$";
	public final static String CHARS = "^([a-zA-Z]\\s*){0,250}$";
	public final static String Status = "[0-1]";
	public final static String Email = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	public final static String DATE = "([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))";
}
