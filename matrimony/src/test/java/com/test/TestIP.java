package com.test;

import java.util.Locale;

import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.jpa.criteria.expression.function.LocateFunction;

import com.matrimony.util.GeoIP;
import com.maxmind.geoip2.model.CountryResponse;
import com.maxmind.geoip2.record.Country;

public class TestIP {
	public static void main(String[] args) {
    	System.out.println(RandomStringUtils.randomAlphanumeric(8));
	}
}
