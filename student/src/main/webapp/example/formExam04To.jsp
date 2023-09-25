<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	request.setCharacterEncoding("UTF-8");
	String comment = request.getParameter("comment");
	
	String[] hobbies = request.getParameterValues("hobby");
	
	String hobby = "";
	
	if(hobbies==null){
		
	}else{
		for(int i=0; i<hobbies.length; i++){
			hobby += hobbies[i] + ",";
		}
		hobby = hobby.substring(0, hobby.length() -1);
	}
	
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	이름 : ${param.name}<br/>
	나이 : ${param.age}<br/>
	이미지 : ${param.image}<br/>
	숨김? : ${param.money}<br/>
	성별 : ${param.gender}<br/>
	취미 : <%=hobby%><br/>
	직업 : ${param.job}<br/>
	코멘트 : <%=comment%>
</body>
</html>