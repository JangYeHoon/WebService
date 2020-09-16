<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%
   String userId = (String) session.getAttribute("userId");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style type='text/css'>
@import url("Board_write.css");
</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>음악의 중심 - WaterMelon</title>
<script>
function gopage(){
	location.href = "music?task=mainHome#fragment-4";
}
</script>
</head>
<body>
	<form action="ServiceCenterServlet" method="post">
		<input type="hidden" name="task" value="serviceWrite">
		<table class=type_board border=1 cellspacing=0 cellpadding=0
			align="center">
			<tr>
				<th><b>글쓴이</b></th>
				<td><%=userId%></td>
			</tr>
			<tr>
				<th><b>제목</b></th>
				<td><input type="text" name="serviceTitle" size="50"
					maxlength="100" /></td>
			</tr>
			<tr>
				<th><b>내용</b></th>
				<td><textarea name="serviceContents" cols="67" rows="15"></textarea>
					</textarea></td>
			</tr>
		</table>
		<table cellspacing=0 cellpadding=0 border=0 align="center">
			<tr>
				<td><input type="submit" value="작성"></td>
			</tr>
		</table>
	</form>
	<center><a href="javascript:history.go(-1)"><input type="button" value="뒤로"></a></center>
</body>
</html>