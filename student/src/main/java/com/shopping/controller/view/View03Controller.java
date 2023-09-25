package com.shopping.controller.view;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.controller.SuperClass;
import com.shopping.model.bean.Combo03;
import com.shopping.model.dao.CompositeDao;

public class View03Controller extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		super.doGet(request, response);
		
		CompositeDao dao = new CompositeDao();
		List<Combo03> lists = null;
		
		try {
			lists = dao.view03();
			request.setAttribute("lists", lists);
			super.gotoPage("view/view03.jsp");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
