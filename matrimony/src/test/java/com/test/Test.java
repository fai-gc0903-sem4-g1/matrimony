package com.test;

import java.util.regex.Pattern;

import com.matrimony.model.Regex;


public class Test {
	public static void main(String[] args) {
		boolean check=Pattern.matches(Regex.NAME, "Mỹ Mạnh");
		System.out.println(check);
		
	}
}
