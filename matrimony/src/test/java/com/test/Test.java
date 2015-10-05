package com.test;

import java.util.regex.Pattern;


public class Test {
	public static void main(String[] args) {
		boolean check=Pattern.matches("^[A-Za-z\\d]{8,}$","1234567cxz8");
		System.out.println(check);
		
	}
}
