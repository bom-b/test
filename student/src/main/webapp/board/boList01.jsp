<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="./../common/bootstrap5.jsp" %>
<%@ include file="./../common/common.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>board list</title>
</head>
<body>
<div class="container">
		<h2>게시물 목록</h2>
		<p>게시물 목록을 보여주는 페이지 입니다.</p><br/><br/>
		<table class="table table-hover">
			<!-- table-hover, table-striped, table-condensed -->
			<thead>
				<tr>
					<th>글번호</th>
					<th>작성자</th>
					<th>비밀번호</th>
					<th>글 제목</th>
					<th>글 내용</th>
					<th>작성 일자</th>
					<th>조회수</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>5</td>
					<td>홍길동</td>
					<td>abc1234</td>
					<td>자바</td>
					<td>자바 어려워</td>
					<td>23/08/25</td>
					<td>
						<span class="badge">10</span>
					</td>
				</tr>
				<tr>
					<td>4</td>
					<td>박영희</td>
					<td>abc1234</td>
					<td>자바</td>
					<td>자바 어려워</td>
					<td>23/08/25</td>
					<td>
						<span class="badge">10</span>
					</td>
				</tr>
				<tr>
					<td>3</td>
					<td>김구</td>
					<td>abc1234</td>
					<td>자바</td>
					<td>자바 어려워</td>
					<td>23/08/25</td>
					<td>
						<span class="badge">10</span>
					</td>
				</tr>
				<tr>
					<td>2</td>
					<td>유관순</td>
					<td>abc1234</td>
					<td>자바</td>
					<td>자바 어려워</td>
					<td>23/08/25</td>
					<td>
						<span class="badge">10</span>
					</td>
				</tr>
				<tr>
					<td>1</td>
					<td>김유신</td>
					<td>abc1234</td>
					<td>자바</td>
					<td>자바 어려워</td>
					<td>23/08/25</td>
					<td>
						<span class="badge rounded-pill bg-secondary">10</span>
					</td>
				</tr>
			</tbody>
		</table>
		<!-- 
			ol : order list
			ul : unordered list
			li : list
		 -->
		<ul class="pagination">
			<li class="page-item">
				<a class="page-link" href="#">이전</a>
			</li>
			<li class="page-item">
				<a class="page-link" href="#">1</a>				
			</li>
			<li class="page-item active">
				<a class="page-link" href="#">2</a>				
			</li>
			<li class="page-item">
				<a class="page-link" href="#">3</a>				
			</li>
			<li class="page-item disabled">
				<a class="page-link" href="#">4</a>				
			</li>
			<li class="page-item">
				<a class="page-link" href="#">5</a>				
			</li>
			<li class="page-item">
				<a class="page-link" href="#">다음</a>				
			</li>
		</ul>
	</div>
</body>
</html>