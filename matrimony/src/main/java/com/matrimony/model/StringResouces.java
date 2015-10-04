/**
 * 
 */
package com.matrimony.model;

import java.io.IOException;
import java.util.Properties;

/**
 * @author SON
 *
 */
public class StringResouces {
	private Properties data;
	public final static String vi_VN ="vi_VN";
	public final static String en_US ="en_US";
	public StringResouces(String lang){
		this.data=new Properties();
		try {
			this.data.load(getClass().getResourceAsStream("/com/matrimony/i18n/StringResource_"+lang+".properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Properties getData() {
		return data;
	}
}
