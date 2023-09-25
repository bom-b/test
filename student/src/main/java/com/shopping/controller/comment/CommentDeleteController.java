package com.shopping.controller.comment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.controller.SuperClass;
import com.shopping.controller.board.BoardDetailController;
import com.shopping.model.dao.CommentDao;

public class CommentDeleteController extends SuperClass {
	@Override
	// 지정한 댓글 번호를 사용하여 나의 댓글 번호를 삭제합니다.
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		super.doGet(request, response);
		
		int cnum = Integer.parseInt(request.getParameter("cnum")) ;
		CommentDao dao = new CommentDao() ;
		int cnt = -1;
		
		try {
			cnt = dao.DeleteData(cnum) ;
			
			new BoardDetailController().doGet(request, response); 
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
