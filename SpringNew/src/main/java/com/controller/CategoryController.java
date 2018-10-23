package com.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bean.Category;
import com.daoFactory.CategoryFactory;

@Controller
public class CategoryController {

	private static final String UPLOAD_DIRECTORY ="temp";
	
	@Autowired
	ServletContext context;
	
	@RequestMapping(value = "/dashboard/addcategory")
	public ModelAndView addCategory() {

		ModelAndView modelAndView = new ModelAndView("addcategory");
		return modelAndView;
	}
	
	@RequestMapping(value = "/dashboard/allcategory")
	public ModelAndView allCategory() {

		ModelAndView mv = null ;
		CategoryFactory dao  = CategoryFactory.getDao();
		ArrayList<Category> category = dao.getAllCategorys();

		if(category != null) {
		    mv = new ModelAndView("allcategory");
		    mv.addObject("category", category);
		}	
		return mv;		
	}
	
	@RequestMapping(value = "/dashboard/editcategory/{id}")
	public ModelAndView editCategory(@PathVariable("id") String id) {
		
        int id2 = Integer.parseInt(id);
		ModelAndView mv = null;
		CategoryFactory  dao = CategoryFactory.getDao();
		Category category = dao.getCategory(id2);
		
		if(category != null) {
			mv = new ModelAndView("editcategory");
			mv.addObject("category",category);
		}else {
			mv = new ModelAndView("allcategory");
		}
		return mv;
	}
	
	@RequestMapping(value = "/dashboard/editcategory" , method=RequestMethod.POST)
	public ModelAndView updatecategory(@ModelAttribute("category") Category category) {
		

		ModelAndView mv = null;
		CategoryFactory  dao = CategoryFactory.getDao();
		boolean b = dao.updateCategory(category);
		ArrayList<Category> prod = dao.getAllCategorys();
		if(b == true) {
			mv = new ModelAndView("allcategory");
			mv.addObject("category",prod);
			mv.addObject("success", "Category is Successfully Added");
		}else {
			mv = new ModelAndView("editcategory");
			mv.addObject("error", "cCategory is not Added");
		}
		return mv;
	}
	
	@RequestMapping(value="dashboard/deletecategory/{id}")
	public ModelAndView deletecategory(@PathVariable("id") String id) {
		
		
		ModelAndView mv = null;
		Category category = new Category();
	//	category.setCid(Integer.parseInt(id));
		CategoryFactory dao = CategoryFactory.getDao();
		boolean b = dao.deleteCategory(category);
		ArrayList<Category> prod = dao.getAllCategorys();
		
		if(b == true) {
			mv = new ModelAndView("allcategory");
			mv.addObject("success", "category is deleted");
			mv.addObject("category", prod);
		}else {
			mv = new ModelAndView("addcategory");
			mv.addObject("error", "category is not deleted");
		}
		
		return mv;
	}
	
	@RequestMapping(value="/dashboard/addCategory",method=RequestMethod.POST)
	public ModelAndView addcategory(@RequestParam("file") MultipartFile file) throws IOException  {
		

		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				String rootPath = context.getRealPath("");
				File dir = new File(rootPath + "/assets"+File.separator + "tmpFiles");
				
				if (!dir.exists())
					dir.mkdirs();

				String name = file.getOriginalFilename();
				System.out.println("file name -- "+ name);
				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath()
						+ File.separator + name);
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				System.out.println("successfully Done!");

			} catch (Exception e) {
				e.printStackTrace();
			}
		
		
	   
		
	}
		ModelAndView mv = new ModelAndView("allcategory");
		return mv;
		
	}
	
}
