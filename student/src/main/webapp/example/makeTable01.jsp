<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>표 만들기</h1>
	<table border="10">
		<thead>
			<tr>
				<th>이름</th>
				<th>국어</th>
				<th>영어</th>
				<th>수학</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td align="center">김유신</td>
				<td align="right">40</td>
				<td align="right">50</td>
				<td align="right">60</td>
			</tr>
			<tr>
				<td align="center"s>유관순</td>
				<td align="right">45</td>
				<td align="right">55</td>
				<td align="right">65</td>
			</tr>
		</tbody>
	</table>
	<hr/><!-- horizontal ruler -->
	<h1>표 만들기</h1>
	<table border="10">
		<thead>
			<tr>
				<th>이름</th>
				<th>국어</th>
				<th>영어</th>
				<th>수학</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td align="center" rowspan="2">김유신</td>
				<td align="right">40</td>
				<td align="right">50</td>
				<td align="right">60</td>
			</tr>
			<tr>
				<td align="center" colspan="3">150</td>
			</tr>
		</tbody>
	</table>
	<hr/>
	
	<h1>연습</h1>
	<table border="1">
		<thead>
		</thead>
		<tbody>
			<tr>
				<td align="center" width="10%" >가</td>
				<td align="center" width="10%" >나</td>
				<td align="center" width="10%" >가</td>
				<td align="center" width="10%" >나</td>
				<td align="center" width="10%" >가</td>
				<td align="center" width="10%" >나</td>
				<td align="center" width="10%" >나</td>
			</tr>
			<tr>
				<td align="center" colspan="3" >세부설명</td>
				<td align="center" colspan="4" >ㄱㄱㄱㄱㄱ</td>
			</tr>
			<tr>
				<td align="center" rowspan="2" colspan="2" height="20%">코멘트</td>
				<td align="center" colspan="5" >가나다</td>
			</tr>
			<tr>
				<td align="center" colspan="5" >가나다</td>
			</tr>
		</tbody>
	</table>
</body>
</html>