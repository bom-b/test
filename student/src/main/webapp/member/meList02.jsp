<%@page import="com.shopping.model.bean.Member"%>
<%@page import="com.shopping.model.dao.MemberDao"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- %@는 지시어, include는 저 파일을 이 파일에 포함시킨다는 명령 -->    
<%@ include file="./../common/bootstrap5.jsp" %>
<%@ include file="./../common/common.jsp" %>

<%
	MemberDao dao = new MemberDao();
	List<Member> lists = dao.getDataList();
	request.setAttribute("datalist", lists);
	
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style type="text/css">
	</style>
</head>
<body>
	<div class="container">
		<h2>회원 목록</h2>
		<p>회원 목록을 보여주는 페이지 입니다.</p>
		<table class="table table-hover">
			<!-- table-hover, table-striped, table-condensed -->
			<thead>
				<tr>
					<th>아이디</th>
					<th>이름</th>
					<th>비밀번호</th>
					<th>성별</th>
					<th>생일</th>
					<th>결혼 여부</th>
					<th>급여</th>
					<th>주소</th>
					<th>매니저</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="bean" items="${requestScope.datalist }">
				<c:if test="${bean.salary >= 3000}">
						<tr class="table-danger">
					</c:if>
					<c:if test="${bean.salary >= 2000 and bean.salary < 3000}">
						<tr class="table-warning">
					</c:if>
					<c:if test="${bean.salary < 2000}">
						<tr>
					</c:if>
					<td>${bean.id }</td>
					<td>${bean.name }</td>
					<td>${bean.password }</td>
					<td>${bean.gender }</td>
					<td>${bean.birth }</td>
					<td>${bean.marriage }</td>
					<td>${bean.salary }</td>
					<td>${bean.address }</td>
					<td>${bean.manager }</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<!-- 
			ol : order list
			ul : unordered list
			li : list
		 -->
		<ul class="pagination">
			<li class="page-item">
				<a class="page-link" href="#">이전</a>
			</li>
			<li class="page-item">
				<a class="page-link" href="#">1</a>				
			</li>
			<li class="page-item active">
				<a class="page-link" href="#">2</a>				
			</li>
			<li class="page-item">
				<a class="page-link" href="#">3</a>				
			</li>
			<li class="page-item">
				<a class="page-link" href="#">4</a>				
			</li>
			<li class="page-item">
				<a class="page-link" href="#">5</a>				
			</li>
			<li class="page-item">
				<a class="page-link" href="#">다음</a>				
			</li>
		</ul>
	</div>
</body>
</html>