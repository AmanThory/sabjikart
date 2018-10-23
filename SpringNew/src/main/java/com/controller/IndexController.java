package com.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bean.Login;
import com.bean.User;
import com.daoFactory.DaoFactory;

@Controller
public class IndexController {

	@RequestMapping(value = "/")
	public ModelAndView start() {

		ModelAndView modelAndView = new ModelAndView("login");
		return modelAndView;
	}
	
	@RequestMapping(value = "/dashboard", method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("login") Login login) {
		
		ModelAndView mv = null;
		DaoFactory dao = DaoFactory.getDao();
		List<User> user = dao.login(login);
		
		if(user!=null) {
			mv = new ModelAndView("index");
	        mv.addObject("login", login);
		}
		else {
			mv = new ModelAndView("login");
	        mv.addObject("login", login);
		}
	
        
		return mv;
	}
}
