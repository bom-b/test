package com.shopping.controller.mall;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.controller.SuperClass;
import com.shopping.controller.product.ProductListController;

public class MallUpdateController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("여기로 넘어옴?");
		super.doGet(request, response);
		
		if(super.mycart.getCartItemSize() == 0) {
			super.setAlertMessage("카트목록이 존재하지 않아서 상품목록 페이지로 이동합니다.");
			new ProductListController().doGet(request, response);
			
		} else {
			
			int pnum = Integer.parseInt(request.getParameter("pnum"));
			int qty = Integer.parseInt(request.getParameter("qty"));
			
			super.mycart.EditCart(pnum, qty);
			super.session.setAttribute("mycart", mycart);
			new MallListController().doGet(request, response);
		}
		
		
	}
}
