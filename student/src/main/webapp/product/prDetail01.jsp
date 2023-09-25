<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- %@는 지시어, include는 저 파일을 이 파일에 포함시킨다는 명령 -->
<%@ include file="./../common/bootstrap5.jsp"%>
<%@ include file="./../common/common.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.container {
	margin-top: 30px;
}

.card {
	margin-left: auto;
	margin-right: auto;
}

.leftside {
	margin-left: 0px;
}

.card_borderless {
	border: 0px;
}

.small_image {
	width: 100px;
	height: 100px;
	margin: 10px;
	border-radius: 5px;
}

#totalprice {
	color: red;
	font-size: 20px;
	font-weight: bolder;
}

.cart {
	background-color: black;
	border: 1px solid black;
} /* 속성 표기법 */
.rightnow {
	background-color: red;
	border: 1px solid red;
}

#qty {
	margin: -10px;
	border: 0px;
	font-size: 0.7rem;
}

.plus, .minus {
	font-size: 1.1rem;
}

#bigsize {
	
}
</style>

<link rel="stylesheet" href="./../css/jquery-picZoomer.css">
<script src="./../js/jquery.picZoomer.js"></script>

<script>
	/* const 키워드는 읽기전용(자바의 final과 동일한 개념) */
	/* 최대 구매 가능 개수 */
	const maxPurchaseSize = 5;
	
	/* 단가 */
	var price = 10000; 

	$(document).ready(function(){
		var popoverTriggerList = [].slice.call(document
				.querySelectorAll('[data-bs-toggle="popover"]'))
		var popoverList = popoverTriggerList.map(function(popoverTriggerEl) {
			return new bootstrap.Popover(popoverTriggerEl);
		});
		
		$('#qty').innerWidth($('.minus').innerWidth() - 3);
		
		/* 최초시작시 금액을 0으로 설정 */
		$('#totalprice').text('0');
		
		/* attr() 함수는 속성(attribute)을 읽거나 쓰기 위한 함수 */
		$('.small_image').click(function(){
			$('.active_image').attr('src', $(this).attr('src'));
		});
		
		/* 사용자가 +버튼을 클릭함 */
		$('.plus').click(function(){
			var qty = $('#qty').val();
			if(qty == maxPurchaseSize) {
				alert('최대' + maxPurchaseSize + '개 까지만 주문이 가능합니다.');
				return; /* 더 이상 진행하지 않도록 함 */
			}
			/* Number 객체는 자바의 Integer.parseInt()와 동일한 효과 */
			var newQty = Number(qty) + 1;
			if(qty == ''){
				$('#qty').val('1');
			}else{
				$('#qty').val(newQty);
			}
			$('#totalprice').text(newQty*price);
		});
		
		/* 사용자가 -버튼을 클릭함 */
		$('.minus').click(function(){
			var qty = $('#qty').val();
			if(qty == 0) {
				alert('최소 1개 이상 구매 가능합니다.');
				return; /* 더 이상 진행하지 않도록 함 */
			}
			
			/* Number 객체는 자바의 Integer.parseInt()와 동일한 효과 */
			var newQty = Number(qty) - 1;
			if(qty == ''){
				$('#qty').val('');
				$('#totalprice').text('0');
			}else{
				$('#qty').val(newQty);
				$('#totalprice').text(newQty*price);
			}
		});
		
		/* 수량 입력판 포커스를 잃을 때 */
		$('#qty').blur(function() {
			var qty = $('#qty').val();
			
			/* NaN : Not a Number */
			/* isNaN() : 숫자 형식이 아니면 true를 반환해 줍니다. */
			if(qty =='' || isNaN(qty)){
				alert('0 이상' + maxPurchaseSize + '이하의 숫자만 가능합니다.');
				$('#qty').val('0');
				return;
			}
			
			if(!isNaN(qty)){
				var newQty = Number(qty);
				if(newQty < 0 || newQty > maxPurchaseSize){
					alert('0 이상' + maxPurchaseSize + '이하의 숫자만 가능합니다.');
					$('#qty').val('0');
					$('#qty').focus();
					return;
				}
			}
			
		});
		
		/* 장바구니 클릭 */
		$('.cart').click(function() {
			var qty = $('#qty').val();
			if(qty < 1 || qty > 5){
				alert('최소 1개 이상 담아야 합니다.')
			}
		});
		
		/* 즉시 구매 클릭 */
		$('.rightnow').click(function() {
			var qty = $('#qty').val();
			if(qty < 1 || qty > 5){
				alert('최소 1개 이상 담아야 합니다.')
			}
		});
		
		$('.picZoomer').picZoomer();

	});
</script>
</head>
<body>
	<div class="container">
		<h2>상품 목록</h2>
		<p>고객들이 구매하고자 하는 상품 목록 페이지 입니다.</p>
		<br /> <br />
		<table class="table table-borderless">
			<thead>
			</thead>
			<tbody>
				<tr>
					<td class="col-lg-5">
						<div class="card picZoomer">
							<img alt="그림01" src="./../image/images/coffee01.png"
								class="car-img-top active_image" name="bigsize">
						</div>
					</td>

					<td class="col-lg-1"><img alt="그림01"
						src="./../image/images/coffee01.png"
						class="car-img-top small_image rounded" width="180" height="180">
						<img alt="그림02" src="./../image/images/cappuccino01.png"
						class="car-img-top small_image rounded" width="180" height="180">
						<img alt="그림03" src="./../image/images/vanilla_latte_01.png"
						class="car-img-top small_image rounded" width="180" height="180">
					</td>

					<td class="col-lg-6">
						<div class="card leftside card_borderless">
							<h5 class="card-title">상품01</h5>
							<p class="card-text">이 상품은 좋아요</p>
							<p class="card-text">
								합계: <span id="totalprice">10,000</span>원
							</p>
							<ul class="pagination">
								<li class="page-item"><a class="page-link minus" href="#">
										- </a></li>

								<li class="page-item">
									<a class="page-link" href="#"> 
										<input type="text" name="qty" id="qty" value="0"
										data-bs-trigger="hover" 
										data-bs-toggle="popover" title="수량 누적 알림"
										data-bs-content="기존 카트에 품목이 이미 존재하면 수량을 누적합니다.">
										<!-- hover:마우스가 들어가면 실행, defalt: 클릭하면 실행 -->
									</a>
								</li>

								<li class="page-item"><a class="page-link plus" href="#">
										+ </a></li>
							</ul>
							<div class="btn-group">
								<button type="button" class="btn btn-primary cart">장바구니</button>
								<button type="button" class="btn btn-primary rightnow">바로구매</button>
							</div>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>