/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrimony.database;

import com.matrimony.entity.TableFriends;
import com.matrimony.entity.User;
import com.matrimony.exception.STException;
import com.matrimony.util.HibernateUtil;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author SON
 */
public class FriendDAO {

    public static void addTableFriends(TableFriends table) {
        Session ss = HibernateUtil.openSession();
        ss.getTransaction().begin();
        ss.save(table);
        ss.getTransaction().commit();
        ss.close();
    }

    public static boolean EditRecord(String nameFromId, String nameToId, int status) {
        int result = 0;
        Session session = HibernateUtil.openSession();
        session.getTransaction().begin();
        Query query = session.createQuery("Update friend set status = :status"
                + "WHERE friendFromId =:friendFromId friendtoId =:friendtoId");
        query.setParameter("status", status);
        query.setParameter("friendFromId", nameFromId);
        query.setParameter("friendToId", nameToId);
        result = query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return result > 0;
    }
    public static User getUserById(String userId){
        Session session = HibernateUtil.openSession();
        Query query = session.createQuery("FROM user WHERE accountId=:accountId ");
        query.setParameter("userId", userId);
        User user = (User) query.uniqueResult();
        session.close();
        return user;
    }

    public static List<User> getListFriend(List<TableFriends> list) {
        List<User> l = null;
        Iterator<TableFriends> ite = list.iterator();
        while (ite.hasNext()) {
            String nameToId = ite.next().getFriendToId();
            User u = FriendDAO.getUserById(nameToId);
            l.add(u);
        }
        return l;
    }

    public static List<User> searchBySttToGetSuggest(User user) throws STException.EmptySuggest {
        List list = null;
        List<User> listSuggest = null;
        Session session = HibernateUtil.openSession();
        Query query = session.createQuery("FROM friend WHERE status=:status and friendFromId=:friendFromId");
        query.setParameter("status", 1);
        query.setParameter("friendFromId", user.getUserId());
        list = query.list();
        if (list != null) {
            listSuggest = FriendDAO.getListFriend(list);//lay lai danh sach nhung loi goi y
        }
        else{
            throw new STException.EmptySuggest("Empty Suggest");
        }
        session.close();
        return listSuggest;
    }
    public static List<User> searchBySttToGetFriend(User user) throws STException.EmptyFriend{
        List list = null;
        List<User> listFriend = null;
        Session session = HibernateUtil.openSession();
        Query query = session.createQuery("FROM friend WHERE status=:status and friendFromId=:friendFromId");
        query.setParameter("status", 2);
        query.setParameter("friendFromId", user.getUserId());
        list = query.list();
        if (list != null) {
            listFriend = FriendDAO.getListFriend(list);//lay lai danh sach nhung loi goi y
        }
        else{
            throw new STException.EmptyFriend("Empty Friend");
        }
        session.close();
        return listFriend;
    }

    public static List<User> searchBySttToGetRequest(User user) throws STException.EmptyRequest {
        List list = null;
        List<User> listRequest = null;
        Session session = HibernateUtil.openSession();
        Query query = session.createQuery("FROM friend WHERE status=:status and friendToId=:friendToId");
        query.setParameter("status", 2);
        query.setParameter("friendToId", user.getUserId());
        list = query.list();
        if (list != null) {
            listRequest = FriendDAO.getListFriend(list);
        }
        else{
            throw new STException.EmptyRequest("Empty Request");
        }
        session.close();
        return listRequest;
    }
//    public static boolean CheckRequest(String nameFromId, String nameToId) throws STException.EmailAlready{
//        TableFriends table = null;
//        Session session = HibernateUtil.openSession();
//        session.getTransaction().begin();
//        Query query = session.createQuery("FROM TableFriends WHERE friendFromId=:friendFromId and friendToId=:friendToId");
//        query.setParameter("friendFromId", nameFromId);
//        query.setParameter("friendToId", nameToId);
//        table = (TableFriends) query.uniqueResult();
//        if(table == null){
//            throw new STException.EmailAlready("Request Sended");
//        }
//    }
}
