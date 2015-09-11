package com.test;

import java.util.Locale;

import org.hibernate.jpa.criteria.expression.function.LocateFunction;

import com.matrimony.util.GeoIP;
import com.maxmind.geoip2.model.CountryResponse;
import com.maxmind.geoip2.record.Country;

public class TestIP {
	public static void main(String[] args) {
		CountryResponse country=GeoIP.getCountry("1.55.117.61");
		System.out.println(country.getCountry());
		Locale l=new Locale("", country.getCountry().getIsoCode());
		System.out.println(l.getISO3Language());
//		Locale.for
//		for(String str:l.getISOLanguages()){
//			System.out.println(str);
//		}
	}
}
