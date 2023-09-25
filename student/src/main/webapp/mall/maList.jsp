<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%>
<%@ include file="/common/bootstrap5.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	/* 총금액과 적립 포인트를 위한 스타일 지정 */
	.largeSpan {
		color: red;
		font-size: 1.2em;
		font-weight: bolder;
	}
	
	/* 수정버튼 스타일 지정 */
	.row{margin: auto; vertical-align: middle;}
	.col{margin: -10px; vertical-align: middle;}
	.form-control-sm{width: 40px; height: 40px; border: 1px solid Gainsboro;}
</style>
<script type="text/javascript">

	// 카트 목록에서 pnum의 개수를 변경해주는 함수
	function editQty(pnum) {
		// 입력양식을 'edit_상품번호' 형식으로 만들어 두었습니다.
		var qty = $('#edit_' + pnum).val();	
		if(!qty){
			swal('수정할 개수를 입력해주세요');
			$('#edit_' + pnum).focus;
			return false;s
			
		}
		// 수정될 상품의 번호와 수정량을 컨트롤러로 넘긴다.
		location.href = '<%=notWithFormTag%>maUpdate&pnum=' + pnum + '&qty=' + qty;  
		
	}
	
	

	// 전체 체크박스 클릭시
	function checkAll() {
		if($('#checkall').prop('checked')) { // 전체 선택 on
			$('input[name=cartCheckBox]').prop('checked',true);
 		} else { // 전체 선택 off
 			$('input[name=cartCheckBox]').prop('checked',false);
 		}
		
		reCalculateAmount();
	}

	// 개별체크박스의 상태나, 전체 체크박스 상태의 변경시 호출됩니다
	function reCalculateAmount() {
		var cartTotalPrice = 0;
		var cartTotalPoint = 0;
		
		// 체크 상태가 on인 항목들을 반복합니다.
		$('input[name=cartCheckBox]:checked').each(function() { //체크된 것들만.
			var pnum = $(this).val();
			var price = $('.price_' + pnum).attr('data');
			var point = $('.point_' + pnum).attr('data');
			var qty = $('.qty_' + pnum).attr('data');
			
			cartTotalPrice += price * qty;
			cartTotalPoint += point * qty;
		});
		
		$('.cartTotalPrice').html(AddComma(cartTotalPrice)+ '원');
		$('.cartTotalPoint').html(AddComma(cartTotalPoint)+ 'P');
	}
	
	// 3자리마다 콤마 유형 넣기
    function AddComma(num){ 
        var regexp = /\B(?=(\d{3})+(?!\d))/g;
        return num.toString().replace(regexp, ',');
    }   
	
	$(document).ready(function(){
	   $('#checkall').prop('checked' , true); // 전체 선택 체크박스 on
	   $('input[name=cartCheckBox]').each(function() { // 개별 체크 박스 on
			$(this).prop('checked', true);
		});
	   
	   $('input[name=cartCheckBox]').change(function() { // 개별 체크 박스의 상태 변경될때,
			reCalculateAmount();
		});
	   
	});
</script>
</head>
<body>
	<div class="container mt-3">
		<h2>카트 내역 보기</h2>
		<p>나의 관심/구매예정인 목록</p>
		<table class="table table-striped">
			<thead>
				<tr>
					<th width="9%">
						<div class="form-check">
							<input class="form-check-input" type="checkbox" id="checkall" name="option1" onclick="checkAll();"> 
							<label class="form-check-label">전체 선택</label>
						</div>
					</th>
					<th align="center" valign="middle">상품명</th>
					<th align="center" valign="middle">수량</th>
					<th align="center" valign="middle">단가</th>
					<th align="center" valign="middle">포인트</th>
					<th align="center" valign="middle">금액</th>
					<th align="center" valign="middle">누적포인트</th>
					<th align="center" valign="middle">수정</th>
					<th align="center" valign="middle">삭제</th>
				</tr>
			</thead>
			<tbody>
			<%-- 변수 총금액: totalAmount, 총누적포인트: totalPoint --%>
			<c:set var="totalAmount" value="" />
			<c:set var="totalPoint" value="" />
			<c:forEach var="bean" items="${sessionScope.cartWishList}">
				<tr>
	                <td align="center" valign="middle">
		                 <div class="form-check">
		                      <input type="checkbox" name="cartCheckBox" value="${bean.pnum}">
		                 </div>                  
               		</td>
					<td align="center" valign="middle">
						<img class="rounded" alt="${bean.image01}" width="40" height="40" src="upload/${bean.image01}">
						${bean.pname}
					</td>
					<td class="qty_${bean.pnum}" data="${bean.qty}" align="center" valign="middle">
						<fmt:formatNumber value="${bean.qty}" pattern="###,###,###"/>개
					</td>
					<td class="price_${bean.pnum}" data="${bean.price}" align="center" valign="middle">
						<fmt:formatNumber value="${bean.price}" pattern="###,###,###"/>원
					</td>
					<td class="point_${bean.pnum}" data="${bean.point}" align="center" valign="middle">
						<fmt:formatNumber value="${bean.point}" pattern="###,###,###"/>P
					</td>
					<td align="center" valign="middle" >
						<fmt:formatNumber value="${bean.price * bean.qty}" pattern="###,###,###"/>원
					</td>
					<td align="center" valign="middle">
						<fmt:formatNumber value="${bean.point * bean.qty}" pattern="###,###,###"/>P
					</td>
					<td align="center" valign="middle">
	                  <div class="row">
	                     <div class="col">
	                     	<span>
	                        	<input class="form-control-sm" type="text" id="edit_${bean.pnum}" name="edit_${bean.pnum}">
	                        </span>
	                        <span>
		                        <button class="btn btn-outline-secondary btn-sm" onclick="editQty('${bean.pnum}');">                      
		                        수정
		                     	</button>
	                     	</span>
	                     </div>
	                  </div>
	               </td>
	               <td align="center" valign="middle">
	                  <a href="<%=notWithFormTag%>maDelete&pnum=${bean.pnum}">
	                     삭제
	                  </a>                  
	               </td>
				</tr>
				<%-- 총금액과 총누적포인트를 누적합니다. --%>
				<c:set var="totalAmount" value="${totalAmount + bean.price * bean.qty}" />
				<c:set var="totalPoint" value="${totalPoint + bean.point * bean.qty}" />
			</c:forEach>
			
			
			<%-- 결제하기, 추가 주문 기능, 총금액, 총 누적 포인트 보여주기 --%>
			<tr>
				<td align="center" colspan="3">
					<a href="<%=notWithFormTag%>maCalculate">결제하기</a>
					&nbsp;&nbsp;&nbsp;
					<a href="<%=notWithFormTag%>prList">추가주문</a>
				</td>
				<td align="left" colspan="6">
					<span class="largeSpan">
						총 금액 : 
						<span class="cartTotalPrice">
							<fmt:formatNumber value="${totalAmount}" pattern="###,###,###"/>원
						</span>
					</span>
					&nbsp;&nbsp;&nbsp;
					<span class="largeSpan">
						총 적립포인트 : 
						<span class="cartTotalPoint">
							<fmt:formatNumber value="${totalPoint}" pattern="###,###,###"/>P
						</span>
					</span>
				</td>
			</tr>
			</tbody>
		</table>
	</div>
</body>
</html>