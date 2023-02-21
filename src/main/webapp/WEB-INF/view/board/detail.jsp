<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tf" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세</title>
</head>
<body>
	<p>아이디: ${board.board_id}</p>
	<p>이메일: ${board.email}</p>
	<p>이름: ${board.name}</p>
	<p>게시일: ${board.uploaddate}</p>
	<p>조회수: ${board.hit}</p>
	<p>첨부파일: ${board.files}</p> <br>
	<p>내용: ${board.content}</p> <br>
	<p><a href="/Board/board">목록</a></p>
	
	<div>
	<form:form method="post" modelAttribute="commentCommand">
		<label>
			<form:textarea path="comment_content"/>
			<form:errors path="comment_content"/>
		</label>
		<br>
		<input type="submit" value="등록" />
	</form:form>
	</div>
	
	<br>
	
	<div>
	<c:forEach var="comment" items="${comment_list}">
		<p>--------------------------</p>
		<label>아이디: ${comment.email}  게시일: ${comment.comment_uploaddate}</label> <br>
		<label>내용: ${comment.comment_content}</label>
		<p>--------------------------</p>
	</c:forEach>
	</div>
</body>
</html>