<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>EMPLOYEES</title>
	
	<script type="text/javascript" src="./../js/jquery.js"></script> <!-- js 파일 로딩 -->
	<script type="text/javascript">
		$(document).ready(function(){
			var label_count = $('label').length;
			$('#label_cnt').html('<b>' + label_count + '</b>');
			
			/* type password에 클래스 지정해서 스타일 지정하기 */
			$(':password').addClass('password_style');
			$('.password_style').css('background-color', 'red');
			
			/* disabled="disabled" 속성을 가진 항목에 대하여 스타일 지정하기 */
			$('input[type="text"]:disabled').css('background-color', 'red');
			
			/* 콤보 박스에 선택된 요소 값 찾기 */
			/* 버튼을 눌렀을때 */
			$('#check01').click(function(){
				var checklist = $('option[selected="selected"]');
				
				var result = '';
				for(var i=0; i < checklist.length; i++){
					if(checklist[i].selected){
						result += checklist[i].value() + ' ';
					}
				}
				$('#check_result_01').html(result);
			});
			
			
		})
	</script>
		
	<style type="text/css">
		.label_style{background-color: yellow; font-size: 30px;}
	</style>
</head>
<body>
	<h1>회원 가입 양식</h1>
	<form action="employeesTo.jsp">
		<label>아이디 : </label>
		<input type="text" name="id">
		<br/>
		<label>이름 : </label>
		<input type="text" name="name">
		<br/>
		<label>비밀번호 : </label>
		<input type="password" name="password">
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
		<label>급여 : </label>
		<input type="text" name="salary">
		<br/>
		<label>주소 : </label>
		<input type="text" name="address">
		<br/>
		<label>매니저 : </label>
		<input type="text" name="manager">
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
			<option value="베트남" >베트남
		</select>
		<br/>
		<label>코멘트 : </label>
		<textarea name="comment" rows="7" cols="30"></textarea>
		<br/>
		<br/>
		<input type="reset" value="초기화">
		<!-- entity : 특수문자나 whitecharacter 등을 표현하기 위한 기법 -->
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="submit" value="가입하기">
	</form>
	<hr/>
	<label>정보 확인</label><br>
	라벨 개수 : <span id="label_cnt"></span><br>
	
	<button id="check01">콤보박스 체크 요소 확인</button><br/><br/>
	
	체크 결과 01 : <span id="check_result_01"></span><br/>
	
</body>
</html>