package com.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bean.Product;
import com.daoFactory.ProductFactory;



@Controller
public class ProductController {

	
	@RequestMapping(value = "/dashboard/addproduct")
	public ModelAndView addProduct() {

		ModelAndView modelAndView = new ModelAndView("addproduct");
		return modelAndView;
	}
	
	@RequestMapping(value = "/dashboard/allproduct")
	public ModelAndView allProduct() {

		ModelAndView mv = null ;
		ProductFactory dao  = ProductFactory.getDao();
		ArrayList<Product> product = dao.getAllProducts();

		if(product != null) {
		    mv = new ModelAndView("allproduct");
		    mv.addObject("product", product);
		}	
		return mv;		
	}
	
	@RequestMapping(value = "/dashboard/editproduct/{id}")
	public ModelAndView editProduct(@PathVariable("id") String id) {
		
        int id2 = Integer.parseInt(id);
		ModelAndView mv = null;
		ProductFactory  dao = ProductFactory.getDao();
		Product product = dao.getProduct(id2);
		
		if(product != null) {
			mv = new ModelAndView("editproduct");
			mv.addObject("product",product);
		}else {
			mv = new ModelAndView("allproduct");
		}
		return mv;
	}
	
	@RequestMapping(value = "/dashboard/editproduct" , method=RequestMethod.POST)
	public ModelAndView updateProduct(@ModelAttribute("product") Product product) {
		

		ModelAndView mv = null;
		ProductFactory  dao = ProductFactory.getDao();
		boolean b = dao.updateProduct(product);
		ArrayList<Product> prod = dao.getAllProducts();
		if(b == true) {
			mv = new ModelAndView("allproduct");
			mv.addObject("product",prod);
			mv.addObject("success", "Product is Successfully Added");
		}else {
			mv = new ModelAndView("editproduct");
			mv.addObject("error", "Product is not Added");
		}
		return mv;
	}
	
	@RequestMapping(value="dashboard/deleteproduct/{id}")
	public ModelAndView deleteProduct(@PathVariable("id") String id) {
		
		
		ModelAndView mv = null;
		Product product = new Product();
		product.setPid(Integer.parseInt(id));
		ProductFactory dao = ProductFactory.getDao();
		boolean b = dao.deleteProduct(product);
		ArrayList<Product> prod = dao.getAllProducts();
		
		if(b == true) {
			mv = new ModelAndView("allproduct");
			mv.addObject("success", "Product is deleted");
			mv.addObject("product", prod);
		}else {
			mv = new ModelAndView("addproduct");
			mv.addObject("error", "Product is not deleted");
		}
		
		
		return mv;
	}
	
	@RequestMapping(value="/dashboard/addProduct",method=RequestMethod.POST)
	public ModelAndView addProduct(@ModelAttribute("product") Product product,HttpServletRequest request) {
		
		ModelAndView mv ;
		ProductFactory  dao = ProductFactory.getDao();
		boolean b = dao.addProduct(product);
		
		if(b==true) {
	    	mv = new ModelAndView("allproduct");
	    	mv.addObject("Success", "Successfully Addedd");
	    	mv.addObject("product", dao.getAllProducts());
		}else {
	    	mv = new ModelAndView("addproduct");
	    	mv.addObject("error", "Product is not Added");
		}	
		return mv;
	}
	
	
}
