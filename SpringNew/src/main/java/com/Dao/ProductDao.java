package com.Dao;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.Connection.DbConnection;
import com.bean.Product;
import com.daoFactory.ProductFactory;

public class ProductDao extends ProductFactory{

	Session sess = DbConnection.getSession();
	boolean b = false;
	Transaction trans;
	
	@Override
	public boolean addProduct(Product product) {

		try {
			
			trans = sess.beginTransaction();
			sess.save(product);
			trans.commit();
			b=true;
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		return b;
	}

	@Override
	public ArrayList<Product> getAllProducts() {
		ArrayList<Product> list = new ArrayList<Product>(); 
		try {
			trans = sess.beginTransaction();
			list = (ArrayList<Product>) sess.createQuery("from Product order by id desc").getResultList();
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		return list;
	}

	@Override
	public Product getProduct(Integer id) {

		Product product = new Product();
		try {
			trans = sess.beginTransaction();			
			product = sess.get(Product.class,id);
			trans.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return product;
	}

	@Override
	public boolean updateProduct(Product product) {

		try {
			trans = sess.beginTransaction();
			sess.update(product);
			trans.commit();
			b=true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return b;
	}

	@Override
	public boolean deleteProduct(Product product) {

		try {
			trans = sess.beginTransaction();
			sess.delete(product);
			trans.commit();
			b=true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return b;
	}

	
	
}
