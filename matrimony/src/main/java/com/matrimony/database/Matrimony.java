package com.matrimony.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import com.matrimony.entity.User;
import com.matrimony.entity.UserPreference;
import com.matrimony.exception.STException.ContactNumberAlready;
import com.matrimony.exception.STException.EmailAlready;
import com.matrimony.exception.STException.NotNativeAccount;
import com.matrimony.exception.STException.UserNotExists;
import com.matrimony.exception.STException.WrongPassword;
import com.matrimony.model.MeasureHeart;

public class Matrimony {

	public static List<User> getSuggestUsers(User user) {
		List<User> list = new ArrayList<User>();
		List<User> users = UserDAO.allUsers().stream()
				.filter(u -> !u.getId().equals(user.getId()) && !u.getGender().equals(user.getGender()))
				.collect(Collectors.toList());
		for (int i = 0; i < users.size(); i++) {
			if (FriendDAO.GetFriend(user.getId(), users.get(i).getId()) == null) {
				list.add(users.get(i));
			}
		}
		return list;
	}

	public static Map<User, MeasureHeart> suggest(User user) {
		Map<User, MeasureHeart> map = new HashMap<User, MeasureHeart>();
		UserPreference preference = user.getUserPreference();
		System.out.println("like : "+preference);
		String[] ageGap = preference.getAgeGap().split("-");
		String[] heightGap = preference.getHeightGap().split("-");
		String[] weightGap = preference.getWeightGap().split("-");
		for (User u : UserDAO.allUsers()) {
			if (u.getGender().equals(preference.getGender())) {
				int percent = 0;
				int uage = -1;
				if (u.getBirthday() != null)
					UserDAO.getAgeByBirthday(u.getBirthday());
				if (uage != -1 && Integer.valueOf(ageGap[0]) < uage && uage > Integer.valueOf(ageGap[1]))
					percent += 20;
				if (Integer.valueOf(heightGap[0]) < user.getHeight()
						&& user.getHeight() > Integer.valueOf(heightGap[1]))
					percent += 10;
				if (Integer.valueOf(weightGap[0]) < user.getWeight()
						&& user.getWeight() > Integer.valueOf(weightGap[1]))
					percent += 10;
				if (u.getCountryside() != null && u.getCountryside().equals(user.getCountryside())) {
					percent += 5;
					if (u.getHometown() != null && u.getHometown().equals(user.getHometown()))
						percent += 7;
				}
				if (u.getReligion() != null && u.getReligion().equals(preference.getReligion()))
					percent += 5;
				if (u.getMaritalStatus() != null && u.getMaritalStatus().equals(preference.getMaritalStatus()))
					percent += 10;
				MeasureHeart mh = new MeasureHeart(percent);
				map.put(u, mh);
			}
		}
		ValueMapComparator vmc = new ValueMapComparator(map);
		Map<User, MeasureHeart> sortedMap = new TreeMap<User, MeasureHeart>(vmc);
		sortedMap.putAll(map);
		System.out.println("so nguoi: " + sortedMap.size());
		return sortedMap;
	}

	
}
