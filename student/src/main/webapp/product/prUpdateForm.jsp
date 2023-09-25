<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/common/common.jsp"%>
<%@ include file="/common/bootstrap5.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script>
	$(document).ready(function(){
		$('#inputdate').datepicker({dateFormat: "yy/mm/dd"});		
		
		/* $("#category").prepend('<option value="aaa">bbb</option>'); */
		/* $('option').eq(2).attr('selected', true); */
		
		/* 이전에 선택했던 카테고리 정보가 자동으로 선택되어 있도록 합니다. */
		var category = '${requestScope.bean.category}';
		/* alert(category); */
		
		var optionList = $('#category option');
		optionList.each(function(){
			console.log($(this).val());
			if($(this).val() == category){
				$('option[value="' + category + '"').attr('selected', true);
			}
		});
		
	});
	
	/* form validation check */
	function validCheck(){
		
		/* 상품명은 4글자 이상 10글자 이하이어야 합니다. */
		var name = $('#name').val();
		if(name.length < 4 || name.length > 10){
			swal('상품명은 4글자 이상 10글자 이하이어야 합니다.');
			$('#name').focus();
			return false; 
		}
		
		/* 제조 회사는 4글자 이상 10글자 이하이어야 합니다. */
		var company = $('#company').val();
		if(company.length < 4 || company.length > 10){
			swal('제조 회사는 4글자 이상 10글자 이하이어야 합니다.');
			$('#company').focus();
			return false; 
		}
		
		/* 상품에 대한 코멘트는 10글자 이상 30글자 이하이어야 합니다. */
		var contents = $('#contents').val();
		if(contents.length < 10 || contents.length > 30){
			swal('상품에 대한 코멘트는 10글자 이상 30글자 이하이어야 합니다.');
			$('#contents').focus();
			return false; 
		}
		
		/* 이미지는 필수 입력 사항입니다. */
		var selectedFile = $('#image01').prop('files')[0];
		if(!selectedFile){
			swal('이미지 파일을 업로드 해주세요.');
			$('#image01').focus();
			return false; 
		}
		
		/* 이미지의 확장자는 png 또는 jpg 형식이어야 합니다. */
		var fileName = selectedFile.name;
		var fileExtension = fileName.split('.').pop().toLowerCase();
		if (fileExtension !== 'png' && fileExtension !== 'jpg') {
			swal('이미지의 확장자는 png 또는 jpg 형식이어야 합니다.');
			$('#image01').focus();
			return false; 
		}
		
		/* 재고는(은) 숫자 형식이어야 합니다. */
		var regex = /^[0-9]+$/;
		var stock = $('#stock').val();
		var result = regex.test(stock);
		if(result == false){
			swal('재고는(은) 숫자 형식이어야 합니다.');
			$('#stock').focus();
			return false;
		}
		
		/* 재고는 최대 5개까지입니다. */
		if(stock > 5){
			swal('재고는 최대 5개까지입니다.');
			$('#stock').focus();
			return false; 
		}
		
		/* 단가는(은) 숫자 형식이어야 합니다. */
		var regex = /^[0-9]+$/;
		var price = $('#price').val();
		var result = regex.test(price);
		if(result == false){
			swal('재고는(은) 숫자 형식이어야 합니다.');
			$('#price').focus();
			return false;
		}
		
		/* 단가는(은) 100이상 10000이하의 값이어야 합니다. */
		if(price > 10000 || price < 100){
			swal('단가는(은) 100이상 10000이하의 값이어야 합니다.');
			$('#price').focus();
			return false; 
		}
		
		/* 포인트는(은) 숫자 형식이어야 합니다. */
		var regex = /^[0-9]+$/;
		var point = $('#point').val();
		var result = regex.test(point);
		if(result == false){
			swal('포인트는(은) 숫자 형식이어야 합니다.');
			$('#point').focus();
			return false;
		}
		
		/* 포인트는(은) 3이상 10이하의 값이어야 합니다. */
		if(point > 10 || point < 3){
			swal('포인트는(은) 3이상 10이하의 값이어야 합니다.');
			$('#point').focus();
			return false; 
		}
		
		/* 카테고리를 반드시 선택해 주세요. */
		var category = $('#category').val();
		if(category == '-') {
			swal('카테고리를 반드시 선택해 주세요.');
			$('#category').focus();
			return false;
		}
		
		
		
		/* 날짜 형식은 반드시 yyyy/mm/dd 형식 또는 yyyy-mm-dd으로 작성해 주세요. */
		var regdate = $('#inputdate').val();
		var regex1 = /^\d{4}\/[01]\d{1}\/[0123]\d{1}$/;
		var regex2 = /^\d{4}\-[01]\d{1}\-[0123]\d{1}$/;
		var result1 = regex1.test(regdate);
		var result2 = regex2.test(regdate);
		
		if(result1 == false && result2 == false){
			swal('날짜 형식은 반드시 yyyy/mm/dd 형식 또는 yyyy-mm-dd으로 작성해 주세요.');
			$('#inputdate').focus();
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

/* #productPnum{ /* 상품번호 안보이게 지정 */
	display: none;
	visibility: hidden;
} */

</style>
</head>
<body>
	<div class="container">
		<br />
		<h2>상품 수정</h2>
		<p>(관리자 전용) 상품수정 페이지</p>
		<br />
		<form action="<%=withFormTag%>" method="post" enctype="multipart/form-data">
			<input type="hidden" name="command" value="prUpdate">
			<input type="hidden" name="pageNumber" value="<%=request.getParameter("pageNumber")%>">
			<input type="hidden" name="pageSize" value="<%=request.getParameter("pageSize")%>">
			<input type="hidden" name="mode" value="<%=request.getParameter("mode")%>">
			<input type="hidden" name="keyword" value="<%=request.getParameter("keyword")%>">
			
			<div id="productPnum" class="input-group">
				<span class="input-group-text">상품번호</span> 
				<input class="form-control" type="number" disabled="disabled" id="fakepnum" name="fakepnum" value="${requestScope.bean.pnum}">
				<input class="form-control" type="hidden" id="pnum" name="pnum" value="${requestScope.bean.pnum}">
			</div>
			<div class="input-group">
				<span class="input-group-text">상품이름</span> <input
					class="form-control" type="text" id="name" name="name" value="${requestScope.bean.name}">
			</div>
			<div class="input-group">
				<span class="input-group-text">제조회사</span> <input
					class="form-control" type="text" id="company" name="company"  value="${requestScope.bean.company}">
			</div>
			<div class="input-group">
				<span class="input-group-text">이미지01</span> 
				<input class="form-control" type="file" id="image01" name="image01">
				<input type="hidden" name="deleteImage01" value="${requestScope.bean.image01}">
			</div>
			<div class="input-group">
				<span class="input-group-text">이미지02</span> 
				<input class="form-control" type="file" id="image02" name="image02">
				<input type="hidden" name="deleteImage02" value="${requestScope.bean.image02}">
			</div>
			<div class="input-group">
				<span class="input-group-text">이미지03</span> 
				<input class="form-control" type="file" id="image03" name="image03">
				<input type="hidden" name="deleteImage03" value="${requestScope.bean.image03}">
			</div>
			<div class="input-group">
				<span class="input-group-text">재고</span> <input class="form-control"
					type="number" id="stock" name="stock" value="${requestScope.bean.stock}">
			</div>
			<div class="input-group">
				<span class="input-group-text">가격</span> <input class="form-control"
					type="number" id="price" name="price" value="${requestScope.bean.price}">
			</div>
			<div class="input-group">
				<span class="input-group-text">카테고리</span> 
				<select id="category" name="category" class="form-select">
					<c:forEach var="category" items="${requestScope.categories}">
						<option value="${category.engname}">${category.korname}</option>
					</c:forEach>
				</select>
			</div>
			<div class="input-group">
				<span class="input-group-text">내용</span> <input class="form-control"
					type="text" id="contents" name="contents" value="${requestScope.bean.contents}">
			</div>
			<div class="input-group">
				<span class="input-group-text">적립 포인트</span> <input
					class="form-control" type="number" id="point" name="point" value="${requestScope.bean.point}">
			</div>
			<div class="input-group">
				<span class="input-group-text">입고일자</span> <input
					class="form-control" type="date" id="inputdate" name="inputdate" value="${requestScope.bean.inputdate}">
			</div>
			<div id="buttonset" class="input-group">
				<button type="submit" class="btn btn-primary" onclick="return validCheck();">수정</button>
				&nbsp;&nbsp;&nbsp;
				<button type="reset" class="btn btn-primary">초기화</button>
			</div>
		</form>
	</div>
</body>
</html>