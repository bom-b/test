<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/common/common.jsp" %>
<%@ include file="/common/bootstrap5.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>회원과 게시물</h2>
	<div class="container mt-3">
		<table class="table table-striped">
			<thead>
				<tr>
					<th>이름</th>
					<th>글제목</th>
					<th>글내용</th>
					<th>작성일자</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="bean" items="${requestScope.lists}">
				<tr>
					<td>${bean.name}</td>
					<td>${bean.subject}</td>
					<td>${bean.content}</td>
					<td>${bean.regdate}</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>