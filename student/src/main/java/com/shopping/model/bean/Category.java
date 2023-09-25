package com.shopping.model.bean;

public class Category {
	private String module;
	private String type;
	private String engname;
	private String korname;
	private Integer ordering;
	
	public Category() {}

	@Override
	public String toString() {
		return "Category [module=" + module + ", type=" + type + ", engname=" + engname + ", korname=" + korname
				+ ", ordering=" + ordering + "]";
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEngname() {
		return engname;
	}

	public void setEngname(String engname) {
		this.engname = engname;
	}

	public String getKorname() {
		return korname;
	}

	public void setKorname(String korname) {
		this.korname = korname;
	}

	public Integer getOrdering() {
		return ordering;
	}

	public void setOrdering(Integer ordering) {
		this.ordering = ordering;
	}
	
	
}


