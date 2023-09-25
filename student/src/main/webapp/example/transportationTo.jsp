<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ include file="./../common/bootstrap5.jsp" %>
<%@ include file="./../common/common.jsp" %>    

    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	transportationTo.jsp 으로 옵니다.<br/>
	회사 : ${applicationScope.map['company'] }<br/>
	대표 : ${applicationScope.map['ceo'] }<br/>
	이메일 : ${applicationScope.map['email'] }<br/>
	번호 : ${applicationScope.map['phone'] }<br/>
	업로드 경로 : ${applicationScope.map['uploadPath'] }<br/>
	테스트 : ${applicationScope.map[1] }<br/>
	
</body>
</html>