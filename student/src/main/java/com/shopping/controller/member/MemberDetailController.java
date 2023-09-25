package com.shopping.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.model.dao.MemberDao;
import com.shopping.controller.SuperClass;
import com.shopping.model.bean.Member;

public class MemberDetailController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		super.doGet(request, response);
		
		String id = request.getParameter("id");
		MemberDao dao = new MemberDao();
		Member bean = null;
		
		try {
			bean = dao.getDataByPrimarykey(id);
			
			if(bean == null) {
				super.setAlertMessage("잘못된 회원 정보 입니다.");
				super.gotoPage("common/home.jsp");
			} else {
				request.setAttribute("bean", bean);
				super.gotoPage("member/meDetail.jsp");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
