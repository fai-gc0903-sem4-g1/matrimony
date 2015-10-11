/**
 * 
 */
package com.matrimony.database;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
		Session ss=HibernateUtil.session;
		Transaction tran=ss.beginTransaction();
		ss.save(userPreference);
		tran.commit();
	}
	
	public static void initUserPrefrence(User user) {
    	UserPreference userPreference=new UserPreference();
    	
    	userPreference.setUserId(user.getId());
    	userPreference.setCountrysideLike(user.getCountryside()==null?"":user.getCountryside());
    	userPreference.setHometownLike(user.getHometown()==null?"":user.getHometown());
    	userPreference.setReligionLike(user.getReligion()==null?"":user.getReligion());
    	userPreference.setMaritalStatusLike(user.getMaritalStatus()==null?"":user.getMaritalStatus());
    	userPreference.setHeightGapLike(String.valueOf(user.getHeight()-15)+"-"+String.valueOf(user.getHeight()+15));
    	userPreference.setWeightGapLike(String.valueOf(user.getWeight()-15)+"-"+String.valueOf(user.getWeight()+15));
    	
    	Calendar calendar=DateUtils.toCalendar(user.getBirthday());
    	Calendar currentCalendar=DateUtils.toCalendar(new Date());
    	int age=currentCalendar.get(Calendar.YEAR)-calendar.get(Calendar.YEAR);
    	userPreference.setAgeGapLike(String.valueOf(age-5)+"-"+String.valueOf(age+5));
    	
    	if(user.getGender().equals(Convention.FEMALE)){
    		userPreference.setGenderLike(Convention.MALE);
    	}
    	else{
    		userPreference.setGenderLike(Convention.FEMALE);
    	}
    	
    	UserPreferenceDAO.add(userPreference);
    }
}
