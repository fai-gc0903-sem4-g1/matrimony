/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrimony.database;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.Session;

import com.matrimony.entity.User;
import com.matrimony.exception.STException;
import com.matrimony.exception.STException.ContactNumberAlready;
import com.matrimony.exception.STException.EmailAlready;
import com.matrimony.exception.STException.NotNativeAccount;
import com.matrimony.exception.STException.UserNotExists;
import com.matrimony.exception.STException.WrongPassword;
import com.matrimony.security.HashUtil;
import com.matrimony.util.HibernateUtil;

/**
 *
 * @author SON
 */
@SuppressWarnings("unchecked")
public class UserDAO {
	public static List<User> allUsers() {
		Session ss = HibernateUtil.getCurrentSession();
		ss.beginTransaction();
		List<User> accounts = ss.createQuery("FROM user").list();
		ss.getTransaction().commit();
		return accounts;
	}

	public static void add(User user) {
		Session ss = HibernateUtil.getCurrentSession();
		ss.beginTransaction();
		ss.save(user);
		ss.getTransaction().commit();
	}

	public static void Update(User user) {
		if (user.getSalt() == null) {
			String newSalt = HashUtil.generateSalt(user.getUsername());
			String newPasswordHashed = HashUtil.hashPassword(user.getPassword(), newSalt);
			user.setPassword(newPasswordHashed);
			user.setSalt(newSalt);
		}
		Session ss = HibernateUtil.getCurrentSession();
		ss.beginTransaction();
		ss.update(user);
		ss.getTransaction().commit();
	}

	public static User findByUsername(String id) {
		Session ss = HibernateUtil.getCurrentSession();
		ss.beginTransaction();
		User account = (User) ss.createQuery("from user where username=?").setString(0, id).uniqueResult();
		ss.getTransaction().commit();
		return account;
	}

	public static User findById(String id) {
		Session ss = HibernateUtil.getCurrentSession();
		ss.beginTransaction();
		User account = (User) ss.get(User.class, id);
		ss.getTransaction().commit();
		return account;
	}

	public static User findByEmail(String email) {
		Session ss = HibernateUtil.getCurrentSession();
		ss.beginTransaction();
		User account = (User) ss.createQuery("from user where email=?").setString(0, email).uniqueResult();
		ss.getTransaction().commit();
		return account;
	}

	public static User findByContactNumber(String contactNumber) {
		Session ss = HibernateUtil.getCurrentSession();
		ss.beginTransaction();
		User user = (User) ss.createQuery("from user where contactNumber=? and contactNumber!=''")
				.setString(0, contactNumber).uniqueResult();
		ss.getTransaction().commit();
		System.out.println(user);
		return user;
	}

	public static User findByEmailOrContactNumberOrUsername(String string) {
		User account;
		if ((account = findByEmail(string)) == null) {
			if ((account = findByContactNumber(string)) == null) {
				account = findByUsername(string);
			}
		}
		return account;
	}

	public static User register(User user) throws EmailAlready, ContactNumberAlready {
		if (findByEmail(user.getEmail()) != null) {
			throw new STException.EmailAlready("Add user: email already");
		} else if (findByContactNumber(user.getContactNumber()) != null) {
			throw new STException.ContactNumberAlready("Add user: contact number already");
		} else {
			Timestamp now = new Timestamp(System.currentTimeMillis());
			if (user.getPassword() != null) {
				user.setSalt(HashUtil.generateSalt(UUID.randomUUID().toString()));
				user.setPassword(HashUtil.hashPassword(user.getPassword(), user.getSalt()));
			}
			user.setCreateAt(now);

			// ADD USER
			add(user);
			UserPreferenceDAO.initUserPrefrence(user);
			String emailOrPhone = user.getEmail() != null ? user.getEmail() : user.getContactNumber();
			User temp = findByEmailOrContactNumberOrUsername(emailOrPhone);
			temp.setUsername(temp.getId());

			// UPDATE USERNAME FOR USER
			Update(temp);
			return temp;
		}
	}

	public static User login(User user) throws STException.WrongPassword, NotNativeAccount, UserNotExists {
		User userFind = findByEmailOrContactNumberOrUsername(user.getUsername());
		if (userFind == null) {
			throw new STException.UserNotExists("Login: user not exists");
		} else if (!userFind.getRegMethod().equalsIgnoreCase("Native")) {
			throw new STException.NotNativeAccount("Login: This account login with social network");
		}
		String passwordHased = HashUtil.hashPassword(user.getPassword(), userFind.getSalt());
		if (userFind.getPassword().equals(passwordHased)) {
			// userFind.setLoginTime(new Timestamp(System.currentTimeMillis()));
			// userFind.setIpLogin(user.getIpLogin());
			// UPDATE USER WHEN LOGGED IN
			Update(userFind);
			return userFind;
		} else {
			throw new STException.WrongPassword("Login: Wrong password");
		}
	}

	public static boolean hasExpiries(User user) {
		Timestamp now = new Timestamp(System.currentTimeMillis());
		if (user.getExpiries().after(now))
			return true;
		else
			return false;
	}

	public static int getAgeByBirthday(Date birthday) {
		Calendar now = DateUtils.toCalendar(new Date(System.currentTimeMillis()));
		Calendar before = DateUtils.toCalendar(birthday);
		return now.get(Calendar.YEAR) - before.get(Calendar.YEAR);
	}

	public static void main(String[] args) {
		User u1=findById("990258dd506f431d01506f4347b20000");
		System.out.println(u1.isVerified());
	}
}
