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

/**
 *
 * @author SON
 */
public class FriendDAO {

	public static void addFriend(Friend table) {
		Session ss = HibernateUtil.openSession();
		ss.getTransaction().begin();
		ss.save(table);
		ss.getTransaction().commit();
		ss.close();
	}

	public static void EditRecord(Friend friend) {
		Session ss = HibernateUtil.openSession();
		ss.getTransaction().begin();
		ss.update(friend);
		ss.getTransaction().commit();
		ss.close();
	}

	public static User getUserById(String userId) {
		Session session = HibernateUtil.openSession();
		Query query = session.createQuery("FROM user WHERE userId=:userId ");
		query.setParameter("userId", userId);
		User user = (User) query.uniqueResult();
		session.close();
		return user;
	}

	public static List<Friend> allFriend() {
		Session ss = HibernateUtil.openSession();
		List<Friend> friends = ss.createQuery("FROM friend").list();
		ss.close();
		return friends;
	}

	public static boolean CheckStt(String nameFromId, String nameToId) {
		List<Friend> friend = null;
		boolean b = false;
		Session session = HibernateUtil.openSession();
		session.getTransaction().begin();
		Query query = session.createQuery("FROM friend WHERE friendFromId=:friendFromId and friendToId=:friendToId");
		query.setParameter("friendFromId", nameFromId);
		query.setParameter("friendToId", nameToId);
		friend = query.list();
		for (int i = 0; i < friend.size(); i++) {
			Friend f = friend.get(i);
			if (f.getStatus() == 1) {
				b = true;
			}
			break;
		}
		session.close();
		return b;
	}

	public static boolean CheckExist(String userFromId, String userToId) {
		List<Friend> friend = FriendDAO.allFriend();
		boolean b = false;
		for (int i = 0; i < friend.size(); i++) {
			Friend f = friend.get(i);
			if (f.getUserFromId().equals(userFromId) && f.getUserToId().equals(userToId)) {
				b = true;
			}
			break;
		}
		return b;
	}

	public static Friend getFriend(String userFromId, String userToId) {
		Friend f = null;
		Session session = HibernateUtil.openSession();
		Query query = session.createQuery("FROM friend WHERE friendFromId=:friendFromId and friendToId=:friendToId");
		query.setParameter("friendFromId", userFromId);
		query.setParameter("friendToId", userToId);
		f = (Friend) query.uniqueResult();
		session.close();
		return f;
	}

	// @SuppressWarnings("null")
	// public static List<User> getListFriend(List<Friend> list) {
	// List<User> l = null;
	// Iterator<Friend> ite = list.iterator();
	// while (ite.hasNext()) {
	// // String nameToId = ite.next().getFriendId();
	// // User u = FriendDAO.getUserById(nameToId);
	// // l.add(u);
	// }
	// return l;
	// }
	// @SuppressWarnings("unchecked")
	// public static List<User> searchBySttToGetSuggest(User user) throws
	// STException.EmptySuggest {
	// List<Friend> list = null;
	// List<User> listSuggest = null;
	// Session session = HibernateUtil.openSession();
	// Query query =
	// session.createQuery("FROM friend WHERE status=:status and friendFromId=:friendFromId");
	// query.setParameter("status", 1);
	// query.setParameter("friendFromId", user.getUserId());
	// list = query.list();
	// if (list != null) {
	// listSuggest = FriendDAO.getListFriend(list);//lay lai danh sach nhung loi
	// goi y
	// }
	// else{
	// throw new STException.EmptySuggest("Empty Suggest");
	// }
	// session.close();
	// return listSuggest;
	// }
	// @SuppressWarnings("unchecked")
	// public static List<User> searchBySttToGetFriend(User user) throws
	// STException.EmptyFriend{
	// List<Friend> list = null;
	// List<User> listFriend = null;
	// Session session = HibernateUtil.openSession();
	// Query query =
	// session.createQuery("FROM friend WHERE status=:status and friendFromId=:friendFromId");
	// query.setParameter("status", 2);
	// query.setParameter("friendFromId", user.getUserId());
	// list = query.list();
	// if (list != null) {
	// listFriend = FriendDAO.getListFriend(list);//lay lai danh sach nhung loi
	// goi y
	// }
	// else{
	// throw new STException.EmptyFriend("Empty Friend");
	// }
	// session.close();
	// return listFriend;
	// }
	// @SuppressWarnings("unchecked")
	// public static List<User> searchBySttToGetRequest(User user) throws
	// STException.EmptyRequest {
	// List<Friend> list = null;
	// List<User> listRequest = null;
	// Session session = HibernateUtil.openSession();
	// Query query =
	// session.createQuery("FROM friend WHERE status=:status and friendToId=:friendToId");
	// query.setParameter("status", 2);
	// query.setParameter("friendToId", user.getUserId());
	// list = query.list();
	// if (list != null) {
	// listRequest = FriendDAO.getListFriend(list);
	// }
	// else{
	// throw new STException.EmptyRequest("Empty Request");
	// }
	// session.close();
	// return listRequest;
	// public static boolean CheckToId(String nameToId) {
	// List<Friend> friend = null;
	// boolean b = false;
	// Session session = HibernateUtil.openSession();
	// session.getTransaction().begin();
	// Query query = session.createQuery("FROM friend");
	// friend = query.list();
	// for (int i = 0; i < friend.size(); i++) {
	// Friend f = friend.get(i);
	// if (f.getUserToId().getUserId().equals(nameToId)) {
	// b = true;
	// break;
	// }
	// }
	// return b;
	// }

	// public static boolean CheckFormId(String nameToId) {
	// List<Friend> friend = null;
	// boolean b = false;
	// Session session = HibernateUtil.openSession();
	// session.getTransaction().begin();
	// Query query = session.createQuery("FROM friend");
	// friend = query.list();
	// for (int i = 0; i < friend.size(); i++) {
	// Friend f = friend.get(i);
	// if (f.getUserFromId().getUserId().equals(nameToId)) {
	// b = true;
	// break;
	// }
	// }
	// return b;
	// }
}
