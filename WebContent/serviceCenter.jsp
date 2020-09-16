<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type='text/css'>
@import url("serviceCenter.css");
</style>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>음악의 중심 - WaterMelon</title>
</head>
<body>
   <table class="type11">
      <thead>
      <tr>
         <th scope ="cols"><h3>계정문제</h3></th>
         <th scope ="cols"><h3>문의</h3></th>
         <th scope ="cols"><h3>자주하는질문</h3></th>
      </tr>
      <tbody>
      <tr>
         <td><a href="userInfoChange.jsp"><img src="images\btn_IDchange.png" width="20"height="20"border="0">정보 변경</a></td>
         <td><a href = "serviceWrite.jsp"><img src="images\question_write.png" width="20"height="20"border="0">문의 내용 작성</a></td>
         <td></td>
      </tr>
      <tr>
         <td></td>
         <td><a href="<%= request.getContextPath() %>/ServiceCenterServlet?task=serviceList"><img src="images\list_check.png" width="20"height="20"border="0">문의 내역</a></td>
         <td></td>
      </tr>
      </tbody>
   </table>
</body>
</html>