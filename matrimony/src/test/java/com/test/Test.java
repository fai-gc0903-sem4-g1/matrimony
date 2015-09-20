package com.test;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

import org.apache.commons.lang3.time.DateUtils;

import com.matrimony.database.UserDAO;
import com.matrimony.entity.Transaction;
import com.matrimony.entity.User;
import com.matrimony.exception.STException.ContactNumberAlready;
import com.matrimony.exception.STException.EmailAlready;
import com.matrimony.util.HibernateUtil;

public class Test {
	public static void main(String[] args) {
//		User user=new User();
//		user.setEmail("who_care@yahoo.com");
//		user.setPassword("1234");
//		user.setBirthday(new Date(System.currentTimeMillis()));
//		user.setFirstName("Cao Son");
//		user.setLastName("Dam");
//		user.setCreateAt(new Timestamp(System.currentTimeMillis()));
//		user.setGender("male");
//		try {
//			UserDAO.add(user);
//		} catch (EmailAlready e) {
//			System.out.println(e);
//		} catch (ContactNumberAlready e) {
//			System.out.println(e);
//		}
		
		User getUser=UserDAO.findById("990258dd4feab9d7014feaba409f0000");
		System.out.println(getUser);
		System.out.println(getUser.getTransactions());
		
		
	}
}
