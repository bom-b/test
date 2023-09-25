package com.shopping.controller.view;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.controller.SuperClass;
import com.shopping.model.bean.Combo04;
import com.shopping.model.dao.CompositeDao;

public class View04Controller extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		super.doGet(request, response);
		
		CompositeDao dao = new CompositeDao();
		List<Combo04> lists = null;
		
		try {
			lists = dao.view04();
			request.setAttribute("lists", lists);
			super.gotoPage("view/view04.jsp");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
