<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.util.*"%>
<%@ page import="Dao.*"%>
<%
	List musicList = (List) request.getAttribute("musicRank");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<meta charset="UTF-8">
<style type='text/css'>
@import url("musicRank.css");
</style>
<title>������ �߽� - WaterMelon</title>
</head>
<body>
	<table class = "type08" >
			<thead>
			<tr>
				<th width = 50 >����</th>
				<th width = 150>��Ƽ��Ʈ</th>
				<th width = 150>����</th>
				<th width = 150 >���</th>
				<th width = 150>���</th>
				<th width = 150>�º�</th>
				<th width = 150>���ƿ�</th>
			</tr>
		</thead>
		<!-- ���� ��ŷ �޾Ƽ� �ѷ��ִ� �κ� -->
		<%
			for (int i = 0; i < musicList.size(); i++) {
				MusicBean ml = (MusicBean) musicList.get(i);
		%>
		<tr>
			<td width = 50><%=i+1%>
			</td>
			<td width = 150><%=ml.getMusicArtist()%>
			</td>
			<td width = 150><%=ml.getMusicName()%>
			</td>
			<!-- ���� �ϳ��ϳ� �����ư -->
			<td width = 150><a
				href="<%=request.getContextPath()%>/music?task=playMusic&title=<%=ml.getMusicNum()%>"><img
					src="images\btn_play.png" width="12" height="11" border="0"></a></td>
			<td width = 150><a
				href="<%=request.getContextPath()%>/music?task=addMusic&title=<%=ml.getMusicNum()%>"><img
					src="images\btn_plus.png" width="10" height=10 "border="0"></a></td>
			<%
				if (ml.getMusicVideoPath() != null) {
			%>
			<td width = 150><a
				href="<%=request.getContextPath()%>/music?task=VideoPlay&title=<%=ml.getMusicNum()%>"><img
					src="images\btn_mv.png" width="15" height="15" border="0"></a></td>
			<%
				} else {
			%>
			<td width = 150><img src="images\btn_mvx.png" width="15" height="15"
				border="0"></td>
			<%
				}
			%>
			<td width = 150><a href="<%=request.getContextPath()%>/music?task=likeMusic&title=<%=ml.getMusicNum()%>">
			<button style="font-size: 10px; color: green">
					���ƿ� <i class="fa fa-heart"></i>
				</button></a><%= ml.getMusicLike() %>
			</td>
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>