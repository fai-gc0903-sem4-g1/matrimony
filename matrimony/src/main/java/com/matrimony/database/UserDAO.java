/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrimony.database;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import org.hibernate.Session;

import com.matrimony.entity.User;
import com.matrimony.exception.STException;
import com.matrimony.security.HashUtil;
import com.matrimony.util.HibernateUtil;

/**
 *
 * @author SON
 */
@SuppressWarnings("unchecked")
public class UserDAO {

    public static void add(User user) throws STException.EmailAlready, STException.ContactNumberAlready {
        if (findByEmail(user.getEmail()) != null) {
            throw new STException.EmailAlready("Add user: email already");
        } else if (findByContactNumber(user.getContactNumber()) != null) {
            throw new STException.ContactNumberAlready("Add user: contact number already");
        } else {
        	user.setSalt(HashUtil.generateSalt(UUID.randomUUID().toString()));
            user.setPassword(HashUtil.hashPassword(user.getPassword(), user.getSalt()));
            long currentMillis=System.currentTimeMillis();
            user.setCreateAt(new Timestamp(currentMillis));
            
            Session ss = HibernateUtil.session;
            ss.getTransaction().begin();
            ss.save(user);
            ss.getTransaction().commit();
            //ss.close();
            System.out.println("Added user " + user.getEmail());
        }
    }

    public static List<User> allUsers() {
        Session ss = HibernateUtil.session;
		List<User> accounts = ss.createQuery("FROM user").list();
        //ss.close();
        return accounts;
    }

    public static User findByUsername(String id) {
        Session ss = HibernateUtil.session;
        User account = (User) ss.createQuery("from user where username=?").setString(0, id).uniqueResult();
        //ss.close();
        return account;
    }

    public static User findById(String id) {
        User account = (User) HibernateUtil.session.get(User.class, id);
//        //ss.close();
        return account;
    }

    public static User findByEmail(String email) {
        Session ss = HibernateUtil.session;
        User account = (User) ss.createQuery("from user where email=?").setString(0, email).uniqueResult();
        //ss.close();
        return account;
    }

    public static User findByContactNumber(String contactNumber) {
        Session ss = HibernateUtil.session;
        User user = (User) ss.createQuery("from user where contactNumber=? and contactNumber!=''").setString(0, contactNumber).uniqueResult();
        //ss.close();
        System.out.println(user);
        return user;
    }

    public static void Update(User user) {
        Session ss = HibernateUtil.session;
        ss.getTransaction().begin();
        ss.update(user);
        ss.getTransaction().commit();
        //ss.close();
    }

    public static User login(String loginName, String password) throws STException.UsernameNotExist, STException.WrongPassword {
        System.out.println("Login name "+loginName);
        User user = findByEmailOrContactNumberOrUsername(loginName);
        if (user == null) {
            throw new STException.UsernameNotExist("Login: username not exists");
        }
        String passwordTemp = HashUtil.hashPassword(password, user.getSalt());
        if (user.getPassword().equals(passwordTemp)) {
            return user;
        } else {
            throw new STException.WrongPassword("Login: Wrong password");
        }
    }

    public static User findByEmailOrContactNumberOrUsername(String string) {
        User account;
        if ((account = findByEmail(string)) == null) {
            if ((account = findByContactNumber(string)) == null) {
                account = findByUsername(string);
            }
        }
        return account;
    }

    public static void main(String[] args) {
    }
}
