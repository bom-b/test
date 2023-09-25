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
	
	<script>
		$(document).ready(function(){
			
		});	
		
		/* form validation check */
		function validCheck(){
			
			var id = $('#id').val();
			if(id.length < 4 || id.length > 10){
				swal('아이디는 4자리 이상 10자리 이하로 입력해야 합니다.');
				$('#id').focus();
				return false; /* false이면 이벤트 전파 방지 */
			}
			
			var name = $('#name').val();
			if(name.length < 3 || name.length > 15){
				swal('이름은 3자리 이상 15자리 이하로 입력 가능 합니다.');
				$('#name').focus();
				return false;
			}
			
			var password = $('#password').val();
			if(password.length < 3 || password.length > 12){
				swal('비밀번호는 3자리 이상 12자리 이하로 입력해야 합니다.');
				$('#password').focus();
				return false;
			}
			
			var regex = /^[a-z]\S{4,11}$/;
			var password = $('#password').val();
			var result = regex.test(password);
			
			if(result == false){
				swal('비밀번호의 첫글자는 반드시 소문자이어야 합니다.');
				return false;
			}
			
			/* if(password.indexOf('@') <= 0 && password.indexOf('#') <= 0 && password.indexOf('$') <= 0){
				swal('비밀번호는 특수문자 @,#,$ 중 하나를 반드시 포함해야 합니다.');
				return false;
			} */
			
			var radioList = $('input[type="radio"]:checked');
			if(radioList.length == 0) {
				swal('성별이 선택되지 않았습니다.');
				return false;
			}
			
			/* jqueryUI 플러그인 date picker  */			
			/* var birthday = $('#birthday').val();
			var regex = /^\d{4}\/[01]\d{1}\/[0123]\d{1}$/;
			var result = regex.test(birthday);
			
			if(result == false){
				swal('생일은 yyyy/mm/dd 형식으로 입력해야 합니다.');
				return false;
			} */
		}
		
	</script>
</head>
<body>
	<div class="container">
		<br/>
		<h2>회원가입</h2>
		<p>회원가입 페이지</p>
		<br/>
		<form action="<%=withFormTag%>" method="post">
			<input type="hidden" name="command" value="meInsert">
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
				<span class="input-group-text">생일</span>
				<input class="form-control" type="date" id="birth" name="birth">
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
				<button type="submit" class="btn btn-primary" onclick="return validCheck();">등록</button>
				&nbsp;&nbsp;&nbsp;
				<button type="reset" class="btn btn-primary">초기화</button>
			</div>
		</form>
	</div>
</body>
</html>