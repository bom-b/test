<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="./../common/bootstrap5.jsp" %>
<%@ include file="./../common/common.jsp" %>
<%@ page import="com.shopping.model.dao.MemberDao" %>
<%@ page import="com.shopping.model.bean.Member"%>
<%
	// 스크립트 릿: 자바 코딩하는 곳
	//String id = request.getParameter("id");
	//out.print("아이디: " + id);
	
	String id = "hong";
	MemberDao dao = new MemberDao();
	Member bean = dao.getDataByPK(id);
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	
	<script type="text/javascript">
		$(document).ready(function(){
			
			$('input[value="<%=bean.getGender()%>"]').attr('checked', true);
			
			/* 취미 관련 문자열 예시 : 탁구,축구, */
			var hobbyCheck = "<%=bean.getHobby()%>";
			
			/* 취미 체크 박스 목록 */
			var checkHobbies = $('input[name=hobby]');
			
			for (var i = 0; i < checkHobbies.length; i++) {
				var idx = hobbyCheck.indexOf(checkHobbies[i].value);
				
				if(idx >= 0) {
					checkHobbies[i].checked = true;
				}
				
			}				
		});
	</script>
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
		<h2>회원 정보 수정</h2>
		<p>특정 회원에 대하여 정보를 수정하는 페이지</p>
		<br/>
		<form action="">
			<div class="input-group">
				<span class="input-group-text">아이디</span>
				<input class="form-control" type="text" id="fakeid" name="fakeid" value="<%=bean.getId()%>" disabled="disabled">
				<input type="hidden" id="id" name="id" value="<%=bean.getId()%>">
			</div>
			<div class="input-group">
				<span class="input-group-text">비밀번호</span>
				<input class="form-control" type="password" id="password" name="password">
			</div>
			<div class="input-group">
				<span class="input-group-text">이름</span>
				<input class="form-control" type="text" id="name" name="name" value="<%=bean.getName()%>">
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
				<input class="form-control" type="date" id="birthday" name="birthday" value="<%=bean.getBirth()%>">
			</div>
			<div class="input-group">
				<span class="input-group-text">결혼여부</span>
				<input class="form-control" type="text" id="marriage" name="marriage" value="<%=bean.getMarriage()%>">
			</div>
			<div class="input-group">
				<span class="input-group-text">급여</span>
				<input class="form-control" type="number" id="salary" name="salary" value="<%=bean.getSalary()%>">
			</div>
			<div class="input-group">
				<span class="input-group-text">주소</span>
				<input class="form-control" type="text" id="address" name="address" value="<%=bean.getAddress()%>">
			</div>
			<div class="input-group">
				<span class="input-group-text">관리자</span>
				<input class="form-control" type="text" id="manager" name="manager" value="<%=bean.getManager()%>">
			</div>
			<div id="buttonset" class="input-group">
				<button type="submit" class="btn btn-primary">수정</button>
				&nbsp;&nbsp;&nbsp;
				<button type="reset" class="btn btn-primary">초기화</button>
			</div>
		</form>
	</div>
</body>
</html>