<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/common/common.jsp" %>
<%@ include file="/common/bootstrap5.jsp" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board list</title>
<script type="text/javascript">
	$(document).ready(function(){
		// mode 값 유지하기
		var optionList = $('#mode option'); // mode 안에 있는 옵션 태그들을 옵션리스트 변수에 대입
		for(var i=0; i < optionList.length; i++) {
			if (optionList[i].value == '${requestScope.pageInfo.mode}') {
				optionList[i].selected = true;
			}
		}
		
		// keyword 값 유지하기
		$('#keyword').val('${requestScope.pageInfo.keyword}')
	});
	
	// 전체 검색
	function searchAll() {
		location.href = '<%=notWithFormTag%>boList';
	}
	
	// 글쓰기
	function writeForm() {
		location.href = '<%=notWithFormTag%>boInsert';
	}
	
/* 	// 수정시에 로그인 정보 체크
	function validCheck(){
		
		if('${sessionScope.loginfo.id}' !== '${bean.id}') {
			swal('글 작성자만 게시물을 수정할 수 있습니다.')
			return false;
		}
		
	} */
</script>

<style type="text/css">

.rounded-pill{opacity: 0.6;}

#tableMain{
	background-color: #ebebeb;
}

.searchBox{
	margin: auto;
}

/* .keyword, .mode, .col {
	margin: auto;
}  */

.form-control-sm{
	border: 1px solid #FAEBD7;
}

#pagingStatus {
	margin-left: 20px;
}


</style>

</head>
<body>
<div class="container">
		<h2>게시물 목록</h2>
		<p>게시물 목록을 보여주는 페이지 입니다.</p><br/><br/>
		<table class="table table-hover">
			<thead>
				<tr>
					<th id="tableMain">글번호</th>
					<th id="tableMain">작성자</th>
					<th id="tableMain">비밀번호</th>
					<th id="tableMain">글 제목</th>
					<th id="tableMain">글 내용</th>
					<th id="tableMain">작성 일자</th>
					<th id="tableMain">조회수</th>
					<th id="tableMain"></th>
					<th id="tableMain"></th>
				</tr>
			</thead>
			<tbody>
			<tr>
			    <td colspan="9" align="center">
			        <form name="myform" action="<%=withFormTag%>" method="get">
			            <input type="hidden" name="command" value="boList">
			            <select class="form-control-sm" id="mode" name="mode">
			                <option value="all" selected="selected">--- 선택해 주세요 ---</option>
			                <option value="id">작성자</option>
			                <option value="subject">글제목</option>
			                <option value="content">글내용</option>
			            </select>
			            <input class="form-control-sm" type="text" name="keyword" id="keyword" placeholder="키워드 입력">
			            <button type="submit" class="btn btn-primary form-control-sm">검색</button>
			            <button type="button" class="btn btn-primary form-control-sm" onclick="searchAll();">초기화</button>
			            <button type="button" class="btn btn-info form-control-sm" onclick="writeForm();">글 쓰기</button>
			            <span id="pagingStatus">${requestScope.pageInfo.pagingStatus} </span>
			        </form>
			    </td>
			</tr>
			<c:forEach var="bean" items="${requestScope.datalist }">
				<tr>
					<td>${bean.no }</td>
					<td>${bean.id }</td>
					<td>${bean.password }</td>
					<td>
						<c:forEach var="i" begin="1" end="${bean.depth }">
							<span class="badge rounded-pill bg-secondary">re</span>
						</c:forEach>
						<a href="<%=notWithFormTag%>boDetail&no=${bean.no}">${bean.subject }</a>
					</td>
					<td>${bean.contents }</td>
					<td>${bean.regdate }</td>
					<td>
						<c:if test="${bean.readhit < 100}">
							<span class="badge rounded-pill bg-secondary">${bean.readhit }</span>
						</c:if>
						<c:if test="${bean.readhit >= 100}">
							<span class="badge rounded-pill bg-danger">${bean.readhit }</span>
						</c:if>
					</td>
					<td>
					<c:set var="replyInfo" value="&groupno=${bean.groupno}&orderno=${bean.orderno}&depth=${bean.depth}"/>
					
					<c:if test="${sessionScope.loginfo.id==bean.id}">
						<a id="updateAnchor" href="<%=notWithFormTag%>boUpdate&no=${bean.no}${requestScope.pageInfo.flowParameter}${replyInfo}">
							수정
						</a>
					</c:if>
					</td>
					<td>
					<c:set var="replyInfo" value="&groupno=${bean.groupno}&orderno=${bean.orderno}&depth=${bean.depth}"/>
					
						<a id="updateAnchor" href="<%=notWithFormTag%>boReply&no=${bean.no}${requestScope.pageInfo.flowParameter}${replyInfo}">
							답글
						</a>
					</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		${requestScope.pageInfo.pagingHtml} 
	</div>
</body>
</html>