package com.Connection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DbConnection {
	
	public static Session getSession() {
		
		Session sess = null;
		try {
			Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
			SessionFactory sf = cfg.buildSessionFactory();
			sess = sf.openSession();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return sess;
	}

}
