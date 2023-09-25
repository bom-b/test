package com.shopping.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.model.dao.MemberDao;
import com.shopping.controller.SuperClass;
import com.shopping.model.bean.Member;

// 회원가입을 위한 컨트롤러
public class MemberInsertController extends SuperClass {
	
	private final String PREFIX = "member/";
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		super.gotoPage(PREFIX + "meInsertForm.jsp");
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doPost(request, response);
		
		Member bean = new Member();
		
		bean.setId(request.getParameter("id"));
		bean.setName(request.getParameter("name"));
		bean.setPassword(request.getParameter("password"));
		bean.setGender(request.getParameter("gender"));
		bean.setBirth(request.getParameter("birth"));
		// bean.setBirth(String.valueOf(request.getParameter("birth")));
		bean.setMarriage(request.getParameter("marriage"));
		bean.setSalary(Integer.parseInt(request.getParameter("salary")));
		bean.setAddress(request.getParameter("address"));
		bean.setManager(request.getParameter("manager"));
		
		MemberDao dao = new MemberDao();
		int cnt = -1;
		
		try {
			cnt = dao.InsertData(bean);
			if (cnt == -1) {
				//가입 실패
		
				String message = "회원가입 중 오류가 발생하였습니다.";
				super.setAlertMessage(message);	
				
				new MemberInsertController().doGet(request, response);
				
			} else {	
				//가입 성공

				String message = "성공적으로 회원가입 되었습니다.";
				super.setPostiveAlertMessage(message);		
				
				new MemberLoginController().doPost(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			new MemberInsertController().doGet(request, response);
		}
		
		
		
	}
}
