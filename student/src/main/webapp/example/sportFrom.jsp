<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="./../common/bootstrap5.jsp" %>
<%@ include file="./../common/common.jsp" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#selec{
	margin-top: 50px;
	margin-bottom: 50px;
	margin-left: 50px;
	margin-right: 50px;
}
</style>
</head>
<body>
    <form action="<%=appName%>/Sport" method="post" name="selec">
        <div class="input-group">
				<span class="input-group-text">운동 경기 선택</span>
				<select name="command" class="form-select">
					<option value="-">항목을 선택해주세요</option>
				  	<option value="baseball">야구</option>
				  	<option value="football">축구</option>
				</select>
			</div>
        <input type="submit" value="전송">
    </form>
</body>
</html>
