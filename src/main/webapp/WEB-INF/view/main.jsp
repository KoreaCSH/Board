<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main</title>
</head>
<body>
	<c:if test="${empty loginInfo}">
		<p>환영합니다.</p>
		<p><a href="register/terms">[회원 가입하기]</a></p>
		<p><a href="login">[로그인]</a></p>
		<p><a href="board">[게시판]</a></p>
	</c:if>
	
	<c:if test="${! empty loginInfo}">
		<p><strong>${loginInfo.name}</strong>님, 환영합니다.</p>
		<p><a href="edit/changePassword">[비밀번호 변경]</a></p>
		<p><a href="logout">[로그아웃]</a></p>
		<p><a href="board">[게시판]</a></p>
	</c:if>
</body>
</html>