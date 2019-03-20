<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html lang="ko">

<head>
<meta charset="UTF-8">
<title>mediagroup/msgView.jsp</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>
	<div class="title">알림</div>
	<div id="content">
		<dl>
			<dd>${msg1!=null ? img : "" }
				<br> ${msg1 }
			</dd>
			<dd>${msg2!=null ? img : "" }
				<br> ${msg2 }
			</dd>
			<dd>${msg3!=null ? img : "" }
				<br> ${msg3 }
			</dd>
		</dl>
		<p>${link1 }&nbsp;${link2 }&nbsp;${link3 }</p>
	</div>

</body>

</html>