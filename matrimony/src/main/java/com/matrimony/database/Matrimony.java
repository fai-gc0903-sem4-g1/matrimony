package com.matrimony.database;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.time.DateUtils;

import com.matrimony.entity.User;
import com.matrimony.entity.UserPreference;
import com.matrimony.model.Convention;

public class Matrimony {

    public static List<User> getSuggestUsers(User user) {
        List<User> list = new ArrayList<User>();
        List<User> users = UserDAO.allUsers().stream()
                .filter(u -> !u.getId().equals(user.getId()) && !u.getGender().equals(user.getGender()))
                .collect(Collectors.toList());
        for (int i = 0; i < users.size(); i++) {
            if(FriendDAO.GetFriend(user.getId(), users.get(i).getId())==null){
                list.add(users.get(i));
            }
        }
        return list;
    }
    
    public static Map<User, String> suggest(User user) {
    	Map<User, String> map=new HashMap<User, String>();
    	UserDAO.allUsers().forEach(u->map.put(u, "0"));
        return map;
    }
    
    
    
    public static void main(String[] args) {
		// List<User>lst1=UserDAO.allAccounts();
        // System.out.println(lst1.size());
        // lst1.forEach(u->System.out.println(u.getUserId()));
        //
        // List<User>lst2=lst1.stream().filter(u->!u.getUserId().equals("990258dd4fcaf596014fcaf5a0d90000")).collect(Collectors.toList());
        // System.out.println(lst2.size());
        // lst2.forEach(u->System.out.println(u.getUserId()));
    }
}
