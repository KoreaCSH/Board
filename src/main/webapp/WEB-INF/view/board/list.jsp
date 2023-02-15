<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tf" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록</title>
<link href="css/board/board.css" type="text/css" rel="stylesheet" />
</head>
<body>
	
      <table class="type11">
        <caption class="text-center">Board List</caption>
        <thead>
          <tr>
            <th id="id">번호</th>
            <th id="title">제목</th>
            <th id="name">작성자</th>
            <th id="upload-date">작성일</th>
            <th id="hit">조회수</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="board" items="${list}">
          <tr>
            <td>${board.board_id}</td>
            <td id="td-title"><a href="board/${board.board_id}">${board.title}</a></td>
            <td>${board.name}</td>
            <td><tf:formatDateTime value="${board.uploaddate}" pattern="yyyy-MM-dd" /></td>
            <td><fmt:formatNumber value="${board.hit}"/></td>
          </tr>
          </c:forEach>
        </tbody>
      </table>

</body>
</html>