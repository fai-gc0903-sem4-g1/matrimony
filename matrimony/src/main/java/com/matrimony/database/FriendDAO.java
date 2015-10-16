/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrimony.database;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.event.spi.SaveOrUpdateEvent;

import com.matrimony.entity.Friend;
import com.matrimony.entity.User;
import com.matrimony.util.HibernateUtil;

/**
 *
 * @author SON
 */
@SuppressWarnings("unchecked")
public class FriendDAO {

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

	public static void update(Friend friend){
		Session ss=HibernateUtil.getCurrentSession();
		ss.beginTransaction();
		ss.update(friend);
		ss.getTransaction().commit();
	}
	
	// not used
	public static List<User> getRequestInvited(User user) {
		List<User> lst = new ArrayList<User>();
		for (Friend f : user.getRequestInvited()) {
			if (f.getState().equals("WAITTING"))
				lst.add(f.getUserBeInvite());
			System.out.println(f.getUserBeInvite().getId());
		}
		return lst;
	}
	

	public static List<User> getRequestAskInvited(User user) {
		User me=UserDAO.findById(user.getId());
		List<User> lst = new ArrayList<User>();
		for (Friend f : me.getRequestAskedInvited()) {
			System.out.println(f.getUserInvite().getId());
			if (f.getState().equals("WAITTING"))
				lst.add(f.getUserInvite());
		}
		return lst;
	}
	
	public static List<User> getMyFriends(User user){
		User me=UserDAO.findById(user.getId());
		List<User> lst=new ArrayList<User>();
		for(Friend f: me.getRequestInvited()){
			if("FRIEND".equals(f.getState()))
				lst.add(f.getUserBeInvite());
		}
		for(Friend f:me.getRequestAskedInvited()){
			if("FRIEND".equals(f.getState()))
				lst.add(f.getUserInvite());
		}
		return lst;
	}
	
	public static Friend getFriendByUsers(String userInviteId, String userBeInviteId){
		Session ss=HibernateUtil.getCurrentSession();
		ss.beginTransaction();
		Query q=ss.createQuery("from friend where userInviteId=? and userBeInviteId=?");
		q.setString(0, userInviteId);
		q.setString(1, userBeInviteId);
		Friend friend=(Friend) q.uniqueResult();
		ss.getTransaction().commit();
		return friend;
	}
	
	public static String getRelationshipState(User me,User person){
		me=UserDAO.findById(me.getId());
		System.out.println("================");
		System.out.println("My id"+ me.getId());
		System.out.println("My invite: "+me.getRequestInvited().size());
		System.out.println("Person check: "+person.getId());
		for(Friend f: me.getRequestInvited()){
			System.out.println(f.getUserBeInvite().getId());
			if(f.getUserBeInvite().getId().equals(person.getId())){
				return f.getState();
			}
		}
		
		
		return "chiu.";
	}

	public static void main(String[] args) {
		User me=UserDAO.findById("990258dd506f431d01506f4347b20000");
		User person=UserDAO.findById("990258dd506f431d01506f43e27c0020");
//		System.out.println(getRelationshipState(me, person ));
		
		System.out.println(getFriendByUsers("990258dd506f431d01506f4347b20000", "990258dd506f431d01506f43e27c0020"));
		
	}

	/* HOANG ANH CODE */

	public static void AcceptFriend(String nameFormId, String nameToId) {
		Session ss = HibernateUtil.getCurrentSession();
		ss.beginTransaction();
		Query query = ss.createQuery("FROM friend WHERE userInvite=? and userBeInvite=?");
		query.setString(0, nameToId);
		query.setString(1, nameFormId);
		Friend friend = (Friend) query.uniqueResult();
		friend.setStatus(2);
		ss.update(friend);
		ss.getTransaction().commit();
	}

	public static List<User> ListFriend(String nameFormId) {
		List<User> listUser = new ArrayList<>();
		Session session = HibernateUtil.getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("FROM friend WHERE status=?");
		query.setInteger(0, 2);
		List<Friend> list = query.list();
		session.getTransaction().commit();
		for (int i = 0; i < list.size(); i++) {
			if (nameFormId.equals(list.get(i).getUserInvite())) {
				User u = list.get(0).getUserBeInvite();
				listUser.add(u);
			}
			if (nameFormId.equals(list.get(i).getUserBeInvite())) {
				User u = list.get(0).getUserInvite();
				listUser.add(u);
			}
		}
		return listUser;
	}

	public static List<User> ListRequest(String nameFormId) {
		List<User> listUser = new ArrayList<>();
		Session session = HibernateUtil.getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("FROM Friend friend WHERE status=? and userInvite=?");
		query.setString(0, nameFormId);
		query.setInteger(1, 1);
		List<Friend> list = query.list();
		session.getTransaction().commit();
		Collections.sort(list, new Friend.RequestComparator());
		for (int i = 0; i < list.size(); i++) {
			User u = list.get(0).getUserBeInvite();
			listUser.add(u);
		}
		return listUser;
	}

	public static List<User> ListInvite(String nameToId) {
		List<User> listUser = new ArrayList<>();
		Session session = HibernateUtil.getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("FROM Friend friend WHERE status=? and userBeInvite=?");
		query.setString(0, nameToId);
		query.setInteger(1, 1);
		List<Friend> list = query.list();
		session.getTransaction().commit();
		for (int i = 0; i < list.size(); i++) {
			User u = list.get(0).getUserInvite();
			listUser.add(u);
		}
		return listUser;
	}

	public static Friend GetFriend(String nameFromId, String nameToId) {
		Friend f = null;
		Session session = HibernateUtil.getCurrentSession();
		Query query = session.createQuery("FROM friend WHERE userInvite=:userBeInvite");
		query.setParameter("userBeInvite", nameFromId);
		List<Friend> l = query.list();
		Query query0 = session.createQuery("FROM friend WHERE userToId=:userToId");
		query0.setParameter("userToId", nameFromId);
		List<Friend> list = query0.list();
		for (int i = 0; i < l.size(); i++) {
			list.add(l.get(i));
		}
		for (int i = 0; i < list.size(); i++) {
			if (nameToId.equals(list.get(i).getUserInvite()) || nameToId.equals(list.get(i).getUserBeInvite())) {
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
		Query query = session.createQuery("FROM friend WHERE status=:status and userInvite=:userBeInvite");
		query.setParameter("userBeInvite", nameFormId);
		query.setParameter("status", 1);
		List<Friend> list = query.list();
		Collections.sort(list, new Friend.RequestComparator());
		for (int i = 0; i < list.size(); i++) {
			User u = list.get(0).getUserBeInvite();
			listUser.add(u);
		}
		return listUser;
	}

}
