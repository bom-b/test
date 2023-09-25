<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="./../common/bootstrap5.jsp" %>
<%@ include file="./../common/common.jsp" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <form action="<%=appName%>/Traffic" method="post" name="selec">
        <div class="input-group">
				<span class="input-group-text">운송수단</span>
				<select name="command" class="form-select">
					<option value="-">항목을 선택해주세요</option>
				  	<option value="horse">말</option>
				  	<option value="car">자동차</option>
				  	<option value="airplane">비행기</option>
				</select>
			</div>
        <input type="submit" value="전송">
    </form>
</body>
</html>
