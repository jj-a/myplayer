<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html lang="ko">

<head>
<meta charset="UTF-8">
<title>mediagroup/delete.jsp</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>
	<div class="title">미디어 그룹 삭제</div>
	<form method="post" action="./delete.do">
		<input type="hidden" name="mediagroupno" value="${article.mediagroupno }">
		<div id="content">
			<table>
				<tr>
					<td>미디어 그룹을 삭제하시겠습니까?<br>※ 관련 미디어 파일(mp3, mp4)도 전부 삭제됩니다.
					</td>
				</tr>
				<tr>
					<td>
						<div class="bottom">
							<input type="submit" value="삭제 진행">
							<input type="button" value="목록" onclick="window.location.href='./list.do'">
						</div>
					</td>
				</tr>
			</table>
		</div>
	</form>

</body>

</html>