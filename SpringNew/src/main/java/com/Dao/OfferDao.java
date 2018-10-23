package com.Dao;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.Connection.DbConnection;
import com.bean.Offer;
import com.daoFactory.OfferFactory;

public class OfferDao extends OfferFactory{

	Session sess = DbConnection.getSession();
	boolean b = false;
	Transaction trans;
	
	@Override
	public boolean addOffer(Offer offer) {

		try {
			
			trans = sess.beginTransaction();
			sess.save(offer);
			trans.commit();
			b=true;
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		return b;
	}

	@Override
	public ArrayList<Offer> getAllOffers() {
		ArrayList<Offer> list = new ArrayList<Offer>(); 
		try {
			trans = sess.beginTransaction();
			list = (ArrayList<Offer>) sess.createQuery("from Offer order by id desc").getResultList();
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		return list;
	}

	@Override
	public Offer getOffer(Integer id) {

		Offer offer = new Offer();
		try {
			trans = sess.beginTransaction();			
			offer = sess.get(Offer.class,id);
			trans.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return offer;
	}

	@Override
	public boolean updateOffer(Offer offer) {

		try {
			trans = sess.beginTransaction();
			sess.update(offer);
			trans.commit();
			b=true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return b;
	}

	@Override
	public boolean deleteOffer(Offer offer) {

		try {
			trans = sess.beginTransaction();
			sess.delete(offer);
			trans.commit();
			b=true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return b;
	}

	
	
}
