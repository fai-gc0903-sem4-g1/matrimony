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
	public static Session session=getCurrentSession();
	private static SessionFactory sessionFactory;
	
	protected static Session getCurrentSession(){
		if (cfg == null) {
			System.out.println("Configing..");
			cfg = new Configuration();
			cfg.configure();
			sr = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
			sf = cfg.buildSessionFactory(sr);
			session=sf.openSession();
		}
			return session;
	}
	
	 
    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            System.out.println("Hibernate Configuration loaded");
             
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            System.out.println("Hibernate serviceRegistry created");
             
            SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
             
            return sessionFactory;
        }
        catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }
    }
     
    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null) sessionFactory = buildSessionFactory();
        return sessionFactory;
    }

	public static void main(String[] args) {
		SessionFactory sf=HibernateUtil.getSessionFactory();
		session=sf.getCurrentSession();
		System.out.println("current session: "+session);
	}
}
