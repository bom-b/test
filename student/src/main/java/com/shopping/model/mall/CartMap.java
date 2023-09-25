package com.shopping.model.mall;

import java.util.HashMap;
import java.util.Map;

// 이 클래스는 테스트용으로 CartItem 목록을 저장하고 있는 한시적 클래스입니다.
// 차후 데이터베이스를 사용하여 처리할 예정
public class CartMap {

	private Map<Integer, CartItem> itemList = null;
	
	public CartMap() {
		this.itemList = new HashMap<Integer, CartItem>();
		
		// 목록 채워넣기
		itemList.put(1, new CartItem(1, "우유", 10, 100, "사과.png", 10));
		itemList.put(2, new CartItem(2, "채소", 20, 2200, "사과.png", 20));
		itemList.put(3, new CartItem(3, "요거트", 30, 300, "사과.png", 30));
		itemList.put(4, new CartItem(4, "라면", 40, 400, "사과.png", 140));
		itemList.put(5, new CartItem(5, "고기", 50, 500, "사과.png", 10));
		
	}
	
	// 전체목록 반환
	public Map<Integer, CartItem> GetItemList() {
		
		return this.itemList;
	}

}
