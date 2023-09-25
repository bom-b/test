<%@page import="com.shopping.model.bean.Board"%>
<%@page import="com.shopping.model.dao.BoardDao"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/common/common.jsp" %>
<%@ include file="/common/bootstrap5.jsp" %>

<%
	BoardDao dao = new BoardDao();
	List<Board> lists = dao.getDataList();
	request.setAttribute("datalist", lists);

%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>board list</title>
	<style type="text/css">
		.rounded-pill{opacity: 0.6;}
	</style>
</head>
<body>
<div class="container">
		<h2>게시물 목록</h2>
		<p>게시물 목록을 보여주는 페이지 입니다.</p><br/><br/>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>글번호</th>
					<th>작성자</th>
					<th>비밀번호</th>
					<th>글 제목</th>
					<th>글 내용</th>
					<th>작성 일자</th>
					<th>조회수</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="bean" items="${requestScope.datalist }">
				<tr>
					<td>${bean.no }</td>
					<td>${bean.id }</td>
					<td>${bean.password }</td>
					<td>
						<c:forEach var="i" begin="1" end="${bean.depth }">
							<span class="badge rounded-pill bg-secondary">re</span>
						</c:forEach>
						${bean.subject }
					</td>
					<td>${bean.contents }</td>
					<td>${bean.regdate }</td>
					<td>
						<c:if test="${bean.readhit < 100}">
							<span class="badge rounded-pill bg-secondary">${bean.readhit }</span>
						</c:if>
						<c:if test="${bean.readhit >= 100}">
							<span class="badge rounded-pill bg-danger">${bean.readhit }</span>
						</c:if>
					</td>
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