package com.shopping.model.bean;

public class World {
	private String pnum;
	private String name;
	private String company;
	private String price;
	
	public World() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "World [pnum=" + pnum + ", name=" + name + ", company=" + company + ", price=" + price + "]";
	}

	public String getPnum() {
		return pnum;
	}

	public void setPnum(String pnum) {
		this.pnum = pnum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	
}
