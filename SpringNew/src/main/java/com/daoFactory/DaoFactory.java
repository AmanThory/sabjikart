package com.daoFactory;

import java.util.List;

import com.Dao.HibernateDao;
import com.bean.Login;
import com.bean.User;

public abstract class DaoFactory {

	public static DaoFactory getDao() {

		DaoFactory dao = new HibernateDao();
		return dao;
	}

	public abstract List<User> login(Login login);
}
