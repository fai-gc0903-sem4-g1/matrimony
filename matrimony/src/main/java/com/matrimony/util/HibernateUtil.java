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
	private static SessionFactory sessionFactory;
	
	public static Session getCurrentSession(){
		if (sessionFactory == null) {
			System.out.println("Hibernate configuration loading...");
			Configuration cfg = new Configuration();
			System.out.println("Hibernate configuration loaded...");
			cfg.configure();
			ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
			System.out.println("Hibernate registried");
			System.out.println(sr);
			sessionFactory = cfg.buildSessionFactory(sr);
		}
		return sessionFactory.getCurrentSession();
	}
	
	public static void main(String[] args) {
		HibernateUtil.getCurrentSession();
	}
}
