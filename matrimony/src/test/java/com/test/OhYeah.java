/**
 * 
 */
package com.test;

import org.hibernate.Session;

import com.matrimony.database.UserDAO;
import com.matrimony.database.UserPreferenceDAO;
import com.matrimony.entity.City;
import com.matrimony.entity.Country;
import com.matrimony.entity.User;
import com.matrimony.util.HibernateUtil;

/**
 * @author SON
 *
 */
public class OhYeah {
	public static void main(String[] args) {
		HibernateUtil.getCurrentSession();
	}
}
