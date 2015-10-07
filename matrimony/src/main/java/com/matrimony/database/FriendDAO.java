/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrimony.database;

import java.sql.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.matrimony.entity.Friend;
import com.matrimony.entity.User;
import com.matrimony.util.HibernateUtil;
import java.util.ArrayList;

/**
 *
 * @author SON
 */
public class FriendDAO {

    public static void addFriend(Friend friend) {
        Session ss = HibernateUtil.openSession();
        ss.getTransaction().begin();
        ss.save(friend);
        ss.getTransaction().commit();
        ss.close();
    }

    public static void removeFriend(Friend friend) {
        Session ss = HibernateUtil.openSession();
        ss.getTransaction().begin();
        ss.delete(friend);
        ss.getTransaction().commit();
        ss.close();
    }

    public static void UpdateFriend(Friend friend) {
        Session ss = HibernateUtil.openSession();
        ss.getTransaction().begin();
        ss.update(friend);
        ss.getTransaction().commit();
        ss.close();
    }

    public static List<Friend> allFriend() {
        Session ss = HibernateUtil.openSession();
        List<Friend> friends = ss.createQuery("FROM friend").list();
        ss.close();
        return friends;
    }

    public static Friend GetFriend(String userFromId, String userToId) {
        List<Friend> friends = FriendDAO.allFriend();
        Friend friend = new Friend();
        for (int i = 0; i < friends.size(); i++) {
            if (userFromId.equalsIgnoreCase(friends.get(i).getUserFromId()) && userToId.equalsIgnoreCase(friends.get(i).getUserToId())) {
                friend = friends.get(i);
            } else {
                if (userFromId.equalsIgnoreCase(friends.get(i).getUserToId()) && userToId.equalsIgnoreCase(friends.get(i).getUserFromId())) {
                    friend = friends.get(i);
                } else {
                    friend = null;
                }
            }
        }
        return friend;
    }

    public static User getUserById(String userId) {
        Session ss = HibernateUtil.openSession();
        Query query = ss.createQuery("FROM user WHERE id=:id");
        query.setParameter("id", userId);
        System.out.println("ok");
        User u = (User) query.uniqueResult();
        return u;
    }

    public static List<User> ListFriend(String nameFormId) {
        List<User> listUser = new ArrayList<>();
        Session session = HibernateUtil.openSession();
        Query query = session.createQuery("FROM friend WHERE status=:status");
        query.setParameter("status", 2);
        List<Friend> list = query.list();
        for (int i = 0; i < list.size(); i++) {
            if (nameFormId.equals(list.get(i).getUserFromId())) {
                User u = FriendDAO.getUserById(list.get(i).getUserToId());
                listUser.add(u);
            }
            if (nameFormId.equals(list.get(i).getUserToId())) {
                User u = FriendDAO.getUserById(list.get(i).getUserFromId());
                listUser.add(u);
            }
        }
        session.close();
        return listUser;
    }

    public static List<User> ListRequest(String nameFormId) {
        List<User> listUser = new ArrayList<>();
        Session session = HibernateUtil.openSession();
        Query query = session.createQuery("FROM friend WHERE status=:status and userFromId=:userFromId");
        query.setParameter("userFromId", nameFormId);
        query.setParameter("status", 1);
        List<Friend> list = query.list();
        for (int i = 0; i < list.size(); i++) {
            User u = FriendDAO.getUserById(list.get(i).getUserToId());
            listUser.add(u);
        }
        session.close();
        return listUser;
    }

    public static List<User> ListInvite(String nameFormId) {
        List<User> listUser = new ArrayList<>();
        Session session = HibernateUtil.openSession();
        Query query = session.createQuery("FROM friend WHERE status=:status and userToId=:userToId");
        query.setParameter("userToId", nameFormId);
        query.setParameter("status", 1);
        List<Friend> list = query.list();
        for (int i = 0; i < list.size(); i++) {
            User u = FriendDAO.getUserById(list.get(i).getUserFromId());
            listUser.add(u);
        }
        session.close();
        return listUser;
    }
}
