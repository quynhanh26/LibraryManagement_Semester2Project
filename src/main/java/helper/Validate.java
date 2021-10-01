package helper;

import java.time.LocalDate;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {

	public static boolean chiNhapSo( String str) {
		
		while (true) {
		
			Pattern pattern = Pattern.compile(Regex.NUM);
			Matcher matcher = pattern.matcher(str);
			if (matcher.matches()) {
				return true;
			}
			return false;
		}
	}

	

	public static boolean checkRegex(String regex, String input) {
		while (true) {
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(input);
			if (matcher.matches()) {
				return true;
			}
			
			return false;
		}
	}
	

}
