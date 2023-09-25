package com.shopping.controller.member;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.controller.HomeController;
import com.shopping.controller.SuperClass;
import com.shopping.model.dao.MallDao;

public class MemberLogoutController extends SuperClass {
	
	@Override // 회원이 로그아웃을 시도합니다.
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		
		MallDao dao = new MallDao();
		Map<Integer, Integer> whishList = super.mycart.GetAllList();
		try {
			// session 영역에 남겨진 장바구니가 있으면 임시 테이블 wishlist에 추가해줍니다.
			if (super.loginfo != null) {
				if (whishList.size() > 0) {
					dao.insertWishList(super.loginfo.getId(), whishList);
				}
			} else {
				super.neededLogin();
				return;
			}
			
			// 로그인시 저장했던 정보 등을 깨끗이 비웁니다.
			super.session.invalidate();
			
			super.gotoPage("member/meLoginForm.jsp");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	
}
