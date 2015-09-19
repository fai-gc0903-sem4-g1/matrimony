package com.test;

import java.sql.Date;
import java.util.Calendar;
import java.util.Locale;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.jpa.criteria.expression.function.LocateFunction;

import com.matrimony.util.GeoIP;
import com.maxmind.geoip2.model.CountryResponse;
import com.maxmind.geoip2.record.Country;

public class TestIP {
	public static void main(String[] args) {
		Date current=new Date(System.currentTimeMillis());
		int year=DateUtils.toCalendar(current).get(Calendar.YEAR);
		System.out.println(year);
	}
}
