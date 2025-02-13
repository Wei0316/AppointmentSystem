package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormatTool {
	public static void main(String[] args) {
		System.out.print(phoneFormat("02-27370269"));

	}

	static String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
	static Pattern emailPattern = Pattern.compile(emailRegex);
	static String phoneRegex = "^[0-9]{10}$";
	static Pattern phonePattern = Pattern.compile(phoneRegex);
	static String passwordRegex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{6,}$";
	static Pattern passwordPattern = Pattern.compile(passwordRegex);

	public static boolean emailFormat(String email) {
		Matcher emailFormat = emailPattern.matcher(email);
		return emailFormat.matches();
	}

	public static boolean phoneFormat(String phone) {
		Matcher phoneFormat = phonePattern.matcher(phone);
		return phoneFormat.matches();
	}

	public static boolean passwordFormat(String password) {
		Matcher passwordFormat = passwordPattern.matcher(password);
		return passwordFormat.matches();
	}

}
