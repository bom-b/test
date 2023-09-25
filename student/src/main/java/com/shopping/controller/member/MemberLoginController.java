package com.shopping.controller.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.model.dao.MallDao;
import com.shopping.model.dao.MemberDao;
import com.shopping.controller.HomeController;
import com.shopping.controller.SuperClass;
import com.shopping.controller.product.ProductListController;
import com.shopping.model.bean.Member;
import com.shopping.model.bean.WishList;

public class MemberLoginController extends SuperClass{
	
	private final String PREFIX = "member/";
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		super.gotoPage(PREFIX + "meLoginForm.jsp");
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		super.doPost(request, response);
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		System.out.println(id + "/" + password);
		
		MemberDao dao = new  MemberDao();
		Member bean = null;
		
		try {
			bean = dao.getDataByPK(id, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(bean == null) { // 로그인 실패
			
			String message = "로그인 정보가 잘못되었습니다.";
			super.setAlertMessage(message);
			
			super.gotoPage(PREFIX + "meLoginForm.jsp");
			
		} else { // 로그인 성공
			// session 영역에 나의 로그인 정보를 저장합니다.
			super.session.setAttribute("loginfo", bean);
			
			
			MallDao mdao = new MallDao();
			// 나의 wishlist를 테이블에서 읽어와서 session 영역에 바인딩합니다.
			try {
				List<WishList> wishList = mdao.getWishList(bean.getId());
				
				for (WishList item : wishList) {
					super.mycart.AddCart(item.getPnum(), item.getQty());
					
				}
				
				super.session.setAttribute("mycart", mycart);
				
				new ProductListController().doGet(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	
}
