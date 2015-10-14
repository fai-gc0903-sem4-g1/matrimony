/**
 * 
 */
package com.test;

import com.matrimony.database.UserDAO;
import com.matrimony.entity.User;
import com.matrimony.exception.STException.NotNativeAccount;
import com.matrimony.exception.STException.UserNotExists;
import com.matrimony.exception.STException.WrongPassword;
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
