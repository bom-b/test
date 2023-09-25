<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./../common/bootstrap5.jsp" %>
<%@ include file="./../common/common.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	
	<script type="text/javascript" src="./../js/jquery.js"></script> <!-- js 파일 로딩 -->
	<script type="text/javascript">
		/* $(document).ready(function(){}); */
		/* 이 문서가 완전 로딩 되었을때 document ready 구문이 실행됩니다.*/
		$(document).ready(function(){
			$input-dorder-color: red;
		});
	</script>
	<style type="text/css">
		h2, p {
		margin: 15px;
		}
		.input-group {
			margin: 15px;
		}
		.input-group-text {
			display: block;
			margin-left: auto;
			margin-right: auto;
		}
		#buttonset {
			margin-top: 25px;
		}
		.radio_gender, .checkbox_hobby {
			font-size: 0.9rem; /* 주위 글꼴의 1.1배 */
		}
	</style>
</head>
<body>
	<div class="container">
		<br/>
		<h2>게시물 등록</h2>
		<p>게시물 등록 페이지</p>
		<br/>
		<form action="">
			<div class="input-group">
				<span class="input-group-text">작성자</span>
				<input class="form-control" type="text" id="id" name="id">
			</div>
			<div class="input-group">
				<span class="input-group-text">비밀번호</span>
				<input class="form-control" type="password" id="password" name="password">
			</div>
			<div class="input-group">
				<span class="input-group-text">글제목</span>
				<input class="form-control" type="text" id="title" name="title">
			</div>
			<div class="input-group">
				<span class="input-group-text">글내용</span>
				<input class="form-control" type="text" id="content" name="content">
			</div>
			<div class="input-group">
				<span class="input-group-text">작성일자</span>
				<input class="form-control" type="text" id="regdate" name="regdate" >
			</div>
			<!-- <br/>
			<div class="mb-3">
				<label for="id" class="form-label">작성자</label>
				<input class="form-control form-control-lg" type="text" id="id" name="id" placeholder="홍길동">
			</div>
			<br/> -->
			<div id="buttonset" class="input-group ">
				<button type="submit" class="btn btn-primary ">등록</button>
				&nbsp;&nbsp;&nbsp;
				<button type="reset" class="btn btn-primary">초기화</button>
			</div>
		</form>
	</div>
</body>
</html>