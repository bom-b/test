<%@page import="com.shopping.model.bean.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="./../common/bootstrap5.jsp" %>
<%@ include file="./../common/common.jsp" %>
<%
	Map<String, String> memberList = new HashMap<String, String>();
	memberList.put("admin", "1234"); //관리자
	memberList.put("hong", "hong"); //사용자 홍길동
%>
<%
	request.setCharacterEncoding("UTF-8");
	String id = request.getParameter("id");
	String password = request.getParameter("password");
	
	boolean isLogin = false; //false이면 로그인 실패(로그인 실패가 기본 값)
	
	boolean isIdExist = memberList.containsKey(id); //id 존재 여부
	
	if(isIdExist){ //해당 아이디가 존재하는 경우
		String dbpassword = memberList.get(id);
		if(password.equals(dbpassword)) {
			isLogin = true;
		} else { //비밀번호 불일치
			
		}
	}else{ //존재하지 않는 경우
		
	}
	
	// 로그인 성공, 실패 분기문
	String message = "";
	if(isLogin){
		Member mem = new Member();
		mem.setId(id);
		mem.setPassword(password);
		
		// 세션에 저장할 나의 로그인 정보 이름은 loginfo라고 하겠다
		session.setAttribute("loginfo", mem);
		message = "로그인 성공(" + id + ")"; 
		
		// sendRedirect(): 리다이렉션 방식으로 페이지 전환
		response.sendRedirect("./../product/prList02.jsp");
	}else{
		message = "로그인 실패";
		session.setAttribute("alertmessage", "아이디 또는 비밀번호를 잘못 입력하셨습니다.");
		response.sendRedirect("meLoginForm02.jsp");
	}
	out.print(message);
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>