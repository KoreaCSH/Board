<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	</p>
	<p>
		이름: 
		<label><input type="text" name="name"/></label>
	</p>
	<p>
		성별: 
		<label><input type="radio" name="sex" value="남"/>남자</label>
		<label><input type="radio" name="sex" value="여"/>여자</label>
	</p>
	<p>
		생년월일: 
		<label><input type="text" name="birthdate"/></label>
	</p>
	<p>
		비밀번호: 
		<label><input type="password" name="password"/></label>
	</p>
	<p>
		비밀번호 확인: 
		<label><input type="password" name="confirmPassword"/></label>
	</p>
	<input type="submit" value="가입">
	</form>
</body>
</html>