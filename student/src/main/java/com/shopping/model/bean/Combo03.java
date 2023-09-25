package com.shopping.model.bean;

public class Combo03 {
	
	private String mname;
	private String pname;
	private String orderdate;
	private int qty;
	private int price;
	private int amount;
	
	public Combo03() {
		
	}

	@Override
	public String toString() {
		return "Combo03 [mname=" + mname + ", pname=" + pname + ", orderdate=" + orderdate + ", qty=" + qty + ", price="
				+ price + ", amount=" + amount + "]";
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}


	
}
