<%@ page contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>
<%@ page language="java" import="java.util.*, java.sql.*, javax.servlet.http.*" %>
<%@ page import="java.io.*, java.text.*" %>

<script language="javascript">
   function writeCheck()
   {
      var form = document.writeform;
      
      if( !form.name.value )
      {
         alert( "�̸��� �����ּ���" );
         form.name.focus();
         return;
      }

      if( !form.title.value )
      {
         alert( "������ �����ּ���" );
         form.title.focus();
         return;
      }

      if( !form.memo.value )
      {
         alert( "������ �����ּ���" );
         form.memo.focus();
         return;
      }

      form.submit();
      location.href="music?task=mainHome#fragment-3";
   }
</script>
<html>
<style type='text/css'>
@import url("Board_write.css");
</style>
<head>

<title>������ �߽� - WaterMelon</title>
</head>

<table class = type_board width=500 border=1 cellspacing=0 cellpadding=5 align="center">
   <form name=writeform method=post action="BoardServlet">
      <input type="hidden" name="task" value="BoardWrite">
      <tr>
         <th><b>�̸�</b></th>
         <td><input type=text name=name size=50  maxlength=50></td>
      </tr>
      <tr>
         <th><b>����</b></th>
         <td><input type=text name=title size=50  maxlength=50></td>
      </tr>
      <tr>
         <th><b>����</b></th>
         <td><textarea name=memo cols=50 rows=10 colspan=2></textarea></td>
      </tr>
   </form>
</table>

<table cellspacing = 0 cellpadding = 0 border = 0 width=500 align="center">
   <tr>
      <td><input type=button value="���" OnClick="javascript:writeCheck()"></td>
      <td><a href="javascript:history.go(-1)"><input type=button value="�ڷ�"></a></td>
   </tr>
</table>

</html>