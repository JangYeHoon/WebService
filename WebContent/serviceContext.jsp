<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import = "java.util.*" %>
<%@ page import = "Dao.*" %>
<%
	ServiceCenterBean service = (ServiceCenterBean)request.getAttribute("serviceContext");
	int userNum = (Integer)session.getAttribute("userNum");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>음악의 중심 - WaterMelon</title>
</head>
<body>
<table border=1 cellpadding="0" cellspacing="0">
	<tr>
		<td style="font-family:돋음; font-size:12" height="16">
			<div align="center">제 목&nbsp;&nbsp;</div>
		</td>
		
		<td style="font-family:돋음; font-size:12">
		<%= service.getServicecenterTitle() %>
		</td>
	</tr>
	
	<tr bgcolor="cccccc">
		<td colspan="1" style="height:1px;">
		</td>
	</tr>
	
	<tr>
		<td style="font-family:돋음; font-size:12">
			<div align="center">내 용</div>
		</td>
		<td style="font-family:돋음; font-size:12">
			<table border=0 width=490 height=250 style="table-layout:fixed">
				<tr>
					<td valign=top style="font-family:돋음; font-size:12">
					<%= service.getServicecenterContents() %>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	
	<tr bgcolor="cccccc">
		<td colspan="2" style="height:1px;"></td>
	</tr>
	<tr><td colspan="2">&nbsp;</td></tr>
	
	<tr align="center" valign="middle">
		<td colspan="5">
			<font size=2>
				<a href="<%= request.getContextPath() %>/ServiceCenterServlet?task=serviceList">[목록]</a>
			</font>
		</td>
		<% if (userNum == 0) { %>
		<td colspan="5">
         <font size=2>
            <a href="<%= request.getContextPath() %>/serviceReWrite.jsp?num=<%= service.getServicecenterNum() %>&name=<%=service.getServicecenterName()%>">[답변]</a>
         </font>
      </td>
      <% } %>
	</tr>
</table>
</body>
</html>