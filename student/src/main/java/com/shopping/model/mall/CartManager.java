package com.shopping.model.mall;

import java.util.HashMap;
import java.util.Map;

// 매장내의 카트를 관리하기 위한 유틸리티 클래스
public class CartManager { 
	
	// carts : 나의 카트에 담겨져 있는 상품들의 번호와 수량 정보
	// 카트의 품목은 <번호, 수량> 형식입니다.
	private Map<Integer, Integer> carts = null;
	
	
	public CartManager() {
		this.carts = new HashMap<Integer, Integer>();
	}


	// 카트에 담겨 있는 품목의 개수를 반환한다.
	public int getCartItemSize() {
		
		return this.carts.size();
	}

	// 카트에 상품을 추가합니다.
	public void AddCart(int pnum, int qty) {
		if(this.carts.containsKey(pnum)) { // 이미 카트에 품목이 있는 경우
			int newQty = this.carts.get(pnum) + qty;
			this.carts.put(pnum, newQty);
		} else {
			this.carts.put(pnum, qty);
		}
	}

	// 카트에 들어있는 상품 개수를 수정합니다. (덮어쓰기 (overwrite))
	public void EditCart(int pnum, int qty) {
		this.carts.put(pnum, qty);
		
	}

	// 해당 상품 목록을 Cart 품목에서 제거합니다.
	public void DeleteCart(int pnum) {
		this.carts.remove(pnum);
		
	}

	// 카트를 비웁니다. (결제시 사용할 예정)
	public void RemoveAllCart() {
		this.carts.clear();
		
	}

	// 카트 내의 모든 품목들을 반환합니다.
	public Map<Integer, Integer> GetAllList() {
		
		
		return this.carts;
	}
	
	
	
}
