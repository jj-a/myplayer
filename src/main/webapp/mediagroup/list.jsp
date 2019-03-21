<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html lang="ko">

<head>
<meta charset="UTF-8">
<title>mediagroup/list.jsp</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>
	<div id="playlist">
		<div class="title">
			<h1>미디어 그룹 목록</h1>
		</div>
		<div class="content">
			<table style="width:100%">
				<tr>
					<th>그룹번호</th>
					<th>그룹제목</th>
					<th>수정/삭제</th>
				</tr>
				<c:forEach var="article" items="${list }">
					<tr>
						<td>${article.mediagroupno }</td>
						<td class="link" onclick="location.href='../media/list.do?mediagroupno=${article.mediagroupno}'" onmouseover="style='cursor:pointer;'">${article.title }</td>
						<td><input type="button" value="수정" onclick="location.href='./update.do?mediagroupno=${article.mediagroupno}'">
						&nbsp;<input type="button" value="삭제" onclick="location.href='./delete.do?mediagroupno=${article.mediagroupno}'"></td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan=3>
						<div class="bottom">
							<input type="button" value="그룹 등록" onclick="window.location.href='./create.do'">
						</div>
					</td>
				</tr>
			</table>
		</div>
	</div>

</body>

</html>