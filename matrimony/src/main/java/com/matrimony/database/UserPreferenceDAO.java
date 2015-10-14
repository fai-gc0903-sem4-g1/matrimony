/**
 * 
 */
package com.matrimony.database;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.Session;

import com.matrimony.entity.User;
import com.matrimony.entity.UserPreference;
import com.matrimony.model.Convention;
import com.matrimony.util.HibernateUtil;

/**
 * @author SON
 *
 */
public class UserPreferenceDAO {
	public static void add(UserPreference userPreference){
		Session ss=HibernateUtil.getCurrentSession();
		ss.beginTransaction();
		ss.save(userPreference);
		ss.getTransaction().commit();
	}
	
	public static void initUserPrefrence(User user) {
    	UserPreference userPreference=new UserPreference();
    	
    	userPreference.setId(user.getId());
    	userPreference.setCountryside(user.getCountryside()==null?"":user.getCountryside());
    	userPreference.setHometown(user.getHometown()==null?"":user.getHometown());
    	userPreference.setReligion(user.getReligion()==null?"":user.getReligion());
    	userPreference.setMaritalStatus(user.getMaritalStatus()==null?"":user.getMaritalStatus());
    	userPreference.setHeightGap(String.valueOf(user.getHeight()-15)+"-"+String.valueOf(user.getHeight()+15));
    	userPreference.setWeightGap(String.valueOf(user.getWeight()-15)+"-"+String.valueOf(user.getWeight()+15));
    	
    	Calendar calendar=DateUtils.toCalendar(user.getBirthday());
    	Calendar currentCalendar=DateUtils.toCalendar(new Date());
    	int age=currentCalendar.get(Calendar.YEAR)-calendar.get(Calendar.YEAR);
    	userPreference.setAgeGap(String.valueOf(age-5)+"-"+String.valueOf(age+5));
    	
    	if(user.getGender().equals(Convention.FEMALE)){
    		userPreference.setGender(Convention.MALE);
    	}
    	else{
    		userPreference.setGender(Convention.FEMALE);
    	}
    	
    	UserPreferenceDAO.add(userPreference);
    }
}
