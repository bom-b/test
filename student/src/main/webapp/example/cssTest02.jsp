<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>EMPLOYEES</title>
	<style>
		:root{accent-color: red;}
		body{background-color: black;}
	</style>
	<style type="text/css">
		#title{font-style: italic; font-weight: bold; color: white;}
	
		label{font-weight: bold; font-size: 18px; font: 500; color: white;}
		
		input{color: white;}
		
		.necessary{color: red;}
	</style>
</head>
<body>
	<h1 id="title">회원 가입 양식</h1>
	<form action="employeesTo.jsp">
		<label class="necessary">아이디 : </label>
		<input type="text" name="id">
		<br/>
		<label class="necessary">이름 : </label>
		<input type="text" name="name">
		<br/>
		<label class="necessary">비밀번호 : </label>
		<input type="password" name="password">
		<br/>
		<br/>
		<label>성별 : </label>
		<input type="radio" name="gender" value="1" checked="checked">남자
		<input type="radio" name="gender" value="2">여자
		<br/>
		<label>생일 : </label>
		<input type="date" name="birthday">
		<br/>
		<label>결혼여부 : </label>
		<input type="radio" name="marriage" value="1">결혼
		<input type="radio" name="marriage" value="2">미혼
		<input type="radio" name="marriage" value="3">이혼
		<br/>
		<br/>
		<label>급여 : </label>
		<input type="text" name="salary">
		<br/>
		<label>주소 : </label>
		<input type="text" name="address">
		<br/>
		<label>매니저 : </label>
		<input type="text" name="manager">
		<br/>
		<br/>
		<label>이미지 : </label>
		<input type="file" name="image">
		<br/>
		<label>히든 : </label>
		<input type="hidden" name="hidden" value="1234">
		<br/>
		<label>읽기전용 : </label>
		<input type="text" name="readonly" value="abc" disabled="disabled">
		<br/>
		<br/>
		<label>취미 : </label>
		<input type="checkbox" name="hobby" value="탁구">탁구
		<input type="checkbox" name="hobby" value="축구">축구
		<input type="checkbox" name="hobby" value="야구">야구
		<br/>
		<label>가고 싶은 국가 : </label>
		<select name="travle">
			<option value="-">-- 국가를 선택하세요 -- 
			<option value="일본">일본
			<option value="중국">중국
			<option value="대만">대만
			<option value="베트남">베트남
		</select>
		<br/>
		<br/>
		<label>코멘트 : </label>
		<textarea name="comment" rows="7" cols="30"></textarea>
		<br/>
		<br/>
		<input type="reset" value="초기화">
		<!-- entity : 특수문자나 whitecharacter 등을 표현하기 위한 기법 -->
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="submit" value="가입하기">
</body>
</html>