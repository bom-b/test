package com.shopping.controller.comment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.controller.SuperClass;
import com.shopping.controller.board.BoardDetailController;
import com.shopping.model.bean.Comment;
import com.shopping.model.dao.CommentDao;

public class CommentInsertController extends SuperClass {
	
	@Override // 부모 글번호에 대하여 로그인한 사람이 댓글을 작성하는 로직입니다.
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		super.doPost(request, response);
		
		Comment bean = new Comment();
		bean.setNo(Integer.parseInt(request.getParameter("no"))); // 부모(게시물) 글 번호
		bean.setContent(request.getParameter("content")); // 내가 작성한 댓글내용
		bean.setId(request.getParameter("id")); // 나의 아이디
		
		CommentDao dao = new CommentDao();
		int cnt = -1;
		
		try {
			// 다오를 통해 빈의 정보를 데이터베이스로 넘긴다.
			cnt = dao.InsertData(bean);
			
			new BoardDetailController().doGet(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
