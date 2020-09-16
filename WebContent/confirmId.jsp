<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import = "java.util.*" %>
<%@ page import = "Dao.UserDao" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>음악의 중심 - WaterMelon</title>
<% request.setCharacterEncoding("euc-kr"); %>
<%
	String id = request.getParameter("id");
	UserDao manager = new UserDao();
	
	int check = manager.confirmId(id);
%>
<script language = "javascript">
	function setid()
	{
		opener.document.userinput.userId.value = "<%= id %>";
		self.close();
	}
</script>
</head>
<body>
	<%
		if ( check == 0 ) {
	%>
	이미 사용중인 아이디입니다.
	<form name = "checkForm" method = "post" action = "confirmId.jsp">
		다른 아이디를 사용하세요.
		<input type = "text" size = "10" name = "id">
		<input type = "submit" value = "ID중복확인">
	</form>
	<%
		}
		else {
	%>
	입력하신 <%= id %>는 사용하실 수 있는 ID입니다.
	<input type = "button" value = "닫기" onclick = "setid()">
	<% } %>
</body>
</html>