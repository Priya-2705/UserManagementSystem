package com.priya.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.priya.model.User;


public class HibernateUtil {
	
	static SessionFactory sessionFactory = null;
	
	public static SessionFactory buildSessionFactory() {
		
		if(sessionFactory != null) {
			return sessionFactory;
		}
		
		Configuration cfg = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class);
		sessionFactory = cfg.buildSessionFactory();
		return sessionFactory;
	}
}
