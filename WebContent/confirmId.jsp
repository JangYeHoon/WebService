<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import = "java.util.*" %>
<%@ page import = "Dao.UserDao" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>������ �߽� - WaterMelon</title>
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
	�̹� ������� ���̵��Դϴ�.
	<form name = "checkForm" method = "post" action = "confirmId.jsp">
		�ٸ� ���̵� ����ϼ���.
		<input type = "text" size = "10" name = "id">
		<input type = "submit" value = "ID�ߺ�Ȯ��">
	</form>
	<%
		}
		else {
	%>
	�Է��Ͻ� <%= id %>�� ����Ͻ� �� �ִ� ID�Դϴ�.
	<input type = "button" value = "�ݱ�" onclick = "setid()">
	<% } %>
</body>
</html>