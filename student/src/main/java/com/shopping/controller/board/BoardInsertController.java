package com.shopping.controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.controller.SuperClass;
import com.shopping.model.bean.Board;
import com.shopping.model.dao.BoardDao;

public class BoardInsertController extends SuperClass{
	private final String PREFIX = "board/";
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		super.gotoPage(PREFIX + "boInsertForm.jsp");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doPost(request, response);
		
		Board bean = new Board();
		bean.setId(request.getParameter("id"));
		bean.setPassword(request.getParameter("password"));
		bean.setSubject(request.getParameter("title"));
		bean.setContents(request.getParameter("content"));
		bean.setRegdate(request.getParameter("regdate"));
		
		BoardDao dao = new BoardDao();
		int cnt = -1;
		
		try {
			cnt = dao.InsertData(bean);
			
			if(cnt == -1) { 
				//  등록 실패
				System.out.println("게시물 등록 실패");
				new BoardInsertController().doGet(request, response);
			
			} else {
				// 등록 성공
				// 게시물 목록 보기 페이지로 이동	
				String message = "성공적으로 게시글이 등록되었습니다.";
				super.setPostiveAlertMessage(message);	
				
				new BoardListController().doGet(request, response);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	
		
	}
}
