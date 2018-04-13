package Facade;

public class Facade {
	public static boolean isEmpty(String string) {
		if(string==null||string.equals(""))
			return true;
		return false;
	}
}
