package com.shopping.model.mall;

// 카트에 담겨져 있는 상품 한개에 대한 세부 정보를 저장하는 빈 클래스
public class CartItem {
	
	private String id; // 고객아이디
	private int pnum; // 상품번호
	private String pname; // 상품명
	private int qty; // 구매수량
	private int price; // 단가
	private String image01; // 대표이미지
	private int point; // 적립포인트
	
	public CartItem() {}

	public CartItem(int pnum, String pnme, int qty, int price, String image01, int point) {
		this.pnum = pnum;
		this.pname = pnme;
		this.qty = qty;
		this.price = price;
		this.image01 = image01;
		this.point = point;
	}

	@Override
	public String toString() {
		return "CartItem [pnum=" + pnum + ", pnme=" + pname + ", qty=" + qty + ", price=" + price + ", image01="
				+ image01 + ", point=" + point + "]";
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

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
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

	public String getImage01() {
		return image01;
	}

	public void setImage01(String image01) {
		this.image01 = image01;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}
	
	
	
	
	
}
