<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="./../common/bootstrap5.jsp" %>
<%@ include file="./../common/common.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	
	<style type="text/css">
		/* box model에 대한 공부가 필요하다. */
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
		<h2>회원가입</h2>
		<p>회원가입 페이지</p>
		<br/>
		<form action="">
			<div class="input-group">
				<span class="input-group-text">아이디</span>
				<input class="form-control" type="text" id="id" name="id">
			</div>
			<div class="input-group">
				<span class="input-group-text">비밀번호</span>
				<input class="form-control" type="password" id="password" name="password">
			</div>
			<div class="input-group">
				<span class="input-group-text">이름</span>
				<input class="form-control" type="text" id="name" name="name">
			</div>
			<div class="input-group">
				<span class="input-group-text">성별</span>
				<div class="form-control">
					<label class="radio-inline radio_gender">
						<input type="radio" id="gender1" name="gender" value="male">남자
					</label>
					<label class="radio-inline radio_gender">
						<input type="radio" id="gender2" name="gender" value="female">여자
					</label>
				</div>
			</div>
			<div class="input-group">
				<span class="input-group-text">취미</span>
				<div class="form-control">
					<label class="checkbox-inline checkbox_hobby">
						<input type="checkbox" id="hobby1" name="hobby" value="탁구">탁구
					</label>
					<label class="checkbox-inline checkbox_hobby">
						<input type="checkbox" id="hobby2" name="hobby" value="농구">농구
					</label>
					<label class="checkbox-inline checkbox_hobby">
						<input type="checkbox" id="hobby3" name="hobby" value="야구">야구
					</label>
					<label class="checkbox-inline checkbox_hobby">
						<input type="checkbox" id="hobby4" name="hobby" value="축구">축구
					</label>
				</div>
			</div>
			<div class="input-group">
				<span class="input-group-text">생일</span>
				<input class="form-control" type="date" id="birthday" name="birthday">
			</div>
			<div class="input-group">
				<span class="input-group-text">결혼여부</span>
				<input class="form-control" type="text" id="marriage" name="marriage">
			</div>
			<div class="input-group">
				<span class="input-group-text">급여</span>
				<input class="form-control" type="number" id="salary" name="salary">
			</div>
			<div class="input-group">
				<span class="input-group-text">주소</span>
				<input class="form-control" type="text" id="address" name="address">
			</div>
			<div class="input-group">
				<span class="input-group-text">관리자</span>
				<input class="form-control" type="text" id="manager" name="manager">
			</div>
			<div id="buttonset" class="input-group">
				<button type="submit" class="btn btn-primary">등록</button>
				&nbsp;&nbsp;&nbsp;
				<button type="reset" class="btn btn-primary">초기화</button>
			</div>
		</form>
	</div>
</body>
</html>