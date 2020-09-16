<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import = "java.util.*" %>
<%@ page import = "Dao.*" %>
<%
   List serviceList = (List)request.getAttribute("serviceList");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>음악의 중심 - watermelon</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script>
function goPage() {
	location.href = "music?task=mainHome#fragment-4";
}
</script>
</head>
<body>
   <h1>고객센터 문의 내역</h1>
   <table class = "type01" width=50% border="0" cellpadding="0" cellspacing="0">
       <tr align="center" valign="middle" bordercolor="#333333"> 
           <td style="font-family:Tahoma;font-size:8pt;" width="8%" height="26"> 
               <div align="center">번호</div> 
           </td> 

           <td style="font-family:Tahoma;font-size:8pt;" width="50%"> 
               <div align="center">제목</div> 
           </td> 

           <td style="font-family:Tahoma;font-size:8pt;" width="14%"> 
               <div align="center">작성자</div> 
             </td>
          </tr>
          <%
      for ( int i = 0; i < serviceList.size(); i++ ) {
         ServiceCenterBean sl = (ServiceCenterBean)serviceList.get(i);
      %>
      
          <tr align="center" valign="middle" bordercolor="#333333" 
        onmouseover="this.style.backgroundColor='F8F8F8'" 
        onmouseout="this.style.backgroundColor=''"> 
        <td height="23" style="font-family:Tahoma;font-size:10pt;"> 
            <%= sl.getServicecenterNum() %>
        </td> 

        <td style="font-family:Tahoma;font-size:10pt;"> 
            <div align="left">
	            <% if(sl.getServicecenterReCheck() != 0) { %>
	            	&nbsp;&nbsp;-->[Re]
	            <% } %>
               <a href = "ServiceCenterServlet?task=serviceContext&num=<%=sl.getServicecenterNum()%>"><%= sl.getServicecenterTitle() %></a>
            </div>
        </td> 

        <td style="font-family:Tahoma;font-size:10pt;">
        	<% if(sl.getServicecenterReCheck() != 0) { %>
            	<div align="center">admin</div> 
            <% } else { %>
            	<div align="center"><%=sl.getServicecenterName() %></div> 
            <% } %>
        </td>  
        <%} %>
        <td><a href="javascript:goPage()"><input type=button value="뒤로"></a></td>
   </table>
</body>
</html>