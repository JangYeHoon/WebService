<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	String userId = (String)session.getAttribute("userId");
	int num = Integer.parseInt(request.getParameter("num"));
	String name = request.getParameter("name");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<form action="ServiceCenterServlet" method = "post">
		<input type="hidden" name="task" value="serviceReWrite">
		<input type="hidden" name="num" value="<%=num%>">
		<input type="hidden" name="name" value="<%=name%>">
		<table cellpadding = "0" cellspacing = "0">
			<tr>
				<td style = "font-family: 돋움; font-size:12" height = "16">
					<div align = "center">제 목</div>
				</td>
				<td>
					<input type="text" name="serviceTitle" size="50" maxlength="100"/>
				</td>
			</tr>
			<tr>
				<td style = "font-family: 돋움; font-size:12" height = "12">
					<div align = "center">내 용</div>
				</td>
				<td>
					<textarea name="serviceContents" cols = "67" rows = "15"></textarea>
				</td>
			</tr>
			<tr align="center" valign="middle">
				<td colspan="5">
					<font size=2>
						<input type="submit" value="작성">
					</font>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>