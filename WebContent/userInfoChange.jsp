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
<title>음악의 중심 - WaterMelon</title>
</head>
<body>
   <form action="UserServlet" method="post" name="userinput" enctype="multipart/form-data">
   <fieldset>
         <legend>개인정보 입력</legend>
         <ol>
            <li><label>아이디</label><%=userId%></li>
            <li><label>이름</label> <input type = "text" name = "userName"></li>
            <li><label>전화번호</label><input type = "text" name = "userPhone"></li>
            <input type="submit" value="변경완료">
         </ol>
      </fieldset>
   </form>
</body>
</html>