package com.test;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import com.maxmind.db.Reader;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.model.CountryResponse;

public class TestIP {
	public static void main(String[] args) {
		File countryDB=new File(TestIP.class.getResource("/com/resoucres/GeoLite2-Country.mmdb").getFile());
		File cityDB=new File(TestIP.class.getResource("/com/resoucres/GeoLite2-City.mmdb").getFile());
		try {
			InetAddress inetAddr=InetAddress.getByName("173.252.74.22");
			DatabaseReader countryReader=new DatabaseReader.Builder(countryDB).build();
			DatabaseReader cityReader=new DatabaseReader.Builder(cityDB).build();
			CountryResponse countryResponse=countryReader.country(inetAddr);
			CityResponse cityResponse=cityReader.city(inetAddr);
			System.out.println(countryResponse);
			System.out.println(cityResponse);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GeoIp2Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void getLocation(String ipAddr){
		
	}
}
