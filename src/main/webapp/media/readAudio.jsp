<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html lang="ko">

<head>
<meta charset="UTF-8">
<title>media/readAudio.jsp</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>
	<div id="playlist">
		<div class="title">
			<h1>PLAY AUDIO</h1>
			<h2 class="musictitle">♬&nbsp;&nbsp;${article.title }&nbsp;&nbsp;♬</h2>
		</div>
		<div class="content">
			<img id="poster" src="./storage/${article.poster }" style="width: 300px; height: 250px"> <br> <br>
			<audio id="audio1" src="./storage/${article.filename }" controls autoplay></audio>
		</div>
		<hr>
		<div class="list">
			<table style="width: 100%">
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>등록일</th>
				</tr>
				<c:forEach var="article" items="${list }">
					<tr>
						<td>${article.mediano }</td>
						<td class="link" onclick="location.href='./play.do?mediano=${article.mediano}'" onmouseover="style='cursor:pointer;'">${article.title }</td>
						<td>${article.rdate }</td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan=3>
						<div class="bottom">
							<input type="button" value="음악 목록" onclick="window.location.href='./list.do?mediagroupno=${article.mediagroupno}'">
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
			poster.src = "../music/" + img;
			audio1.src = "../music/" + music;

			audio1.play();
		}//loadMusic() end
	</script>
</body>

</html>