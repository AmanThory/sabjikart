package com.daoFactory;

import java.util.ArrayList;

import com.Dao.CategoryDao;
import com.bean.Category;

public abstract class CategoryFactory {

	public static CategoryFactory getDao() {
		
		CategoryFactory dao = new CategoryDao();
		return dao;
	}
	
	public abstract boolean addCategory(Category category);
	public abstract ArrayList<Category> getAllCategorys();
	public abstract Category getCategory(Integer id);
	public abstract boolean updateCategory(Category category);
	public abstract boolean deleteCategory(Category category);
}
