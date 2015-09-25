/**
 * 
 */
package com.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.Normalizer;

/**
 * @author SON
 *
 */
public class TestTwo {
	public static String stripAccents(String s) 
	{
	    s = Normalizer.normalize(s, Normalizer.Form.NFD);
	    s = s.replaceAll("[\\p{InCmbiningDiacriticalMarks}]", "");
	    return s;
	}
	
	public static void main(String[] args) {
		File file=new File(System.getProperty("user.home")+"/Desktop/name.txt");
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(file));
			String line;
			while((line=br.readLine())!=null){
				System.out.println(stripAccents(line.toLowerCase()));
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
