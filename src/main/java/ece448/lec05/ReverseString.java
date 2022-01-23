package ece448.lec05;

public class ReverseString {
	public static String reverse(String str) {
		StringBuilder sb = new StringBuilder(str);
		return sb.reverse().toString();
	}
}
