<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="Dao.*"%>
<%
	String userId = (String) session.getAttribute("userId");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type='text/css'>
@import url("login.css");
</style>
<link rel="stylesheet" href="login.css">
<title>음악의 중심 - WaterMelon</title>
</head>
<script type="text/javascript">
	function clearText(field) {
		if (field.defaultValue == field.value)
			field.value = "";
	}
</script>
<body>
	<!-- 로고  -->
	<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
	<h2>
		<p align="center">
			<img width="150" height="80" src="images/logo.png" />WaterMelon
		</p>
	</h2>
	<br />

	<!-- 로그인이 됬는지 체크하는 부분 -->
	<%
		if (userId == null || userId == "") {
	%>
	<div style="margin: 0%;">
		<table align="center" cellpadding="5">
				<tr>
					<form action="UserServlet" method="post">
					<input type="hidden" name="task" value="login">
					<td>I D</td>
					<td><input type="text" name="userId" size="15"
						maxlength="15" placeholder="아이디" onFocus="clearText(this)"
						class='form-field'></td>
				</tr>
				<tr>
					<td>P W</td>
					<td><input type="password" name="userPW" size="15"
						maxlength="15" placeholder="비밀번호" onFocus="clearText(this)"
						class='form-field'></td>
				</tr>
				<tr>
					<td>
					<input type="submit" class='button' value="로그인"></td>
					</form>
					<td><form action="UserJoin.jsp" method="post">
					<input type="submit" class='button' value="회원가입">
					</form>
					</td>
				</tr>
			</table>
	</div>
	<% } else { %>
		<% response.sendRedirect("music?task=mainHome"); %>
	<% } %>
</body>
</html>