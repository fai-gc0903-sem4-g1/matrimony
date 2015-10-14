/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrimony.database;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.matrimony.entity.Friend;
import com.matrimony.entity.Friend.RequestComparator;
import com.matrimony.entity.User;
import com.matrimony.util.HibernateUtil;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author SON
 */
public class FriendDAO{

    public static void addFriend(Friend friend) {
        Session ss = HibernateUtil.getCurrentSession();
        ss.beginTransaction();
        ss.save(friend);
        ss.getTransaction().commit();
    }

    public static void removeFriend(Friend friend) {
        Session ss = HibernateUtil.getCurrentSession();
        ss.beginTransaction();
        ss.delete(friend);
        ss.getTransaction().commit();
    }

    public static void AcceptFriend(String nameFormId, String nameToId) {
        Session ss = HibernateUtil.getCurrentSession();
        ss.beginTransaction();
        Query query = ss.createQuery("FROM friend WHERE userFromId=:userFromId and userToId=:userToId");
        query.setParameter("userFromId", nameToId);
        query.setParameter("userToId", nameFormId);
        Friend friend = (Friend) query.uniqueResult();
        friend.setStatus(2);
        ss.update(friend);
        ss.getTransaction().commit();
    }

    public static List<User> ListFriend(String nameFormId) {
        List<User> listUser = new ArrayList<>();
        Session session = HibernateUtil.getCurrentSession();
        Query query = session.createQuery("FROM friend WHERE status=:status");
        query.setParameter("status", 2);
        List<Friend> list = query.list();
        for (int i = 0; i < list.size(); i++) {
            if (nameFormId.equals(list.get(i).getUserFromId())) {
                User u = list.get(0).getUserToId();
                listUser.add(u);
            }
            if (nameFormId.equals(list.get(i).getUserToId())) {
                User u = list.get(0).getUserFromId();
                listUser.add(u);
            }
        }
        return listUser;
    }

    public static List<User> ListRequest(String nameFormId) {
        List<User> listUser = new ArrayList<>();
        Session session = HibernateUtil.getCurrentSession();
        Query query = session.createQuery("FROM friend WHERE status=:status and userFromId=:userFromId");
        query.setParameter("userFromId", nameFormId);
        query.setParameter("status", 1);
        List<Friend> list = query.list();
        Collections.sort(list,new Friend.RequestComparator());
        for (int i = 0; i < list.size(); i++) {
            User u = list.get(0).getUserToId();
            listUser.add(u);
        }
        return listUser;
    }

    public static List<User> ListInvite(String nameFormId) {
        List<User> listUser = new ArrayList<>();
        Session session = HibernateUtil.getCurrentSession();
        Query query = session.createQuery("FROM friend WHERE status=:status and userToId=:userToId");
        query.setParameter("userToId", nameFormId);
        query.setParameter("status", 1);
        List<Friend> list = query.list();
        for (int i = 0; i < list.size(); i++) {
            User u = list.get(0).getUserFromId();
            listUser.add(u);
        }
        return listUser;
    }

    public static Friend GetFriend(String nameFromId, String nameToId) {
        Friend f = null;
        Session session = HibernateUtil.getCurrentSession();
        Query query = session.createQuery("FROM friend WHERE userFromId=:userFromId");
        query.setParameter("userFromId", nameFromId);
        List<Friend> l = query.list();
        Query query0 = session.createQuery("FROM friend WHERE userToId=:userToId");
        query0.setParameter("userToId", nameFromId);
        List<Friend> list = query0.list();
        for (int i = 0; i < l.size(); i++) {
            list.add(l.get(i));
        }
        for (int i = 0; i < list.size(); i++) {
            if (nameToId.equals(list.get(i).getUserFromId()) || nameToId.equals(list.get(i).getUserToId())) {
                f = list.get(i);
            }
        }
        return f;
    }

    public static List<Friend> allFriend() {
        Session ss = HibernateUtil.getCurrentSession();
        List<Friend> friends = ss.createQuery("FROM friend").list();
        return friends;
    }

    public static User getUserById(String userId) {
        Session ss = HibernateUtil.getCurrentSession();
        Query query = ss.createQuery("FROM user WHERE id=:id");
        query.setParameter("id", userId);
        User u = (User) query.uniqueResult();
        return u;
    }

    public static int getStatus(String userFromId, String userToId) {
        Friend f = FriendDAO.GetFriend(userFromId, userToId);
        int status = 0;
        if (f == null) {
            return status;
        } else {
            status = f.getStatus();
        }
        return status;
    }

    public static List<User> ListTopRequest(String nameFormId) {
        List<User> listUser = new ArrayList<>();
        Session session = HibernateUtil.getCurrentSession();
        Query query = session.createQuery("FROM friend WHERE status=:status and userFromId=:userFromId");
        query.setParameter("userFromId", nameFormId);
        query.setParameter("status", 1);
        List<Friend> list = query.list();
        Collections.sort(list,new Friend.RequestComparator());
        for (int i = 0; i < list.size(); i++) {
            User u = list.get(0).getUserToId();
            listUser.add(u);
        }
        return listUser;
    }
    
}
