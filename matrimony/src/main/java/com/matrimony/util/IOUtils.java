/**
 * 
 */
package com.matrimony.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author SON
 *
 */
public class IOUtils {
	public static String toString(InputStream is) throws IOException{
		StringBuilder sb=new StringBuilder();
		String line;
		BufferedReader br=new BufferedReader(new InputStreamReader(is));
		while((line=br.readLine())!=null){
			sb.append(line);
			sb.append("\n");
		}
		br.close();
		return sb.toString();
	}
	
	public static byte[] toByteArray(InputStream is) throws IOException{
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		int buffer;
		while((buffer=is.read())!=-1){
			baos.write(buffer);
		}
		baos.close();
		return baos.toByteArray();
	}
}
