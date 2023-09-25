<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/common/common.jsp" %>
<%@ include file="/common/bootstrap5.jsp" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript">

	/* 삭제 버튼 클릭 */
	/* on() 메소드는 선택된 요소에 이벤트 핸들러 함수를 연결시켜 주는 기능을 합니다. */
	/* cnum이라는 속성을 개발자가 지정해 두었습니다. */
	$(document).on('click', '.delete_btn', function(){
		if(confirm('선택하신 항목을 삭제하시겠습니까?')){
			$.ajax({
				url:'<%=notWithFormTag%>cmDelete', 
				data:'cnum=' + $(this).attr('cnum') ,
				type:'get', 
				dataType:'text',
				success:function(result, status){
					console.log(result);	
					console.log(status);
					getListComment(); 
				}
			});
		}
	});

	function getListComment(){
		// 동적으로 생성되는 영역을 비워줌
		$('#comment_List').empty();	
		/* $.ajax() 함수를 이용하여 데이터 보여 주기 */
		$.ajax({
			url:'<%=notWithFormTag%>cmList', 
			data:'no=' + '${requestScope.bean.no}',
			type:'get', 
			dataType:'json',
			success:function(result, status){
				/* console.log('result는 넘어온 데이터 결과 값') ; */
				/* console.log(result) ; */
				
				$.each(result, function(idx){ /* idx는 색인 번호 */
					var cnum = result[idx].cnum ;
					var id = result[idx].id ;
					var content = result[idx].content ;
					var regdate = result[idx].regdate ;
					addNewItem(cnum, id, content, regdate);
				})
			},
			error:function(result, status){
				console.log(result) ;
				console.log(status) ;
			}
		});
	}
	
	function addNewItem(cnum, id, content, regdate) {
		// 댓글 한개를 추가해주는 함수
		
		// 댓글의 외곽 li 태그
		var litag = $('<li>');
		litag.addClass('commentItem');
		
		// 작성자 정보가 들어갈 태그
		var ptag = $('<p>'); 
		ptag.addClass('id');
		
		// 작성자 이름이 들어갈 태그
		var spantag = $('<span>');
		spantag.addClass('name');
		spantag.html(id + '님');
		
		// 작성일자가 들어갈 태그
		var spandate = $('<span>');
		spandate.html('&nbsp;&nbsp;/&nbsp;&nbsp;' + regdate + '&nbsp;');
		
		// 로그인한 사람이 작성한 댓글이면 삭제버튼 활성화
		if (id.trim() === '${sessionScope.loginfo.id}'.trim()) {
			// 삭제 버튼
			var inputtag = $('<input>');
			var attrlist = {'id':id, 'type':'button', 'value':'삭제', 'class':'btn btn-xs btn-outline-primary', 'cnum':cnum};
			inputtag.attr(attrlist);
			inputtag.addClass('delete_btn');
		} else {
			var inputtag = '';
		}
		
		// 댓글 내용
		var content_p = $('<p>');
		content_p.html(content);
		
		// 조립하기(compose up)
		ptag.append(spantag).append(spandate).append(inputtag);
		litag.append(ptag).append(content_p);
		
		$('#comment_List').append(litag);
		
	}
	
	$(document).ready(function(){
	    getListComment();

	    // 사용자가 댓글을 입력하고 댓글 폼 전송 버튼을 눌렀을 때,
	    $('#submitComment').click(function() { // 버튼 클릭 이벤트 처리
	        // 댓글입력없이 전송버튼을 누른 경우
	        if (!$('#content').val()) {
	            swal('댓글을 입력해주세요.');
	            $('#content').focus();
	            return;
	        }

	        // post 방식으로 데이터를 전송합니다.
	        var URL = '<%=notWithFormTag%>cmInsert';
	        var parameters = $('#comment_form').serialize();
	        $.post(URL, parameters, function(data) {
	            // 내용 적는 창 비우기
	            $('#content').val('');
	            // 목록 갱신하기
	            getListComment();
	        }).fail(function() {
	            swal('댓글 작성에 실패하였습니다.');
	        });
	    });
		 
	});
	
	
	

</script>

<style type="text/css">
#backButton{
	margin: auto;
}

#tableMain{
	background-color: #ccf2ff;
}

#contents{
	height: 500px;
}

#backButton{
	margin-top: 10px;
}
</style>

