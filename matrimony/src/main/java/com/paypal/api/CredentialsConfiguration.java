package com.paypal.api;

import java.util.Properties;

public class CredentialsConfiguration {
	private final static String USERNAME="matrimony_master_api1.gmail.com";
	private final static String PASSWORD="VVHHZGZRV4FWZLVX";
	private final static String SIGNATURE="AiPC9BjkCyDFQXbSkoZcgqH3hpacArAciuAhnPhFDqUjrxDyeiJH4vpW";
	private final static String APP_ID="APP-80W284485P519543T";
	public final static String SAND_BOX="https://www.sandbox.paypal.com/cgi-bin/webscr?cmd=_ap-payment&paykey=";
	
	public final static String TREASURE_EMAIL="matrimony_master@gmail.com";
	
	public static Properties getConfig(){
		Properties config = new Properties();
		config.put("mode", "sandbox"); // Load the map with all mandatory parameters
		config.put("acct1.UserName", USERNAME);
		config.put("acct1.Password", PASSWORD);
		config.put("acct1.Signature", SIGNATURE);
		config.put("acct1.AppId", APP_ID);
		return config;
	}
}
