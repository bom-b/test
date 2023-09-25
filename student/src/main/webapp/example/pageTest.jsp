<%@page import="com.shopping.utility.Paging"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/common/common.jsp" %>
<%@ include file="/common/bootstrap5.jsp" %>
    
<%
	String _pageNumber = "8" ;
	String _pageSize = "10" ;
	int totalCount = 283 ;
	String url = "boList" ;
	String mode = "" ;
	String keyword = "" ;
	boolean isGrid = false ; //isGrid=true이면 상품 목록보기 형식, false이면 일반 형식(회원, 게시물 목록)으로 보여주기
	
	Paging pageInfo = new Paging(_pageNumber, _pageSize, totalCount, url, mode, keyword, isGrid);
	
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%=pageInfo.toString()%>
	<hr/>
	<%=pageInfo.getPagingHtml()%>
</body>
</html>