<style type="text/css">
 /* 댓글들을 위한 스타일 지정 */
 * {
    padding: 0;
    margin: 0;
    color: #333;
 }
 ul { list-style: none; }
 #container { padding: 30px 20px; }
 #insertComment {
    padding: 20px 15px;
    border-bottom: 1px solid #7BAEB5;
 }

 #insertComment label {
    display: inline-block;
    width: 80px;
    font-size: 14px;
    font-weight: bold;
    margin-bottom: 10px;
 }

 #insertComment input[type='text'], #insertComment textarea {
    border: 1px solid #ccc;
    vertical-align: middle;
    padding: 3px 10px;
    font-size: 12px;
    line-height: 150%;
 }

 #insertComment textarea {
    width: 450px;
    height: 120px ;
 }

 .commentItem {
    font-size: 13px;
    color: #333;
    padding: 15px;
    border-bottom: 1px dotted #ccc;
    line-height: 150%;
 }

 .commentItem .id {
    color: #555;
    line-height: 200%;
 }

 .commentItem .id input {
    vertical-align: middle;
 }

 .commentItem .id .name {
    color: #222;
    font-weight: bold;
    font-size: 14px;
 }
 
 .form-group {
    margin-bottom: 3px;
 }
 
 .form-control {
    height: 25px;
 }
 
 .emoticon{
 	text-decoration: none;
 }
 
 
 .btn-primary{opacity: 0.8;}
</style>

</head>
<body>
<div class="container">
		<table class="table">
			<thead></thead>
			<tbody>
				<tr>
					<td align="center" class="col-lg-2" id="tableMain">글 제목</td>
					<td class="col-lg-10">${requestScope.bean.subject}</td>
				</tr>
				<tr>
					<td align="center" id="tableMain">글 작성자</td>
					<td>${requestScope.bean.id}</td>
				</tr>
				<tr>
					<td align="center" id="tableMain">조회수</td>
					<td>${requestScope.bean.readhit}</td>
				</tr>
				<tr>
					<td align="center" id="tableMain">올린날짜</td>
					<td>${requestScope.bean.regdate}</td>
				</tr>	
				<tr id="contents">
					<td align="center" style="vertical-align: middle;">내용</td>
					<td style="vertical-align: middle;">${requestScope.bean.contents}</td>		
				</tr>
			</tbody>
		</table>
		<div id="backButton">
			<button type="button" class="btn btn-primary" onclick="history.back()">돌아가기</button>
			
			 &nbsp;&nbsp;&nbsp;
			         
	        <a class="emoticon" href="<%=notWithFormTag%>boEmoticon&mode=likes&no=${bean.no}">
	            <img src="<%=appName%>/image/likes.png" width="30px" height="30px" alt="">
	            ${bean.likes}
	        </a>
	         &nbsp;&nbsp;
	        <a class="emoticon" href="<%=notWithFormTag%>boEmoticon&mode=hates&no=${bean.no}">
	            <img src="<%=appName%>/image/hates.png" width="30px" height="30px" alt="">
	            ${bean.hates}
	        </a>   
		</div>
		
		<div>
			<%-- 댓글 영역 --%>
			<ul id = "comment_List">
				<%-- 여기에 동적으로 요소들을 추가합니다. --%>
			</ul>
		</div>
        <div id="insertComment">
           <form id="comment_form" method="post" role="form" class="form-horizontal" >
		   <table class="table">
		       <thead>
		       </thead>
		       <tbody>
		         <tr>
		           <td>
		              <label for="content" class="col-xs-3 col-lg-3 control-label">작성자</label>              
		           </td>
		           <td>
		            <input type="hidden" name="no" value="${bean.no}" />
		            <input type="text" name="fakeid" id="fakeid" class="form-control" size="10" 
		               disabled="disabled" value="${sessionScope.loginfo.name}(${sessionScope.loginfo.id})님">                           
		            <input type="hidden" name="id" id="id" value="${sessionScope.loginfo.id}">
		            <input type="hidden" name="comment_Type" id="comment_Type" value="맛집 or 관광지 or 행사 or 자유게시판">
		           </td>
		         </tr>
		         <tr>
		           <td>
		              <label for="content" class="col-xs-3 col-lg-3 control-label">댓글 내용</label>
		           </td>
		           <td>
		              <textarea name="content" rows="3" cols="50" id="content" ></textarea>
		           </td>
		         </tr>
		         <tr>
		           <td colspan="2">
		              <button type="button" id="submitComment" class="btn btn-info">저장하기</button> 
		         </td>
		         </tr>
		       </tbody>
		   </table>
		</form>
      </div>
	</div>
</body>
</html>