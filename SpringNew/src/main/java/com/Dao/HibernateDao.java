package com.Dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.Connection.DbConnection;
import com.bean.Login;
import com.bean.User;
import com.daoFactory.DaoFactory;

public class HibernateDao extends DaoFactory{

	boolean b = false;
	Transaction trans ;
	Session sess ;
	
	@Override
	public List<User> login(Login login) {

		List<User> result = null;
		try {
			sess = DbConnection.getSession();
			trans = sess.beginTransaction();
			
			System.out.println("this is :"+login.getEmail()+" This is password   "+login.getPassword());
			
			String hql = "FROM User user where user.email= '"+login.getEmail()+"' and user.password='"+login.getPassword()+"'";
			Query<User> query = sess.createQuery(hql);
			result = query.list();
			
			sess.close();

			
		}catch(Exception e) {
			trans.commit();
			e.printStackTrace();
		}
      return result;
	}

}
