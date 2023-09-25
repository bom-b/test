package com.shopping.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.controller.SuperClass;
import com.shopping.model.dao.MemberDao;

public class MemberDeleteController extends SuperClass{

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		super.doGet(request, response);
		
		String id = request.getParameter("id");
		
		MemberDao dao = new MemberDao();
		
		int cnt = -1;
		
		try {
			cnt = dao.deleteData(id);
			super.session.invalidate(); // 세션에 있는 정보도 지워야함
			
			new MemberInsertController().doGet(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
