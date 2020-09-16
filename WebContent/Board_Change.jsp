<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.*"%>
<%@ page import="Dao.*"%>
<% 
    BoardBean board = (BoardBean)request.getAttribute("boardView"); 
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script language="javascript">
   function writeCheck()
   {
      var form = document.writeform;
      
      if( !form.name.value )
      {
         alert( "이름을 적어주세요" );
         form.name.focus();
         return;
      }

      if( !form.title.value )
      {
         alert( "제목을 적어주세요" );
         form.title.focus();
         return;
      }

      if( !form.memo.value )
      {
         alert( "내용을 적어주세요" );
         form.memo.focus();
         return;
      }
		
      form.submit();
      location.href = "music?task=mainHome#fragment-3";
   }
</script>
<head>
<style type='text/css'>
@import url("Board_write.css");
</style>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>음악의 중심 - WaterMelon</title>
</head>
<body>
<table class = type_board width=500 border=1 cellspacing=0 cellpadding=5 align = "center">
   <form name=writeform method=post action="BoardServlet">
      <input type="hidden" name="task" value="BoardChangeFinish">
      <input type="hidden" name="num" value="<%= board.getBoardNum() %>">
      <tr>
         <th><b>이름</b></th>
         <td><input type=text name=name size=50  maxlength=50 value="<%= board.getBoardWriter() %>"></td>
      </tr>
      <tr>
         <th><b>제목</b></th>
         <td><input type=text name=title size=50  maxlength=50 value="<%= board.getBoardName() %>"></td>
      </tr>
      <tr>
         <th><b>내용</b></th>
         <td><textarea name=memo cols=50 rows=10><%= board.getBoardMemo() %></textarea></td>
      </tr>
   </form> 
</table>

<table cellspacing = 0 cellpadding = 0 border = 0 align="center">
   <tr>
      <td><input type=button value="수정" OnClick="javascript:writeCheck();">&nbsp;&nbsp;</td>
      <td><a href="javascript:history.back()"><input type=button value="뒤로가기"></a></td>
   </tr>
</table>
</body>
</html>