package com.shopping.controller.mall;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.controller.SuperClass;
import com.shopping.controller.product.ProductListController;
import com.shopping.model.dao.ProductDao;
import com.shopping.model.mall.CartItem;

public class MallListController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		
		if(super.loginfo == null) {
			super.neededLogin();
			return;
		}
		
		int cartItemSize = super.mycart.getCartItemSize();
		if (cartItemSize == 0) {
			super.setAlertMessage("카트 목록이 없어서 상품 목록 페이지로 이동합니다.");
			new ProductListController().doGet(request, response);
		} else {
			// cartItemList는 나의 카트 안 품목 리스트
			Map<Integer, Integer> cartItemList =  super.mycart.GetAllList();
			
			try {
				
				ProductDao dao = new ProductDao();
				
				// cartWishList는 고객이 카트에 담은 품목들의 세부 정보를 저장하고 있는 컬렉션
				List<CartItem> cartWishList = new ArrayList<CartItem>();
				
				// keySet은 상품 번호 set
				for(Integer pnum : cartItemList.keySet()) {
					Integer qty = cartItemList.get(pnum); // 구매수량
					CartItem item = dao.GetCartItem(pnum,qty);
					
					cartWishList.add(item);
				}
				
				// session영역에 cartWishList를 바인딩
				super.session.setAttribute("cartWishList", cartWishList);
				super.gotoPage("mall/maList.jsp");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//System.out.println("카트품목개수 : " + super.mycart.getCartItemSize());
		Map<Integer, Integer> cartItems =  super.mycart.GetAllList();
		for(Integer key : cartItems.keySet()) {
			Integer value = cartItems.get(key);
			System.out.println("상품 번호 : " + key + "구매수량 : " + value);
		}
	}
}
