<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./../common/bootstrap5.jsp" %>
<%@ include file="./../common/common.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	
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
	</style>
</head>
<body>
	<div class="container">
		<br/>
		<h2>상품등록</h2>
		<p>상품등록 페이지</p>
		<br/>
		<form action="">
			<div class="input-group">
				<span class="input-group-text">상품번호</span>
				<input class="form-control" type="number" id="pnum" name="pnum">
			</div>
			<div class="input-group">
				<span class="input-group-text">상품이름</span>
				<input class="form-control" type="text" id="name" name="name">
			</div>
			<div class="input-group">
				<span class="input-group-text">제조회사</span>
				<input class="form-control" type="text" id="company" name="company">
			</div>
			<div class="input-group">
				<span class="input-group-text">이미지</span>
				<input class="form-control" type="file" id="image01" name="image01">
			</div>
			<div class="input-group">
				<span class="input-group-text">재고</span>
				<input class="form-control" type="number" id="stock" name="stock">
			</div>
			<div class="input-group">
				<span class="input-group-text">가격</span>
				<input class="form-control" type="number" id="price" name="price">
			</div>
			<div class="input-group">
				<span class="input-group-text">카테고리</span>
				<select id="category" name="category" class="form-select">
					<option value="-">항목을 선택해주세요</option>
				  	<option value="bread">빵</option>
				  	<option value="beverage">음료</option>
				  	<option value="cake">케이크</option>
				</select>
			</div>
			<div class="input-group">
				<span class="input-group-text">내용</span>
				<input class="form-control" type="text" id="contents" name="contents">
			</div>
			<div class="input-group">
				<span class="input-group-text">적립 포인트</span>
				<input class="form-control" type="number" id="point" name="point">
			</div>
			<div class="input-group">
				<span class="input-group-text">입고일자</span>
				<input class="form-control" type="date" id="inputdate" name="inputdate">
			</div>
			<div id="buttonset" class="input-group">
				<button type="submit" class="btn btn-primary">등록</button>
				&nbsp;&nbsp;&nbsp;
				<button type="reset" class="btn btn-primary">초기화</button>
			</div>
		</form>
	</div>
</body>
</html>