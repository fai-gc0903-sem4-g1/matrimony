/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrimony.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author SON
 */
public class HibernateUtil {

    protected static ServiceRegistry sr;
    protected static SessionFactory sf;
    protected static Configuration cfg;
    
    public static Session openSession() {
        if (cfg == null) {
            System.out.println("Configing..");
            cfg = new Configuration();
            cfg.configure();
            sr = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
            sf = cfg.buildSessionFactory(sr);
        }
        return sf.openSession();
    }

    public static void main(String[] args) {
    }
}
