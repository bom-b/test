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
    <form action="<%=appName%>/world" method="post">
        아이디: <input type="text" name="pnum" value="01"><br/>
        이름: <input type="text" name="name" value="오렌지 쥬스"><br/>
        제조사: <input type="text" name="company" value="델몬트"><br/>
        가격: <input type="text" name="price" value="3000"><br/>
        <input type="submit" value="전송">
    </form>
</body>
</html>
