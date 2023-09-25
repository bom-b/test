package com.shopping.controller.mall;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.controller.SuperClass;
import com.shopping.controller.product.ProductDetailController;
import com.shopping.controller.product.ProductListController;
import com.shopping.model.dao.MallDao;

public class MallCalculateController extends SuperClass{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		
		MallDao dao = new MallDao();
		
		try {
			dao.Calculate(super.loginfo, super.mycart.GetAllList());
			
			// 결제가 마무리되면 session 영역의 데이터를 지워야한다.
			// 리무브어트리뷰트!
			super.session.removeAttribute("mycart");
			new ProductListController().doGet(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
}
