<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><c:out value="${category}" /></title>
</head>
<body>
<%@ include file="../etc/header.jsp"%>

<div class="container mt-5">
    <h1><c:out value="${category}" /></h1>
	
<!-- 글 작성 버튼 -->
<a href="${pageContext.request.contextPath}/formPost?category=${category}" class="btn btn-outline-secondary">
    글 작성
</a>

	<!-- 글 목록 리스트 -->
	<div class="mt-4">
	    <table class="table table-striped">
	        <thead>
	            <tr>
	                <th style="width: 20%;">작성자</th>
	                <th style="width: 80%;">제목</th>
	            </tr>
	        </thead>
	        <tbody>
	            <c:forEach var="board" items="${boardList}">
	                <tr>
	                    <td>${board.userId}</td>
	                    <td><a href="${pageContext.request.contextPath}/viewPost?boardId=${board.boardId}">${board.title}</a></td>
	                </tr>
	            </c:forEach>
	        </tbody>
	    </table>
	</div>
</div>

</body>
</html>







  
