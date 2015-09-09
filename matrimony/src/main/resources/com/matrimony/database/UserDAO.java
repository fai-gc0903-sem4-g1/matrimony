/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrimony.database;

import com.matrimony.entity.User;
import com.matrimony.exception.STException;
import com.matrimony.security.HashUtil;
import com.matrimony.util.HibernateUtil;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;

/**
 *
 * @author SON
 */
public class UserDAO {

    public static void add(User a) throws STException.EmailAlready, STException.ContactNumberAlready {
        if (findByEmail(a.getEmail()) != null) {
            throw new STException.EmailAlready("email already");
        } else if (findByContactNumber(a.getContactNumber()) != null) {
            throw new STException.ContactNumberAlready("contact number already");
        } else {
            System.out.println("User not exist");
            a.setSalt(HashUtil.generateSalt(UUID.randomUUID().toString()));
            System.out.println("Salt generated");
            a.setPassword(HashUtil.hashPassword(a.getPassword(), a.getSalt()));
            System.out.println("Password hashed");
            Session ss = HibernateUtil.openSession();
            ss.getTransaction().begin();
            ss.save(a);
            ss.getTransaction().commit();
            ss.close();
            System.out.println("Added user " + a.getEmail());
        }
    }

    public static List<User> allAccounts() {
        Session ss = HibernateUtil.openSession();
        List<User> accounts = ss.createQuery("FROM user").list();
        ss.close();
        return accounts;
    }

    public static User findByUsername(String id) {
        Session ss = HibernateUtil.openSession();
        User account = (User) ss.createQuery("from user where username=?").setString(0, id).uniqueResult();
        ss.close();
        return account;
    }

    public static User findById(String id) {
        Session ss = HibernateUtil.openSession();
        User account = (User) ss.createQuery("from user where userId=?").setString(0, id).uniqueResult();
        ss.close();
        return account;
    }

    public static User findByEmail(String email) {
        Session ss = HibernateUtil.openSession();
        User account = (User) ss.createQuery("from user where email=?").setString(0, email).uniqueResult();
        ss.close();
        return account;
    }

    public static User findByContactNumber(String contactNumber) {
        Session ss = HibernateUtil.openSession();
        User user = (User) ss.createQuery("from user where contactNumber=? and contactNumber!=''").setString(0, contactNumber).uniqueResult();
        ss.close();
        System.out.println(user);
        return user;
    }

    public static void Update(User account) {
        Session ss = HibernateUtil.openSession();
        ss.getTransaction().begin();
        ss.update(account);
        ss.getTransaction().commit();
        ss.close();
    }

    public static User login(String loginName, String password) throws STException.UsernameNotExist, STException.WrongPassword {
        System.out.println("Login name "+loginName);
        User account = findByEmailOrContactNumberOrUsername(loginName);
        if (account == null) {
            throw new STException.UsernameNotExist("username not exists");
        }
        String passwordTemp = HashUtil.hashPassword(password, account.getSalt());
        if (account.getPassword().equals(passwordTemp)) {
            return account;
        } else {
            throw new STException.WrongPassword("Wrong password");
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
       User user=new User();
       user.setPassword("1234");
       user.setEmail("asdf@yahoo.com");
       user.setGender("male");
//        try {
//            add(user);
//            System.out.println("Added");
//        } catch (STException.EmailAlready ex) {
//            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (STException.ContactNumberAlready ex) {
       
//            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
       User aa=findByEmail("taodienmat@yahoo.com");
    }
}
