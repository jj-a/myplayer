<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>media/updateForm.jsp</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	<div class='title'>음원 수정</div>
	<form name="frm" id="form" method="post" action="./update.do" enctype="multipart/form-data">
		<input type="hidden" name="mediagroupno" value="${article.mediagroupno }">
		<input type="hidden" name="mediano" value="${article.mediano }">
		<table class="writetb" border=1>
			<tr>
				<th>제목</th>
				<td><input type='text' name='title' size='50' value='${article.title }'></td>
			</tr>
			<tr>
				<th>포스터</th>
				<td>
					<input type='file' name='posterMF' size='50' style="color:transparent;" onchange="style='color:auto;'">
					<label for='posterMF'>${article.poster }</label>
				</td>
			</tr>
			<tr>
				<th>미디어 파일</th>
				<td>
					<input type='file' name='filenameMF' size='50' style="color:transparent;" onchange="style='color:auto;'">
					<label for='filenameMF'>${article.filename }</label>
				</td>
			</tr>
		</table>

		<div class='bottom'>
			<input type='submit' value='수정'> <input type='button' value='음원목록' onclick="location.href='./list.do'"> 
		</div>

	</form>
</body>
</html>
