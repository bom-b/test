package com.shopping.controller.mall;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.controller.SuperClass;
import com.shopping.controller.product.ProductListController;

public class MallDeleteController extends SuperClass {
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		super.doGet(request, response);
		
		if(super.mycart.getCartItemSize() == 0) {
			super.setAlertMessage("카트목록이 존재하지 않아서 상품목록 페이지로 이동합니다.");
			new ProductListController().doGet(request, response);
		} else {
			// pnum : 카트 목록에서 제거하고자하는 상품의 번호
			int pnum = Integer.parseInt(request.getParameter("pnum"));
			super.mycart.DeleteCart(pnum);
			super.session.setAttribute("mycart", mycart);
			new MallListController().doGet(request, response);
		}

	}
}
