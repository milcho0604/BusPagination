<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my"%>
<c:url var="R" value="/" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="${R}common.css">
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="${R}common.js"></script>
<style>
a.btn {
	float: right;
	margin: -20px 0 5px 0;
}
</style>
</head>
<body>
	<div class="container">
		<h1>버스 목록</h1>
		<a href="create?${pagination.queryString}" class="btn">버스 등록</a>
		<table class="list">
			<thead>
				<tr>
					<th>ID</th>
					<th>버스 번호</th>
					<th>출발 차고지</th>
					<th>도착 차고지</th>
					<th>학과 번호</th>
					<th>첫차</th>
					<th>막차</th>
					<th>학과 이름</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="bus" items="${ buses }">
					<tr data-url="edit?id=${bus.id}&${pagination.queryString}">
						<td>${ bus.id }</td>
						<td>${ bus.busNo }</td>
						<td>${ bus.firstStop }</td>
						<td>${ bus.lastStop }</td>
						<td>${ bus.categoryId }</td>
						<td>${ bus.firstBus }</td>
						<td>${ bus.lastBus }</td>
						<td>${ bus.categoryName }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<my:pagination pageSize="${ pagination.sz }"
			recordCount="${ pagination.recordCount }" queryStringName="pg" />
	</div>
</body>
</html>

