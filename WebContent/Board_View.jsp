<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.util.*"%>
<%@ page import="Dao.*"%>
<%
	BoardBean board = (BoardBean) request.getAttribute("boardView");
	List commentList = (List) request.getAttribute("commentList");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>������ �߽� - WaterMelon</title>
</head>
<body>
	<br>
	<br>
	<br>
	<br>
	<center>
		<table cellpadding="0" cellspacing="0">
			<tr align="center" valign="middle">
				<td colspan="5">���� �Խ���<br> <br></td>
			</tr>
			<tr>
				<td style="font-family: ����; font-size: 12" height="16">
					<div align="center">�� ��&nbsp;&nbsp;</div>
				</td>

				<td align="center" style="font-family: ����; font-size: 12"><%=board.getBoardName()%>
				</td>
			</tr>

			<tr bgcolor="cccccc">
				<td colspan="2" style="height: 1px;"></td>
			</tr>

			<tr>
				<td style="font-family: ����; font-size: 12">
					<div align="center">�� ��</div>
				</td>
				<td style="font-family: ����; font-size: 12">
					<table border=0 width=490 height=250 style="table-layout: fixed">
						<tr>
							<td align="center" valign=top
								style="font-family: ����; font-size: 12"><%=board.getBoardMemo()%>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr bgcolor="cccccc">
				<td colspan="2" style="height: 1px;"></td>
			</tr>
			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>

			<tr align="center" valign="middle">
				<td colspan="5"><font size=3> <a
						href="<%=request.getContextPath()%>/BoardServlet?task=BoardChange&num=<%=board.getBoardNum()%>">
							[����] </a>&nbsp;&nbsp; <a
						href="<%=request.getContextPath()%>/BoardServlet?task=BoardDelete&num=<%=board.getBoardNum()%>">
							[����]</a>&nbsp;&nbsp; <a
						href="<%=request.getContextPath()%>/music?task=mainHome#fragment-3">[���]</a>&nbsp;&nbsp;
				</font></td>
			</tr>
		</table>
	</center>
	<br>
	<br>
	<center>
		<hr size="1" color="#000000" />
		<br>
		<center>
			<h3>��� �ۼ�</h3>
		</center>
		<table>
			<hr size="1" color="#000000" width="600" />
			<%
				for (int i = 0; i < commentList.size(); i++) {
					CommentBean bl = (CommentBean) commentList.get(i);
					if (bl.getCommentName() != null) {
			%>
			<tr align="center" valign="middle" bordercolor="#333333">
				<td><input type="hidden" name="CommentNum"
					value=<%=bl.getCommentNum()%>>
					<div align="right" style="width: 90px;">
						<%=bl.getCommentName()%>
						:
					</div></td>
				<td>
					<div align="left" style="width: 400px;">
						&nbsp;&nbsp;&nbsp;<%=bl.getCommentMemo()%>
					</div>
				</td>
				<td>
					<div align="left">
						<a
							href="<%=request.getContextPath()%>/BoardServlet?task=deleteComment&num=<%=board.getBoardNum()%>&commentNum=<%=bl.getCommentNum()%>"><button>����</button>
						</a>
					</div>
				</td>
			</tr>
		<%
			}
			}
		%>
		</table>
		<hr size="1" color="#000000" width="600"/>
		<table>
			<form name=writeform method=post action="BoardServlet">
				<input type="hidden" name="task" value="CommentWrite"> <input
					type="hidden" name="num" value="1">
				<tr>
					<td><b>�̸�</b></td>
					<td><input type=text name=name size=10 maxlength=50></td>
					<td><b>���</b></td>
					<td><input type=text name=memo size=50 maxlength=50></td>
					<td><input type="submit" class='button' value="�Է�"></td>
				</tr>
			</form>
		</table>
	</center>
</body>
</html>