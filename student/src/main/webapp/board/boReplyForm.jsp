<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/common/common.jsp" %>
<%@ include file="/common/bootstrap5.jsp" %>

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
		
		/* /* 글 내용은 5글자 이상 30글자 이하이어야 합니다. */
		var content = $('#content').val();
		if(content.length < 5 || content.length > 30){
			swal('글 제목은 5글자 이상 30글자 이하이어야 합니다.');
			$('#content').focus();
			return false; 
		} */
		
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

#boardNo{
	display: none;
	visibility: hidden;
}
</style>
</head>
<body>
	<div class="container">
		<br />
		<h2>게시물 답글</h2>
		<p>게시물에 대한 답글을 작성해주세요</p>
		<br />
		<form action="<%=withFormTag%>" method="post">
			<input type="hidden" name="command" value="boReply">
			
			<input type="hidden" name="pageNumber" value="<%=request.getParameter("pageNumber")%>">
			<input type="hidden" name="pageSize" value="<%=request.getParameter("pageSize")%>">
			<input type="hidden" name="mode" value="<%=request.getParameter("mode")%>">
			<input type="hidden" name="keyword" value="<%=request.getParameter("keyword")%>">
			
			<%-- 답글과 관련된 파라미터 목록 --%>
			<input type="hidden" name="groupno" value="<%=request.getParameter("groupno")%>">
			<input type="hidden" name="orderno" value="<%=request.getParameter("orderno")%>">
			<input type="hidden" name="depth" value="<%=request.getParameter("depth")%>">
			
			<div class="input-group" id="boardNo">
				<span class="input-group-text">게시물 번호</span> 
				<input class="form-control" type="number" id="no" name="no" value="${requestScope.bean.no}">
			</div>
			<div class="input-group">
				<span class="input-group-text">작성자</span> 
				
				<c:set var="userInfo" value="${sessionScope.loginfo.name}(${sessionScope.loginfo.id})"/>
				<input class="form-control" type="text" id="fakeWriter" name="fakeWriter" value="${userInfo }" disabled="disabled">
				<input class="form-control" type="hidden" id="id" name="id" value="${sessionScope.loginfo.id}">
			</div>	
			<div class="input-group">
				<span class="input-group-text">글제목</span> 
				<input class="form-control" type="text" id="title" name="title">
			</div>
			<div class="input-group">
				<span class="input-group-text">글내용</span> 
				<input class="form-control" type="text" id="content" name="content">
			</div>
			<div class="input-group">
				<span class="input-group-text">비밀번호</span> <input
					class="form-control" type="password" id="password" name="password">
			</div>
			<div class="input-group">
				<span class="input-group-text">작성일자</span> 
				<input class="form-control" type="date" id="regdate" name="regdate">
			</div>
			<div id="buttonset" class="input-group">
				<button type="submit" class="btn btn-primary"
					onclick="return validCheck();">답글 등록</button>
				&nbsp;&nbsp;&nbsp;
				<button type="reset" class="btn btn-primary">초기화</button>
			</div>
		</form>
	</div>
</body>
</html>