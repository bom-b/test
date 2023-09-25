package com.shopping.controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shopping.controller.SuperClass;
import com.shopping.model.bean.Board;
import com.shopping.model.bean.Member;
import com.shopping.model.dao.BoardDao;

public class BoardUpdateController extends SuperClass{
	private final String PREFIX = "board/";
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		
		// 게시글 수정시 넘어 오는 게시글 번호를 챙긴다.
		Integer no = Integer.parseInt(request.getParameter("no"));
		
		// 다오에서 no에 해당하는 데이터를 가져오고 그 가져온 데이터를 빈으로 만든다.
		BoardDao dao = new BoardDao();
		Board bean = dao.GetDataByPK(no);
		
		
		try {

			request.setAttribute("bean", bean);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		super.gotoPage(PREFIX + "boUpdateForm.jsp");
		
		
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doPost(request, response);
		
		Board bean = new Board();
		bean.setNo(Integer.parseInt(request.getParameter("no")));
		bean.setId(request.getParameter("id"));
		bean.setPassword(request.getParameter("password"));
		bean.setSubject(request.getParameter("title"));
		bean.setContents(request.getParameter("content"));
		bean.setRegdate(request.getParameter("regdate"));
		bean.setGroupno(Integer.parseInt(request.getParameter("groupno")));
		bean.setOrderno(Integer.parseInt(request.getParameter("orderno")));
		bean.setDepth(Integer.parseInt(request.getParameter("depth")));
		
		System.out.println("업데이트 폼에서 넘어온 빈 : " + bean);
		
		BoardDao dao = new BoardDao();
		
		int cnt = -1;
		
		try {
			
			cnt = dao.updateData(bean);
			
			if(cnt == -1) {
				// 실패
				new BoardUpdateController().doGet(request, response);
				
			} else {
				// 성공
				
				
				// 하면 안되는 코딩
				// new BoardListController().doGet(request, response);
				
				// 현재 진행 중인 페이지로 이동하기 위하여 페이징관련 파라미터도 넘겨주어야한다.
				String gotopage = super.getUrlInfomation("boList");
				gotopage += "&pageNumber=" + request.getParameter("pageNumber");
				gotopage += "&pageSize=" + request.getParameter("pageSize");
				gotopage += "&mode=" + request.getParameter("mode");
				gotopage += "&keyword=" + request.getParameter("keyword");
				
				System.out.println("[ flowParameter ] :" + gotopage);
				
				response.sendRedirect(gotopage);
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	
		
	}
}
