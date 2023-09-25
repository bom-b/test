package com.shopping.controller.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.controller.SuperClass;
import com.shopping.model.dao.ProductDao;

public class ProductDeleteController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		super.doGet(request, response);
		
		int pnum = Integer.parseInt(request.getParameter("pnum"));
		
		ProductDao dao = new ProductDao();
		int cnt = -1;
		
		try {
			cnt = dao.DeleteDate(pnum);
			new ProductListController().doGet(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		
		
	}
}
