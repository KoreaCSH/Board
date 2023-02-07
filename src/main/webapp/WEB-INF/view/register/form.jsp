<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
	<form method="post" action="success">
	<p>
		이메일: 
		<label><input type="text" name="email"/></label>
		<form:errors path="email"/>
	</p>
	<p>
		이름: 
		<label><input type="text" name="name"/></label>
		<form:errors path="name"/>
	</p>
	<p>
		성별: 
		<label><input type="radio" name="sex" value="남" checked />남자</label>
		<label><input type="radio" name="sex" value="여"/>여자</label>
	</p>
	<p>
		생년월일: 
		<label><input type="text" name="birthdate"/></label>
		<form:errors path="birthdate"/>
	</p>
	<p>
		비밀번호: 
		<label><input type="password" name="password"/></label>
		<form:errors path="password"/>
	</p>
	<p>
		비밀번호 확인: 
		<label><input type="password" name="confirmPassword"/></label>
		<form:errors path="confirmPassword"/>
	</p>
	<input type="submit" value="가입">
	</form>
</body>
</html>