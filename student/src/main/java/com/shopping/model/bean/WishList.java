package com.shopping.model.bean;

// 나의 찜 목록 한개를 저장하기 위한 빈 클래스
public class WishList {

	private String id;
	private int pnum;
	private int qty;
	
	public WishList() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "WishList [id=" + id + ", pnum=" + pnum + ", qty=" + qty + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getPnum() {
		return pnum;
	}

	public void setPnum(int pnum) {
		this.pnum = pnum;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}
	
	
}
