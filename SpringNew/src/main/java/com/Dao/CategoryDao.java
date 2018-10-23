package com.Dao;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.Connection.DbConnection;
import com.bean.Category;
import com.daoFactory.CategoryFactory;

public class CategoryDao extends CategoryFactory{

	Session sess = DbConnection.getSession();
	boolean b = false;
	Transaction trans;
	
	@Override
	public boolean addCategory(Category Category) {

		try {
			
			trans = sess.beginTransaction();
			sess.save(Category);
			trans.commit();
			b=true;
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		return b;
	}

	@Override
	public ArrayList<Category> getAllCategorys() {
		ArrayList<Category> list = new ArrayList<Category>(); 
		try {
			trans = sess.beginTransaction();
			list = (ArrayList<Category>) sess.createQuery("from Category order by id desc").getResultList();
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		return list;
	}

	@Override
	public Category getCategory(Integer id) {

		Category Category = new Category();
		try {
			trans = sess.beginTransaction();			
			Category = sess.get(Category.class,id);
			trans.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return Category;
	}

	@Override
	public boolean updateCategory(Category category) {

		try {
			trans = sess.beginTransaction();
			sess.update(category);
			trans.commit();
			b=true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return b;
	}

	@Override
	public boolean deleteCategory(Category category) {

		try {
			trans = sess.beginTransaction();
			sess.delete(category);
			trans.commit();
			b=true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return b;
	}

	
	
}
