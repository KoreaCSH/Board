<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tf" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
	<p>게시일: <tf:formatDateTime value="${board.uploaddate}" pattern="yyyy-MM-dd HH:mm" /></p>
	<p>조회수: ${board.hit}</p>
	<p>첨부파일: ${board.files}</p> <br>
	<p>내용: ${board.content}</p> <br>
	<p><a href="/Board/board">목록</a></p>
	
	<div>
	<form method="post" action="comment">
	<textarea name="comment_content"></textarea>
	<input type="submit" value="등록"/>
	</form>	
	</div>
</body>
</html>