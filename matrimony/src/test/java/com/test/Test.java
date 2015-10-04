package com.test;

import java.util.regex.Pattern;


public class Test {
	public static void main(String[] args) {
		boolean check=Pattern.matches("^male|female$", "femalef");
		System.out.println(check);
		
	}
}
