<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- %@는 지시어, include는 저 파일을 이 파일에 포함시킨다는 명령 -->    
<%@ include file="./../common/bootstrap5.jsp" %>
<%@ include file="./../common/common.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<h2>상품 목록</h2>
		<p>고객들이 구매하고자 하는 상품 목록 페이지 입니다.</p><br/><br/>
		<table class="table table-borderless">
			<thead>
			</thead>
			<tbody>
				<tr>
					<td>
						<div class="card" style="width:19rem;">
							<a href="#">
								<img class="card-img-top" alt="상품01" src="./../image/a (1).jpeg" width="200" height="200">
								<div class="card-body">
									<h5 class="card-title">상품01</h5>
									<p class="card-text">이 상품은 좋아요.</p>
								</div>
							</a>
						</div>
					</td>
					<td>
						<div class="card" style="width:19rem;">
							<a href="#">
								<img class="card-img-top" alt="상품01" src="./../image/a (2).jpeg" width="200" height="200">
								<div class="card-body">
									<h5 class="card-title">상품01</h5>
									<p class="card-text">이 상품은 좋아요.</p>
								</div>
							</a>
						</div>
					</td>
					<td>
						<div class="card" style="width:19rem;">
							<a href="#">
								<img class="card-img-top" alt="상품01" src="./../image/a (3).jpeg" width="200" height="200">
								<div class="card-body">
									<h5 class="card-title">상품01</h5>
									<p class="card-text">이 상품은 좋아요.</p>
								</div>
							</a>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="card" style="width:19rem;">
							<a href="#">
								<img class="card-img-top" alt="상품01" src="./../image/a (4).jpeg" width="200" height="200">
								<div class="card-body">
									<h5 class="card-title">상품01</h5>
									<p class="card-text">이 상품은 좋아요.</p>
								</div>
							</a>
						</div>
					</td>
					<td>
						<div class="card" style="width:19rem;">
							<a href="#">
								<img class="card-img-top" alt="상품01" src="./../image/a (5).jpeg" width="200" height="200">
								<div class="card-body">
									<h5 class="card-title">상품01</h5>
									<p class="card-text">이 상품은 좋아요.</p>
								</div>
							</a>
						</div>
					</td>
					<td>
						<div class="card" style="width:19rem;">
							<a href="#">
								<img class="card-img-top" alt="상품01" src="./../image/a (6).jpeg" width="200" height="200">
								<div class="card-body">
									<h5 class="card-title">상품01</h5>
									<p class="card-text">이 상품은 좋아요.</p>
								</div>
							</a>
						</div>
					</td>
				</tr>	
			</tbody>
		</table>
	</div>
</body>
</html>