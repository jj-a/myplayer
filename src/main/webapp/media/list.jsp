<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html lang="ko">

<head>
<meta charset="UTF-8">
<title>media/list.jsp</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>
	<div id="playlist">
		<div class="title">음악 목록</div>
		<hr>
		<div class="list">
			<table style="width:100%">
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>등록일</th>
					<th>파일정보</th>
					<th>수정/삭제</th>
				</tr>
				<c:forEach var="article" items="${list }">
					<tr>
						<td>${article.mediano }</td>
						<td class="link" onclick="location.href='./play.do?mediano=${article.mediano}'" onmouseover="style='cursor:pointer;'">
							${article.title }
						</td>
						<td>${article.rdate }</td>
						<td>
							<c:set var="filesize" value="${fn:substringBefore(article.filesize/1024, '.') }" />
							${article.filename }
							<br><h6 style="margin:5 auto;">(${filesize }KB)</h6>
							<input type="button" value="다운로드" onclick="location.href='${root}/download?dir=/media/storage&filename=${article.filename }'">
						</td>
						<td><input type="button" value="수정" onclick="location.href='./update.do?mediano=${article.mediano}'">
						&nbsp;<input type="button" value="삭제" onclick="location.href='./delete.do?mediano=${article.mediano}'"></td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan=4>
						<div class="bottom">
							<input type="button" value="음악 추가" onclick="window.location.href='./create.do?mediagroupno=${mediagroupno}'">
							&nbsp;<input type="button" value="메인" onclick="window.location.href='../home.do'">
						</div>
					</td>
				</tr>
			</table>
		</div>
	</div>

	<script>
		function loadMusic(img, music) {
			var poster = document.getElementById("poster");
			var audio1 = document.getElementById("audio1");
			poster.src = "../music/"+img;
			audio1.src = "../music/"+music;

			audio1.play();
		}//loadMusic() end
	</script>
</body>

</html>