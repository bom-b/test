<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="./../common/bootstrap5.jsp" %>
<%@ include file="./../common/common.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script type="text/javascript">
		$(document).ready(function() {	
			var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
			var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
				return new bootstrap.Tooltip(tooltipTriggerEl)
			});		
		});
	</script>
</head>
<body>
	<br/>
	<div class="container">
		<h2>로그인 페이지</h2>
		<p>로그인을 위한 페이지 입니다.</p><br/><br/>
		<form action="loginproc.jsp" method="post">
			<div>
				<label for="id" class="form-label">아이디 : </label>
				<input type="text" class="form-control" id="id" name="id" placeholder="아이디를 입력해 주세요."
					data-bs-toggle="tooltip" data-bs-placement="top" title="아이디는 3글자 이상 10글자 이하입니다.">
			</div>
			<br/>
			<div>
				<label for="password" class="form-label">비밀번호 : </label>
				<input class="form-control" type="password" id="password" name="password">
			</div>
			<br/>
			<!-- contextual class : btn-primary, btn-info, btn-danger -->
			<button type="submit" class="btn btn-primary">로그인</button>
			<a type="button" href="meInsertForm01.jsp" class="btn btn-info">회원가입</a>
		</form>
	</div>
</body>
</html>