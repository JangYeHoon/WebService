<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
   String userId = (String)session.getAttribute("userId");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type='text/css'>
@import url("join.css");
</style>

<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>������ �߽� - WaterMelon</title>
</head>
<body>
   <form action="UserServlet" method="post" name="userinput" enctype="multipart/form-data">
   <fieldset>
         <legend>�������� �Է�</legend>
         <ol>
            <li><label>���̵�</label><%=userId%></li>
            <li><label>�̸�</label> <input type = "text" name = "userName"></li>
            <li><label>��ȭ��ȣ</label><input type = "text" name = "userPhone"></li>
            <input type="submit" value="����Ϸ�">
         </ol>
      </fieldset>
   </form>
</body>
</html>