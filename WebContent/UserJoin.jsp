<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script type="text/javascript">
	function checkValue() {
		var userinput = eval("document.userinput");
		if (!userinput.userId.value) {
			alert("���̵� �Է��ϼ���.");
			return false;
		}

		if (!userinput.userPW.value) {
			alert("��й�ȣ�� �Է��ϼ���.");
			return false;
		}
	}

	function openConfirmid(userinput) {
		if (userinput.userId.value == "") {
			alert("���̵� �Է��ϼ���");
		}

		url = "confirmId.jsp?id=" + userinput.userId.value;

		open(url, "confirm", "toolbar = no, location = no, status = no,"
				+ "menubar = no, scrollbars = no, resizable = no,"
				+ "width = 300, height = 200");
	}
</script>
<style type='text/css'>
@import url("join.css");

#btn {
	width: 200px;
	margin: auto;
	padding: 5px;
}
</style>
<link rel="stylesheet" href="join.css">
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>������ �߽� - WaterMelon</title>
</head>
<body>
	<form action="UserServlet" method="post" name="userinput"
		onsubmit="return checkValue()">
		<fieldset>
			<legend>�������� �Է�</legend>
			<ol>
				<li><label for="userID">���̵�</label> <input type="text"
					name="userId"></li>
				<li><input type="button" name="confirm_id" value="�ߺ�Ȯ��"
					onclick="openConfirmid(this.form)"></li>
				<li><label for="userPW">�н�����</label> <input type="password"
					name="userPW"></li>
				<li><label for="userName">�̸�</label> <input type="text"
					name="userName"></li>
				<li><label for="phone">��ȭ��ȣ</label> <input type="text"
					name="userPhone"></li>
			</ol>
		</fieldset>
		<button type="submit" id="btn">ȸ������</button>
	</form>
	<br>
	<br>
	<br>
	<br>
	<a href="javascript:history.go(-1)"><input type=button value="�ڷ�"></a>
</body>
</html>