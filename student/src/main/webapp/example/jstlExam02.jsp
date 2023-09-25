<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./../common/bootstrap5.jsp" %>
<%@ include file="./../common/common.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<!-- 1부터 100까지의 홀수의 총합을 구해 봅니다. -->
	<c:set var="total" value="0"></c:set>
	<c:forEach var="i" begin="1" end="100" step="2">
		<c:set var="total" value="${total + i }"></c:set>
	</c:forEach>
	홀수의 총합 : <c:out value="${total }"></c:out><br/>
	
	<!-- 시험 점수(jumsu)가 82점일때, 학점을 구해 보세요. -->
	<!-- If 구문을 사용한 방식 -->
	<c:set var="jumsu" value="82"></c:set>
	<c:set var="grade" value=""></c:set>
	
	<c:if test="${jumsu < 60 }">
		<c:set var="grade" value="E"></c:set>
	</c:if>
	<c:if test="${jumsu > 60 }">
		<c:set var="grade" value="D"></c:set>
	</c:if>
	<c:if test="${jumsu > 70 }">
		<c:set var="grade" value="C"></c:set>
	</c:if>
	<c:if test="${jumsu > 80 }">
		<c:set var="grade" value="B"></c:set>
	</c:if>
	<c:if test="${jumsu > 90 }">
		<c:set var="grade" value="A"></c:set>
	</c:if>
	
	If 구문을 사용한 방식 학점 : <c:out value="${grade }"></c:out><br/>
	
	<!-- Choose 구문을 사용한 방식 -->	
	<c:choose>
		<c:when test="${jumsu > 90 }">
			<c:set var="grade" value="A"></c:set>
		</c:when>
		<c:when test="${jumsu > 80 }">
			<c:set var="grade" value="B"></c:set>
		</c:when>
		<c:when test="${jumsu > 70 }">
			<c:set var="grade" value="C"></c:set>
		</c:when>
		<c:when test="${jumsu > 60 }">
			<c:set var="grade" value="D"></c:set>
		</c:when>
		<c:otherwise>
			<c:set var="grade" value="E"></c:set>
		</c:otherwise>
	</c:choose>
	Choose 구문을 사용한 방식 학점 : <c:out value="${grade }"></c:out><br/>

</body>
</html>