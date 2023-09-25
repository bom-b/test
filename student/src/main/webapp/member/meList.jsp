<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/common/common.jsp" %>
<%@ include file="/common/bootstrap5.jsp" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">

#tableMain{
background-color: #ebebeb;
}

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
			        <th id="tableMain">아이디</th>
			        <th id="tableMain">이름</th>
			        <th id="tableMain">비밀번호</th>
			        <th id="tableMain">성별</th>
			        <th id="tableMain">생일</th>
			        <th id="tableMain">결혼 여부</th>
			        <th id="tableMain">급여</th>
			        <th id="tableMain">주소</th>
			        <th id="tableMain">매니저</th>
			    </tr>
			</thead>
			<tbody>
				<tr><td colspan="9" align="right">${requestScope.pageInfo.pagingStatus}</td></tr>
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
					<td>
						<a href="<%=notWithFormTag%>meDetail&id=${bean.id}">${bean.name}</a>
					</td>
					<td>${bean.password }</td>
					<c:if test="${bean.gender eq 'male'}">
						<td>남자
					</c:if>
					<c:if test="${bean.gender eq 'female'}">
						<td>여자</td>
					</c:if>			
					<td>${bean.birth }</td>
					<td>${bean.marriage }</td>
					<td>${bean.salary }</td>
					<td>${bean.address }</td>
					<td>${bean.manager }</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		${requestScope.pageInfo.pagingHtml} 
	</div>
</body>
</html>