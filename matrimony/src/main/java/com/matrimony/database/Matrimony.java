package com.matrimony.database;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.matrimony.entity.User;

public class Matrimony {

    public static List<User> getSuggestUsers(User user) {
        List<User> list = new ArrayList<User>();
        List<User> users = UserDAO.allUsers().stream()
                .filter(u -> !u.getId().equals(user.getId()) && !u.getGender().equals(user.getGender()))
                .collect(Collectors.toList());
        System.out.println(user.getId());
        for (int i = 0; i < users.size(); i++) {
            if(FriendDAO.GetFriend(user.getId(), users.get(i).getId())==null){
                list.add(users.get(i));
            }
        }
        System.out.println(users.size());
        return list;
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
