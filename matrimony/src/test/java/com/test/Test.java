package com.test;

import java.sql.Date;
import java.util.Calendar;

import org.apache.commons.lang3.time.DateUtils;

public class Test {
	public static void main(String[] args) {
		Date current=new Date(System.currentTimeMillis());
		int year=DateUtils.toCalendar(current).get(Calendar.YEAR);
		System.out.println(year);
	}
}
