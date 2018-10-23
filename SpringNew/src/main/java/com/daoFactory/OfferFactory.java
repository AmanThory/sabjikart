package com.daoFactory;

import java.util.ArrayList;

import com.Dao.OfferDao;
import com.bean.Offer;

public abstract class OfferFactory {

	public static OfferFactory getDao() {
		
		OfferFactory dao = new OfferDao();
		return dao;
	}
	
	public abstract boolean addOffer(Offer Offer);
	public abstract ArrayList<Offer> getAllOffers();
	public abstract Offer getOffer(Integer id);
	public abstract boolean updateOffer(Offer Offer);
	public abstract boolean deleteOffer(Offer Offer);
}
