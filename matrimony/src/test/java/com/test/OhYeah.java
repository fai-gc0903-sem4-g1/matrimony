/**
 * 
 */
package com.test;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;

import com.matrimony.database.UserDAO;
import com.matrimony.entity.City;
import com.matrimony.entity.Country;
import com.matrimony.entity.Friend;
import com.matrimony.entity.Notification;
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
		
		Session ss = HibernateUtil.getCurrentSession();
		
//		ss.beginTransaction();
//		User user=(User) ss.get(User.class, "990258dd506b74d301506b74d9860000");
//		System.out.println(user);
//		Transaction tran=new Transaction();
//		tran.setId("OIUOIU");
//		ss.save(tran);
//		System.out.println("added");
//		
//		ss.getTransaction().commit();
		
	}
}
