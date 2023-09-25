package com.shopping.controller.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.model.dao.MemberDao;
import com.shopping.utility.Paging;
import com.shopping.controller.SuperClass;
import com.shopping.model.bean.Member;

public class MemberListController extends SuperClass{
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		super.doGet(request, response);
		
		// 페이징 처리를 위한 파라미터들
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		String mode = request.getParameter("mode");
		String keyword = request.getParameter("keyword");
		
		MemberDao dao = new MemberDao();
		
		try {
			int totalCount = dao.GetTotalRecordCount();
			String url = super.getUrlInfomation("meList") ;
			boolean isGrid = false ;
			Paging pageInfo = new Paging(pageNumber, pageSize, totalCount, url, mode, keyword, isGrid);
			
			// 게시물들을 담는 리스트
			List<Member> lists = dao.selectAll(pageInfo);
			
			// 게시물을 담은 리스트를 "datalist" 리퀘스트 저장소에 할당한다. (바인딩)
			request.setAttribute("datalist", lists);
			
			// 페이징 정보를 바인딩
			request.setAttribute("pageInfo", pageInfo);
			
			// 바인딩한 것을 페이지로 보냅니다.
			super.gotoPage("member/meList.jsp");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		super.doPost(request, response);
	}
	
}
