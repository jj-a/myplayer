<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html lang="ko">

<head>
<meta charset="UTF-8">
<title>mediagroup/createForm.jsp</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>

	<div class="title">미디어 그룹 등록</div>

	<form name="frm" id="form" method="post" action="./create.do" onsubmit="return true">
		<table class="writetb" border=1>
			<tr>
				<td>미디어 그룹 제목</td>
				<td><input type="text" name="title" id="title" value="" size="30" placeholder="그룹 제목"></td>
			</tr>
			<tr>
				<td colspan=2>
					<div class="bottom">
						<input type="submit" value="등록">&nbsp;
						<input type="button" value="목록" onclick="window.location.href='./list.do'">
					</div>
				</td>
			</tr>
		</table>
	</form>

</body>
</html>
