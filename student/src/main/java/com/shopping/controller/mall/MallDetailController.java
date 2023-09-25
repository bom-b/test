package com.shopping.controller.mall;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.controller.SuperClass;
import com.shopping.model.bean.Order;
import com.shopping.model.dao.MallDao;
import com.shopping.model.mall.CartItem;

public class MallDetailController extends SuperClass{

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		super.doGet(request, response);
		
		if(super.loginfo == null) {
			super.neededLogin();
			return;
		}
		
		MallDao dao = new MallDao();
		
		try {
			int oid = Integer.parseInt(request.getParameter("oid"));
			Order order = dao.getDetailHistory(oid);
			
			List<CartItem> lists = dao.showDetail(oid);
			System.out.println(lists);
		
			request.setAttribute("order", order); // 주문 정보
			request.setAttribute("lists", lists); // 쇼핑 정보
			
			super.gotoPage("mall/maDetail.jsp");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
