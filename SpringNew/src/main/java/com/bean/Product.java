package com.bean;

import java.beans.Transient;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="product")
public class Product {

	@Id
	@GeneratedValue
	private int pid;
	
	@Column(name="pname")
	@NotBlank(message = "Please enter the Product name!")
	private String pname;
	
	@Column(name="pprice")
	@NotBlank(message = "Please enter the Price!")
	private String pprice;
	
	@Column(name="pcategory")
	@NotBlank(message = "Please enter the Categoy!")
	private String pcategory;
	
	@Column(name="pimage")
	@NotBlank(message = "Please enter the Product image!")
	private String pimage;
	
	@Column(name="poffer")
	@NotBlank(message = "Please enter the Product offer!")
	private String poffer;
	
	@Column(name="pquality")
	@NotBlank(message = "Please enter the Product quality!")
	private String pquality;
	
	@Column(name="pstock")
	@NotBlank(message = "Please enter the How Much Product in Stock!")
	private String pstock;
	
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getPprice() {
		return pprice;
	}
	public void setPprice(String pprice) {
		this.pprice = pprice;
	}
	public String getPcategory() {
		return pcategory;
	}
	public void setPcategory(String pcategory) {
		this.pcategory = pcategory;
	}
	public String getPimage() {
		return pimage;
	}
	public void setPimage(String pimage) {
		this.pimage = pimage;
	}
	public String getPoffer() {
		return poffer;
	}
	public void setPoffer(String poffer) {
		this.poffer = poffer;
	}
	public String getPquality() {
		return pquality;
	}
	public void setPquality(String pquality) {
		this.pquality = pquality;
	}
	public String getPstock() {
		return pstock;
	}
	public void setPstock(String pstock) {
		this.pstock = pstock;
	}
}
