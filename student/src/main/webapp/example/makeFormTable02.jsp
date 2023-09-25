<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<style>
		:root {accent-color: red;}
		.my_btn {background-color: white; color: red; border: none; border-radius: 3px; padding: 10px 10px;}
		body {background-color: blue;}
		th, td {padding: 10px; border: 1px solide red; color: white;}
	</style>
	
	<style type="text/css">
		#title {font-style: italic; font-weight: bold; color: white;}
		#birthday {color: black}
		.necessary {color: red;}
		input {color: black; font-family: cursive;}
		body {font-family: cursive;}
	</style>
	
	<meta charset="UTF-8">
	<title>EMPLOYEES</title>
</head>
<body>
	<h1 id="title">회원 가입 양식</h1>
	<form action="employeesTo.jsp">
		<table border="1">
			<tr>
				<td width="20%" align="center" class="necessary">아이디</td>
				<td width="80%" align="left">
					<input type="text" name="id">
			</tr>
			<tr>
				<td width="20%" align="center" class="necessary">이름</td>
				<td width="80%" align="left">
					<input type="text" name="name">
			</tr>
			<tr>
				<td width="20%" align="center" class="necessary">비밀번호</td>
				<td width="80%" align="left">
					<input type="password" name="password">
			</tr>
			<tr>
				<td width="20%" align="center">성별</td>
				<td width="80%" align="left">
					<input type="radio" name="gender" value="1" checked="checked">남자
					<input type="radio" name="gender" value="2">여자
			</tr>
			<tr>
				<td width="20%" align="center">생일</td>
				<td width="80%" align="left">
					<input id="birthday" type="date" name="birthday">
			</tr>
			<tr>
				<td width="20%" align="center">결혼여부</td>
				<td width="80%" align="left">
					<input type="radio" name="marriage" value="1">결혼
					<input type="radio" name="marriage" value="2">미혼
					<input type="radio" name="marriage" value="3">이혼
			</tr>
			<tr>
				<td width="20%" align="center">급여</td>
				<td width="80%" align="left">
					<input type="text" name="salary">
			</tr>
			<tr>
				<td width="20%" align="center">주소</td>
				<td width="80%" align="left">
					<input type="text" name="address">
			</tr>
			<tr>
				<td width="20%" align="center">매니저</td>
				<td width="80%" align="left">
					<input type="text" name="manager">
			</tr>
			<tr>
				<td width="20%" align="center">이미지</td>
				<td width="80%" align="left">
					<input type="file" name="image">
			</tr>
			<tr>
				<td width="20%" align="center">히든</td>
				<td width="80%" align="left">
					<input type="hidden" name="hidden" value="1234">
			</tr>
			<tr>
				<td width="20%" align="center">읽기전용</td>
				<td width="80%" align="left">
					<input type="text" name="readonly" value="abc" disabled="disabled">
			</tr>
			<tr>
				<td width="20%" align="center">취미</td>
				<td width="80%" align="left">
					<input type="checkbox" name="hobby" value="탁구">탁구
		<input type="checkbox" name="hobby" value="축구">축구
		<input type="checkbox" name="hobby" value="야구">야구
			</tr>
			<tr>
				<td width="20%" align="center">가고 싶은 국가</td>
				<td width="80%" align="left">
					<select name="travle">
						<option value="-">-- 국가를 선택하세요 -- 
						<option value="일본">일본
						<option value="중국">중국
						<option value="대만">대만
						<option value="베트남">베트남
					</select>
			</tr>
			<tr>
				<td width="20%" align="center">코멘트</td>
				<td width="80%" align="left">
					<textarea name="comment" rows="7" cols="30"></textarea>
			</tr>
			<tr >
				<td align="center" colspan="2">
					<input class="my_btn" type="reset" value="초기화">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input class="my_btn" type="submit" value="가입하기">
				</td>
			</tr>
		</table>
	</form>	
</body>
</html>