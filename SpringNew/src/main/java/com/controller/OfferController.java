package com.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bean.Offer;
import com.daoFactory.OfferFactory;

@Controller
public class OfferController {

	
	@RequestMapping(value = "/dashboard/addoffer")
	public ModelAndView addoffer() {

		ModelAndView modelAndView = new ModelAndView("addoffer");
		return modelAndView;
	}
	
	@RequestMapping(value = "/dashboard/alloffer")
	public ModelAndView alloffer() {

		ModelAndView mv = null ;
		OfferFactory dao  = OfferFactory.getDao();
		ArrayList<Offer> offer = dao.getAllOffers();

		if(offer != null) {
		    mv = new ModelAndView("alloffer");
		    mv.addObject("offer", offer);
		}	
		return mv;		
	}
	
	@RequestMapping(value = "/dashboard/editoffer/{id}")
	public ModelAndView editoffer(@PathVariable("id") String id) {
		
        int id2 = Integer.parseInt(id);
		ModelAndView mv = null;
		OfferFactory  dao = OfferFactory.getDao();
		Offer offer = dao.getOffer(id2);
		
		if(offer != null) {
			mv = new ModelAndView("editoffer");
			mv.addObject("offer",offer);
		}else {
			mv = new ModelAndView("alloffer");
		}
		return mv;
	}
	
	@RequestMapping(value = "/dashboard/editoffer" , method=RequestMethod.POST)
	public ModelAndView updateoffer(@ModelAttribute("offer") Offer offer) {
		

		ModelAndView mv = null;
		OfferFactory  dao = OfferFactory.getDao();
		boolean b = dao.updateOffer(offer);
		ArrayList<Offer> prod = dao.getAllOffers();
		if(b == true) {
			mv = new ModelAndView("alloffer");
			mv.addObject("offer",prod);
			mv.addObject("success", "offer is Successfully Added");
		}else {
			mv = new ModelAndView("editoffer");
			mv.addObject("error", "offer is not Added");
		}
		return mv;
	}
	
	@RequestMapping(value="dashboard/deleteoffer/{id}")
	public ModelAndView deleteoffer(@PathVariable("id") String id) {
		
		
		ModelAndView mv = null;
		Offer offer = new Offer();
		offer.setOid(Integer.parseInt(id));
		OfferFactory dao = OfferFactory.getDao();
		boolean b = dao.deleteOffer(offer);
		ArrayList<Offer> prod = dao.getAllOffers();
		
		if(b == true) {
			mv = new ModelAndView("alloffer");
			mv.addObject("success", "offer is deleted");
			mv.addObject("offer", prod);
		}else {
			mv = new ModelAndView("addoffer");
			mv.addObject("error", "offer is not deleted");
		}
		
		return mv;
	}
	
	@RequestMapping(value="/dashboard/addoffer",method=RequestMethod.POST)
	public ModelAndView addoffer(@ModelAttribute("offer") Offer offer) {
		
		ModelAndView mv ;
		OfferFactory  dao = OfferFactory.getDao();
		boolean b = dao.addOffer(offer);
		
		if(b==true) {
	    	mv = new ModelAndView("alloffer");
	    	mv.addObject("Success", "Successfully Addedd");
	    	mv.addObject("offer", dao.getAllOffers());
		}else {
	    	mv = new ModelAndView("addoffer");
	    	mv.addObject("error", "offer is not Added");
		}	
		return mv;
	}
	
	
}
