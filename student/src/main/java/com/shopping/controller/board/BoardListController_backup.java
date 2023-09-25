package com.shopping.controller.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.controller.SuperClass;
import com.shopping.model.bean.Board;
import com.shopping.model.dao.BoardDao;

public class BoardListController_backup extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		
		BoardDao dao = new BoardDao();
		
		try {
			// 게시물들을 담는 리스트
			List<Board> lists = dao.selectAll();
			
			// 게시물을 담은 리스트를 "datalist" 리퀘스트 저장소에 할당한다. (바인딩)
			request.setAttribute("datalist", lists);
			// 바인딩한 것을 페이지로 보냅니다.
			super.gotoPage("board/boList.jsp");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
}
