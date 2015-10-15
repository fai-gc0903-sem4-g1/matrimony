/**
 * 
 */
package com.test;

import org.hibernate.Session;

import com.matrimony.database.UserDAO;
import com.matrimony.entity.City;
import com.matrimony.entity.Country;
import com.matrimony.entity.Friend;
import com.matrimony.entity.Transaction;
import com.matrimony.entity.User;
import com.matrimony.util.HibernateUtil;
import com.paypal.sdk.openidconnect.Userinfo;

/**
 * @author SON
 *
 */
public class OhYeah {
	public static void main(String[] args) {
		String invite = "990258dd506b0a7401506b0adcfd0000";
		String beInvite = "990258dd506b0a7401506b0ae0670001";
		
		
//		Session ss = HibernateUtil.getCurrentSession();
//		ss.beginTransaction();
//		
//		User user=(User) ss.get(User.class, "990258dd506b0a7401506b0adcfd0000");
//		System.out.println(user);
//		Friend f=new Friend();
//		f.setUserInvite(user);
//		f.setUserBeInvite(user);
//		ss.save(f);
//		System.out.println("added");
//		
//		ss.getTransaction().commit();
		
//		Session ss=HibernateUtil.getCurrentSession();
//		ss.beginTransaction();
//		User user=(User) ss.get(User.class, "990258dd506b0a7401506b0adcfd0000");
//		Transaction tran=new Transaction();
//		tran.setId("12321312");
//		tran.setUser(user);
//		tran.setMethod("aoidjfsdoajfdasfka");
//		ss.save(tran);
		
//		ss.getTransaction().commit();

//		Session ss=HibernateUtil.getCurrentSession();
//		ss.beginTransaction();
//		Country country=(Country) ss.get(Country.class, "ST");
//		System.out.println(country);
//		
//		City c1=new City();
//		c1.setName("Hihi");
//		c1.setCountry1(country);
//		c1.setCountry2(country);
//		ss.save(c1);
//		ss.getTransaction().commit();
		
		Session ss = HibernateUtil.getCurrentSession();
		ss.beginTransaction();
		
		User user=(User) ss.get(User.class, "990258dd506b0a7401506b0adcfd0000");
		System.out.println(user);
		Friend f=new Friend();
		f.setUserInvite(user);
		f.setUserBeInvite(user);
		ss.save(f);
		System.out.println("added");
		
		ss.getTransaction().commit();
		
	}
}
