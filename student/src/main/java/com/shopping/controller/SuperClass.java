package com.shopping.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shopping.model.bean.Member;
import com.shopping.model.mall.CartManager;

// 하위 컨트롤러 들이 공통적으로 사용하는 기능들을 여기에 명시합니다.
public class SuperClass implements SuperController {
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	protected HttpSession session;
	
	protected Member loginfo = null; // 로그인 여부를 파악하는 변수
	protected CartManager mycart = null; // 나의 카트
	
	// 로그인안했을시 로그인 페이지로 이동
	public void neededLogin() {
		
		// 미로그인시 로그인 페이지로 이동시킵니다.
		String message = "로그인이 필요한 서비스입니다.";
		this.setAlertMessage(message);
		this.gotoPage("member/meLoginForm.jsp");
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		this.request = request;
		this.response = response;
		this.session = request.getSession();
		
		this.loginfo = (Member)session.getAttribute("loginfo");
		this.mycart = (CartManager)session.getAttribute("mycart");
		
		// 처음엔 카트가 없기때문에 카트를 하나 생성해준다.
		if(mycart == null) {
			mycart = new CartManager();
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		this.request = request;
		this.response = response;
		this.session = request.getSession();	
		
		this.loginfo = (Member)session.getAttribute("loginfo");
		this.mycart = (CartManager)session.getAttribute("mycart");
		
		// 처음엔 카트가 없기때문에 카트를 하나 생성해준다.
		if(mycart == null) {
			mycart = new CartManager();
		}
	}

	public void gotoPage(String gotoPage) {
		
		if(this.request == null) {
			System.out.println("doGet/doPost() 메소드 호출 누락");
			return;
		}
		
		// 요청한 페이지로 포워딩합니다.
		RequestDispatcher dispatcher = null;
		try {
			dispatcher = request.getRequestDispatcher(gotoPage);
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void setAlertMessage(String message) {
		// sesssion 영역에서 "alertMessage"라는 이름으로 사용자에게 주의/오류/경고 문구 등을 띄워줍니다.
		// in common.jsp 파일 하단 참조
		session.setAttribute("alertMessage", message);
	}
	
	public void setPostiveAlertMessage(String message) {
		// 긍정적인 알림
		session.setAttribute("postiveAlertMessage", message);
	}

	// 숫자형 파라미터에 대한 처리를 해주는 메소드
	// 잘못된 숫자 형식이면 0을 반환해준다.
	public Integer getNumberData(String parameter) {
		boolean flag = false; 
		
		// 파라미터에 이상한 데이터가 들어올 경우 flag를 true로 전환
		flag = parameter == null || parameter.equals("") || parameter.equals("null");
		
		// 플래그가 트루면 0 리턴
		return flag ? 0 : Integer.parseInt(parameter);
	}

	public String getUrlInfomation(String todoCommand) {
		// todoCommmand : todolist.txt 파일에 명시에 커맨드 이름
		// command 이름을 사용하여 FullName 웹 주소 형식으로 반환해 줍니다.
		String appName = this.request.getContextPath() ;
		String mappingName = "/Shopping" ;
		
		String text = appName + mappingName + "?command=" + todoCommand ;
		return text ;
	}

}
