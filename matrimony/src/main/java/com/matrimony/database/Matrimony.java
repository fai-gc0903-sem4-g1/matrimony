package com.matrimony.database;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.naming.PartialResultException;

import com.matrimony.entity.User;
import com.matrimony.entity.UserPreference;
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
		UserPreference preference = user.getUserPreferences().iterator().next();
		String[] ageGap = preference.getAgeGapLike().split("-");
		String[] heightGap = preference.getHeightGapLike().split("-");
		String[] weightGap = preference.getWeightGapLike().split("-");
		for (User u : UserDAO.allUsers()) {
			if (u.getGender().equals(preference.getGenderLike())) {
				int percent = 0;
				int uage = UserDAO.getAgeByBirthday(user.getBirthday());
				if (Integer.valueOf(ageGap[0]) < uage && uage > Integer.valueOf(ageGap[1]))
					percent += 10;
				if (Integer.valueOf(heightGap[0]) < uage && uage > Integer.valueOf(heightGap[1]))
					percent += 10;
				if (Integer.valueOf(weightGap[0]) < uage && uage > Integer.valueOf(weightGap[1]))
					percent += 10;
				if (u.getCountryside().equals(user.getCountryside())) {
					percent += 10;
					if (u.getHometown().equals(user.getHometown()))
						percent += 10;
				}
				if (u.getReligion().equals(preference.getReligionLike()))
					percent += 10;
				if (u.getMaritalStatus().equals(preference.getMaritalStatusLike()))
					percent += 10;
				MeasureHeart mh = new MeasureHeart(percent);
				map.put(u, mh);
			}
		}
		ValueMapComparator vmc=new ValueMapComparator(map);
		Map<User, MeasureHeart> sortedMap=new TreeMap<User, MeasureHeart>(vmc);
		sortedMap.putAll(map);
		return sortedMap;
	}
	

	public static void main(String[] args) {
		String a = null;
		String b = null;
		if (a.equals(b)) {
			System.out.println("ok");
		}
	}
}
