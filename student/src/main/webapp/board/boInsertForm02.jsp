<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="./../common/bootstrap5.jsp"%>
<%@ include file="./../common/common.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script>
	$(document).ready(function(){
		
	});
	
	/* form validation check */
	function validCheck(){
		
		/* 글 제목은 3글자 이상 20글자 이하이어야 합니다. */
		var title = $('#title').val();
		if(title.length < 3 || title.length > 20){
			swal('글 제목은 3글자 이상 20글자 이하이어야 합니다.');
			$('#title').focus();
			return false; 
		}
		
		/* 글 내용은 5글자 이상 30글자 이하이어야 합니다. */
		var content = $('#content').val();
		if(content.length < 5 || content.length > 30){
			swal('글 제목은 5글자 이상 30글자 이하이어야 합니다.');
			$('#content').focus();
			return false; 
		}
		
		/* 날짜 형식은 반드시 yyyy/mm/dd 형식 또는 yyyy-mm-dd으로 작성해 주세요. */
		var regdate = $('#regdate').val();
		var regex1 = /^\d{4}\/[01]\d{1}\/[0123]\d{1}$/;
		var regex2 = /^\d{4}\-[01]\d{1}\-[0123]\d{1}$/;
		var result1 = regex1.test(regdate);
		var result2 = regex2.test(regdate);
		
		if(result1 == false && result2 == false){
			swal('날짜 형식은 반드시 yyyy/mm/dd 형식 또는 yyyy-mm-dd으로 작성해 주세요.');
			return false;
		}
		
	}
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
		<br />
		<h2>게시물 등록</h2>
		<p>게시물 등록 페이지</p>
		<br />
		<form action="">
			<div class="input-group">
				<span class="input-group-text">작성자</span> <input
					class="form-control" type="text" id="id" name="id">
			</div>
			<div class="input-group">
				<span class="input-group-text">비밀번호</span> <input
					class="form-control" type="password" id="password" name="password">
			</div>
			<div class="input-group">
				<span class="input-group-text">글제목</span> <input
					class="form-control" type="text" id="title" name="title">
			</div>
			<div class="input-group">
				<span class="input-group-text">글내용</span> <input
					class="form-control" type="text" id="content" name="content">
			</div>
			<div class="input-group">
				<span class="input-group-text">작성일자</span> <input
					class="form-control" type="date" id="regdate" name="regdate">
			</div>
			<!-- <br/>
			<div class="mb-3">
				<label for="id" class="form-label">작성자</label>
				<input class="form-control form-control-lg" type="text" id="id" name="id" placeholder="홍길동">
			</div>
			<br/> -->
			<div id="buttonset" class="input-group">
				<button type="submit" class="btn btn-primary"
					onclick="return validCheck();">등록</button>
				&nbsp;&nbsp;&nbsp;
				<button type="reset" class="btn btn-primary">초기화</button>
			</div>
		</form>
	</div>
</body>
</html>