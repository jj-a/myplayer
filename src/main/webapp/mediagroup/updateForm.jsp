<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html lang="ko">

<head>
<meta charset="UTF-8">
<title>mediagroup/updateForm.jsp</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>

	<div class="title"><h2>미디어 그룹 수정</h2></div>

	<form name="frm" method="post" action="./update.do" onsubmit="return true">
		<input type="hidden" name="mediagroupno" value="${article.mediagroupno }">
		<table class="writetb" border=1 style="border-collapse: collapse;">
			<tr>
				<td>미디어 그룹 제목</td>
				<td><input type="text" name="title" id="title" value="${article.title }" size="30" placeholder="그룹 제목"></td>
			</tr>
			<tr>
				<td colspan=2 style="text-align:center;">
					<div class="bottom">
						<input type="submit" value="수정">&nbsp;
						<input type="button" value="목록" onclick="window.location.href='./list.do'">
					</div>
				</td>
			</tr>
		</table>
	</form>

</body>
</html>
