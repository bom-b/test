<%@page import="com.shopping.model.bean.Product"%>
<%@page import="com.shopping.model.dao.ProductDao"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- %@는 지시어, include는 저 파일을 이 파일에 포함시킨다는 명령 -->    
<%@ include file="./../common/bootstrap5.jsp" %>
<%@ include file="./../common/common.jsp" %>
<%
	ProductDao dao = new ProductDao();
	List<Product> lists = dao.getDatalist();
	
	// request라는 저장소(캐비넷)에 lists을 저장하는데 이름(식별자)은 datalist 입니다.
	// 속성(attribute)은 저장하고자 하는 어떠한 데이터
	// 데이터가 저장되는 저장소를 영역(scope)이라고 합니다.
	request.setAttribute("datalist", lists);
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
	$(document).ready(function(){
	   // Initialize popoveer
	   var popoverTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="popover"]'))
	   var popoverList = popoverTriggerList.map(function (popoverTriggerEl) {
	        return new bootstrap.Popover(popoverTriggerEl);
	   });
	
	});
</script>
<style type="text/css">
	/* table 셀의 수평 가운데 정렬 */
	.card{margin-left:auto;margin-right:auto;}
	.card-img-top{width:250px;height:250px;}
	.removeUnderLine{text-decoration-line: none;}
</style>
</head>
<body>
	<div class="container">
		<h2>상품 목록</h2>
		<p>고객들이 구매하고자 하는 상품 목록 페이지 입니다.</p><br/><br/>
		<table class="table table-borderless">
			<thead>
			</thead>
			<tbody>
				<c:set var="colum_num" value="3" />
				<c:forEach var="bean" items="${requestScope.datalist }" varStatus="status">
					<c:if test="${status.index % colum_num == 0 }"><tr></c:if>
						<td>
							<div class="card" style="width:19rem;">
								<a class="removeUnderLine" href="prDetail02.jsp?pnum=${bean.pnum}">
									<img class="card-img-top" alt="${bean.name }" src="./../image/images/${bean.image01 }" width="200" height="200">
									<div class="card-body">
										<h5 class="card-title">${bean.name }</h5>
										<p class="card-text">
											<span data-bs-toggle="popover" 
											title="${bean.name }" 
											data-bs-trigger="hover"
											data-bs-content="${bean.contents }">
											<c:if test="${fn:length(bean.contents) > 10}">
												${fn:substring(bean.contents, 0, 10)}...
											</c:if>
											</span>
											<c:if test="${fn:length(bean.contents) < 10}">
												${bean.contents}
											</c:if>
										</p>
									</div>
								</a>
							</div>
						</td>
					<c:if test="${status.index % colum_num +1 == 0 }"></tr></c:if>
				</c:forEach>	
			</tbody>
		</table>
	</div>
</body>
</html>