<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="member.register"/></title>
</head>
<body>
	<form:form action="success" method="post" modelAttribute="registerCommand">
	<p>
		이메일: <br> 
		<label>
			<form:input path="email"/>
			<form:errors path="email"/>
		</label>
	</p>
	<p>
		이름: <br>
		<label>
			<form:input path="name"/>
			<form:errors path="name"/>
		</label>
	</p>
	<p>
		성별: <br>
		<label><input type="radio" name="sex" value="남" checked />남자</label>
		<label><input type="radio" name="sex" value="여"/>여자</label>
	</p>
	<p>
		생년월일: <br>
		<label>
			<form:input path="birthdate"/>
			<form:errors path="birthdate"/>
		</label>
	</p>
	<p>
		비밀번호: <br>
		<label>
			<form:password path="password"/>
			<form:errors path="password"/>
		</label>
	</p>
	<p>
		비밀번호 확인: <br>
		<label>
			<form:password path="confirmPassword"/>
			<form:errors path="confirmPassword"/>
		</label>
	</p>
	<input type="submit" value="가입">
	</form:form>
</body>
</html>