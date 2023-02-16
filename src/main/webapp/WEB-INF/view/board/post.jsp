<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글쓰기</title>
</head>
<body>
	<form:form method="post" modelAttribute="postCommand">
	<div>
		<table>
			<tbody>
			<tr>
				<th>제목</th>
				<td colspan="3"><form:input path="title"/></td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td colspan="3"><form:input path="files"/></td>
			</tr>
			<tr>
				<td colspan="4"><form:textarea path="content"/></td>
			</tr>
			</tbody>
		</table>
	</div>
	
	<div>
		<input type="submit" value="등록" />
		<a href="/Board/board">취소</a>
	</div>
	</form:form>
</body>
</html>