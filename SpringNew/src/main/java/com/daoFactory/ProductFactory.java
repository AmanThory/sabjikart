package com.daoFactory;

import java.util.ArrayList;

import com.Dao.ProductDao;
import com.bean.Product;

public abstract class ProductFactory {

	public static ProductFactory getDao() {
		
		ProductFactory dao = new ProductDao();
		return dao;
	}
	
	public abstract boolean addProduct(Product product);
	public abstract ArrayList<Product> getAllProducts();
	public abstract Product getProduct(Integer id);
	public abstract boolean updateProduct(Product product);
	public abstract boolean deleteProduct(Product product);
}
