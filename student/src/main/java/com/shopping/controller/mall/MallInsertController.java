package com.shopping.controller.mall;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.controller.SuperClass;
import com.shopping.controller.product.ProductDetailController;
import com.shopping.controller.product.ProductListController;

// 상품 상세보기 페이지에서 [장바구니] 버튼을 클릭했습니다.
public class MallInsertController extends SuperClass{

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {

		super.doPost(request, response);
		
		// 미로그인시 로그인필요 메소드 호출
		if(super.loginfo == null) {
			super.neededLogin();
			return;
		} 
		
		int stock = Integer.parseInt(request.getParameter("stock")); // 재고 수량
		int qty = Integer.parseInt(request.getParameter("qty"));// 구매할  수량
		
		if(stock < qty) { //재고 부족시
			String message = "재고가 부족합니다.";
			this.setAlertMessage(message);
			new ProductDetailController().doGet(request, response);
			
		} else { //재고 있을시
			int pnum = Integer.parseInt(request.getParameter("pnum")); // 상품번호
			super.mycart.AddCart(pnum, qty);
			super.session.setAttribute("mycart", mycart);
			
			new MallListController().doGet(request, response);
		}
		
		
		
		
	}
	
	
}
