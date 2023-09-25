package com.shopping.controller.view;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.controller.SuperClass;
import com.shopping.model.bean.Combo05;
import com.shopping.model.dao.CompositeDao;

public class View05Controller extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		
		CompositeDao dao = new CompositeDao() ;
		
		List<Combo05> lists = null ;
		
		try {
			lists = dao.View05();
			request.setAttribute("lists", lists);
			super.gotoPage("view/view05.jsp"); 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

