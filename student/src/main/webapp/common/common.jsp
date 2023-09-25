<%@page import="com.shopping.model.bean.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@page import="java.util.*"%>
<%@page import="java.util.List"%>

<%
	// appName: 애플리케이션 이름 (프로젝트 이름_student)
	String appName = request.getContextPath();
	String mappingName = "/Shopping"; //in FrontController.java file
	
	// form 태그에서 사용할 변수
	String withFormTag = appName + mappingName;
	
	// form 태그가 아닌 영역에서 사용할 변수
	String notWithFormTag = appName + mappingName + "?command=";
	
	//out.print("프로젝트이름 : " + appName);
	//out.print("mappingName : " + mappingName + "<br/>");
	//out.print("withFormTag : " + withFormTag + "<br/>");
	//out.print("notWithFormTag : " + notWithFormTag + "<br/>");
%>

<%-- jstl을 위한 태그 라이브러리 선언 --%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- whologin 변수는 현재 로그인 상태를 알려주는 변수입니다. -->
<!-- 로그인 안됨(0) 일반 사용자(1) 관리자(2) -->
<c:set var="whologin" value="0" />
<c:if test="${not empty sessionScope.loginfo}">
	<c:if test="${sessionScope.loginfo.id == 'admin'}">
		<c:set var="whologin" value="2" />
	</c:if>
	<c:if test="${sessionScope.loginfo.id != 'admin'}">
		<c:set var="whologin" value="1" />
	</c:if>
</c:if>

<!-- 로그인 상태를 보여주는 코드 -->
<%-- whologin : ${whologin} --%>


<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	
	<!-- 이 파일은 모든 문서에서 공용으로 참조할 파일입니다. -->
	<!-- 자바 관련 변수 및 패키지 임포트, 네비게이션바, jstl 등등 -->
	<script src="/js/jquery.js"></script>
	<script src="/js/bootstrap.min.js"></script>
	
	<!-- sweetalert -->
	<script src="<%= appName %>/js/sweetalert.js"></script>
	
	<!-- jQuery UI 추가 -->
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	
	<style>
		.navbar{
			margin-bottom: 30px; 
		}
		
		.alert-danger{
			margin: 20px;
		}
	</style>
</head>
<body>
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<div class="container-fluid">
			<a class="navbar-brand" href="#">쇼핑몰</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="collapsibleNavbar">
				<ul class="navbar-nav">
					<li class="nav-item">
						<c:if test="${whologin eq 0 }">
							<a class="nav-link" href="#">로그인 안됨</a>
						</c:if>
						<c:if test="${whologin ne 0 }">
							<a class="nav-link" href="#">${sessionScope.loginfo.name}</a>
						</c:if>
					</li>
					
					<!-- 멤버 섹션 -->
					<li class="nav-item dropdown">
						<a class="nav-link dropdown-toggle" href="#" role="button"
							data-bs-toggle="dropdown">회원</a>
						<ul class="dropdown-menu">
							<c:if test="${whologin eq 0}">
								<li><a class="dropdown-item" href="<%=notWithFormTag%>meInsert">회원가입</a></li>
								<li><a class="dropdown-item" href="<%=notWithFormTag%>meLogin">로그인</a></li>
								<!-- <%=notWithFormTag%>melogin -->
							</c:if>	
							<c:if test="${whologin ne 0}">
								<li><a class="dropdown-item" href="#">정보수정</a></li>
								<li><a class="dropdown-item" href="<%=notWithFormTag%>meLogout">로그아웃</a></li>
								<li><a class="dropdown-item" href="<%=notWithFormTag%>meDetail&id=${sessionScope.loginfo.id}">상세보기</a></li>
								<li><a class="dropdown-item" href="<%=notWithFormTag%>meDelete&id=${sessionScope.loginfo.id}">탈퇴하기</a></li>
							</c:if>
							<c:if test="${whologin eq 2}">
							<li><a class="dropdown-item" href="<%=notWithFormTag%>meList">목록보기</a></li>
							</c:if>	
						</ul>
					</li>
					
					<!-- 게시물 섹션 -->
					<li class="nav-item dropdown">
						<a class="nav-link dropdown-toggle" href="#" role="button"
							data-bs-toggle="dropdown">게시물</a>
						<ul class="dropdown-menu">
							<c:if test="${whologin ne 0}">
								<li><a class="dropdown-item" href="<%=notWithFormTag%>boInsert">게시물 등록</a></li>
							</c:if>
							<li><a class="dropdown-item" href="<%=notWithFormTag%>boList">목록 보기</a></li>
						</ul>
					</li>
					
					<!-- 상품 섹션 -->
					<li class="nav-item dropdown">
						<a class="nav-link dropdown-toggle" href="#" role="button"
							data-bs-toggle="dropdown">상품</a>
						<ul class="dropdown-menu">
							<c:if test="${whologin eq 2}">
								<li><a class="dropdown-item" href="<%=notWithFormTag%>prInsert">상품 등록</a></li>
							</c:if>
							<li><a class="dropdown-item" href="<%=notWithFormTag%>prList">목록보기</a></li>
						</ul>
					</li>
					<!-- 뷰 섹션 -->
					<li class="nav-item dropdown">
						<a class="nav-link dropdown-toggle" href="#" role="button"
							data-bs-toggle="dropdown">데이터 보기</a>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item" href="<%=notWithFormTag%>vwList">목록보기</a></li>
						</ul>
					</li>
					
					<!-- 쇼핑몰 section -->
                	 <li class="nav-item dropdown">
                   	<a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">쇼핑몰</a>
                  	 <ul class="dropdown-menu">          
                     <li>
                        <a class="dropdown-item" href="<%=notWithFormTag%>maList">
                           카트 내역 보기
                        </a>
                     </li>
                     <li>
                        <a class="dropdown-item" href="<%=notWithFormTag%>maHistory">
                           나의 쇼핑 내역
                        </a>
                     </li>
                   </ul>
                 </li> 
				</ul>
			</div>
		</div>
	</nav>
	<c:if test="${not empty sessionScope.alertMessage}">
		<%-- 사용자에게 주의/경고/오류 등을 알려 주기 위한 Alert Box --%>
		<div class="alert alert-danger alert-dismissible">
	    	<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
	    	<strong>경고 메시지</strong> ${sessionScope.alertMessage}
	  	</div>
	</c:if>
	<%-- 보여준 Alert Box를 session 영역에서 제거합니다. --%>
	<c:remove var="alertMessage" scope="session"/> 
	
	<c:if test="${not empty sessionScope.postiveAlertMessage}">
		<%-- 긍정적인 알림 메시지 --%>
		<div class="alert alert-success alert-dismissible">
	    	<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
	    	<strong>성공!</strong> ${sessionScope.postiveAlertMessage}
	  	</div>
	</c:if>
	<%-- 보여준 Alert Box를 session 영역에서 제거합니다. --%>
	<c:remove var="postiveAlertMessage" scope="session"/> 
</body>
</html>