<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>개별변수 바인딩</h3>
		상품번호: ${requestScope.pnum}<br/>
		이름: ${requestScope.name}<br/>
		제조사: ${requestScope.company}<br/>
		가격: ${requestScope.price}<br/>
	<hr/>
	<h3>bean 객체 바인딩</h3>
		상품번호: ${requestScope.bean.pnum}<br/>
		이름: ${requestScope.bean.name}<br/>
		제조사: ${requestScope.bean.company}<br/>
		가격: ${requestScope.bean.price}<br/>
	<hr/>
	<h3>세션 영역 바인딩</h3>
		회사: ${sessionScope.tel}<br/>
		주소: ${sessionScope.fax}<br/>
	<hr/>
	<h3>application 영역 바인딩</h3>
		인사말: ${applicationScope.hello}<br/>
	<hr/>
</body>
</html>