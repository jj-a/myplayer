<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>media/createForm.jsp</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	<div class='title'>음원 등록</div>
	<form name="frm" id="form" method="post" action="./create.do" enctype="multipart/form-data">
		<input type="hidden" name="mediagroupno" value="${mediagroupno }">
		<table class="writetb" border=1>
			<tr>
				<th>제목</th>
				<td><input type='text' name='title' size='50' value='mp3'></td>
			</tr>
			<tr>
				<th>포스터</th>
				<td><input type='file' name='posterMF' size='50'></td>
			</tr>
			<tr>
				<th>미디어 파일</th>
				<td><input type='file' name='filenameMF' size='50'></td>
			</tr>
		</table>

		<div class='bottom'>
			<input type='submit' value='등록'> <input type='button' value='음원목록' onclick="location.href='./list.do'"> 
			<input type='button' value='HOME' onclick="location.href='../mediagroup/list.do'">
		</div>

	</form>
</body>
</html>
